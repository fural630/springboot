package com.xhz.web.module.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.web.module.sys.dao.RoleMenuDao;
import com.xhz.web.module.sys.entity.RoleMenuDO;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-06
 */
@Service
public class RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	public void insert(RoleMenuDO roleMenuDO) {
		roleMenuDao.insert(roleMenuDO);
	}

	public List<String> selectByRoleId(String roleId) {
		return roleMenuDao.selectByRoleId(roleId);
	}
	
	public void deleteByRoleId(String roleId) {
		roleMenuDao.deleteByRoleId(roleId);
	}
}
