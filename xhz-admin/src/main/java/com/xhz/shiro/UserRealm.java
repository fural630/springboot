package com.xhz.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xhz.constant.Constant;
import com.xhz.constant.Constant.MenuType;
import com.xhz.constant.Constant.YESNO;
import com.xhz.redis.RedisService;
import com.xhz.util.CopyUtil;
import com.xhz.web.module.sys.dao.UserDao;
import com.xhz.web.module.sys.entity.LoginUser;
import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.entity.UserDO;
import com.xhz.web.module.sys.service.MenuService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserDao UserDao;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RedisService redisService;

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LoginUser loginUser = (LoginUser) principals.getPrimaryPrincipal();
		String userId = loginUser.getId();
		Set<String> permsSet = new HashSet<String>();
		if (userId.equals(Constant.SUPER_ADMIN)) {
			List<MenuDO> menuDOList = menuService.selectEnableMemu();
			if (CollectionUtils.isNotEmpty(menuDOList)) {
				for (MenuDO menuDO : menuDOList) {
					if (menuDO.getType().equals(MenuType.BUTTON.getValue()) && StringUtils.isNotBlank(menuDO.getPerms())) {
						permsSet.add(menuDO.getPerms());
					}
				}
			}
		} else {
			String userPermsKey = Constant.PERMS_LIST + userId;
			permsSet = redisService.sGet(userPermsKey);
		}
		// 权限
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		UserDO user = UserDao.selectByUserName(username);
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确！");
		}

		// 密码错误
		if (!password.equals(user.getPassWord())) {
			throw new IncorrectCredentialsException("账号或密码不正确！");
		}

		// 账号锁定
		if (user.getIsDeleted().equals(YESNO.YES.getValue())) {
			throw new LockedAccountException("账号已被禁用，请联系管理员");
		}

		List<MenuDO> menuDOList = new ArrayList<MenuDO>();
		Set<String> permsList = new HashSet<String>();
		// 如果是超级管理员权限
		if (user.getId().equals(Constant.SUPER_ADMIN)) {
			menuDOList = menuService.selectEnableMemu();
			if (CollectionUtils.isNotEmpty(menuDOList)) {
				for (MenuDO menuDO : menuDOList) {
					if (menuDO.getType().equals(MenuType.BUTTON.getValue()) && StringUtils.isNotBlank(menuDO.getPerms())) {
						permsList.add(menuDO.getPerms());
					}
				}
			}
		} else {
			String userPermsKey = Constant.PERMS_LIST + user.getId();
			permsList = redisService.sGet(userPermsKey);
			if (CollectionUtils.isEmpty(permsList)) {
				menuDOList = menuService.selectEnableMemu();//todo select menu by user id
				if (CollectionUtils.isNotEmpty(menuDOList)) {
					for (MenuDO menuDO : menuDOList) {
						if (menuDO.getType().equals(MenuType.BUTTON.getValue()) && StringUtils.isNotBlank(menuDO.getPerms())) {
							permsList.add(menuDO.getPerms());
						}
					}
				}
			}
		}
		LoginUser loginUser = CopyUtil.copyProperties(user, LoginUser.class);
		// 把当前用户放入到session中
		loginUser.setPermsList(new ArrayList<String>(permsList));
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(true);
		session.setAttribute(Constant.CURRENT_USER, loginUser);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginUser, password, getName());
		return info;
	}
}
