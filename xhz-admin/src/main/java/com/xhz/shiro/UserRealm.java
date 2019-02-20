package com.xhz.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.xhz.constant.Constant.YESNO;
import com.xhz.util.CopyUtil;
import com.xhz.web.module.sys.dao.MenuDao;
import com.xhz.web.module.sys.dao.UserDao;
import com.xhz.web.module.sys.entity.LoginUser;
import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.entity.UserDO;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserDao UserDao;
	@Autowired
	private MenuDao menuDao;

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LoginUser loginUser = (LoginUser) principals.getPrimaryPrincipal();
		String userId = loginUser.getId();

		List<String> permsList = new ArrayList<String>();
		permsList.add("test1");

		// 用户权限列表
		Set<String> permsSet = new HashSet<String>();
		if (permsList != null && permsList.size() != 0) {
			for (String perms : permsList) {
				if (StringUtils.isBlank(perms)) {
					continue;
				}
				permsSet.addAll(Arrays.asList(perms.trim().split(",")));
			}
		}

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

		LoginUser loginUser = CopyUtil.copyProperties(user, LoginUser.class);
		// 权限
		List<String> permsList = new ArrayList<String>();

		// 如果是超级管理员权限
		if (user.getId().equals(Constant.SUPER_ADMIN)) {
			List<MenuDO> menuDOList = menuDao.selectEnableMemu();
			for (MenuDO menuDO : menuDOList) {
				if (StringUtils.isNotBlank(menuDO.getPerms())) {
					permsList.add(menuDO.getPerms());
				}
			}
			loginUser.setPermsList(permsList);
		} else {

		}

		// 把当前用户放入到session中
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(true);
		session.setAttribute(Constant.CURRENT_USER, loginUser);

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginUser, password, getName());
		return info;
	}

}
