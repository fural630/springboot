package com.xhz.web.module.sys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xhz.web.module.sys.entity.LoginUser;
import com.xhz.web.module.sys.entity.RoleDO;
import com.xhz.web.module.sys.entity.RoleDTO;
import com.xhz.web.module.sys.entity.RoleMenuDO;
import com.xhz.web.module.sys.dao.RoleDao;
import com.xhz.shiro.ShiroUtils;
import com.xhz.util.CopyUtil;

import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-05
 */
@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleMenuService roleMenuService;
	
	public void insertRoleDTO(RoleDTO roleDTO) {
		RoleDO roleDO = CopyUtil.copyProperties(roleDTO, RoleDO.class);
		LoginUser user = ShiroUtils.getUser();
		roleDO.setCreateUserId(user.getId());
		roleDO.setCreateTime(new Date());
		this.insert(roleDO);
		List<String> menuIdList = roleDTO.getMenuIdList();
		if (CollectionUtils.isNotEmpty(menuIdList)){
			String roleId = roleDO.getRoleId();
			for (String menuId : menuIdList) {
				RoleMenuDO roleMenuDO = new RoleMenuDO();
				roleMenuDO.setRoleId(roleId);
				roleMenuDO.setMenuId(menuId);
				roleMenuService.insert(roleMenuDO);
			}
		} 
	}
	
	public void updateRoleDTOById(RoleDTO roleDTO) {
		RoleDO roleDO = CopyUtil.copyProperties(roleDTO, RoleDO.class);
		List<String> menuIdList = roleDTO.getMenuIdList();
		String roleId = roleDO.getRoleId();
		roleMenuService.deleteByRoleId(roleId);
		if (CollectionUtils.isNotEmpty(menuIdList)){
			for (String menuId : menuIdList) {
				RoleMenuDO roleMenuDO = new RoleMenuDO();
				roleMenuDO.setRoleId(roleId);
				roleMenuDO.setMenuId(menuId);
				roleMenuService.insert(roleMenuDO);
			}
		} 
		this.updateById(roleDO);
	}
	
	public RoleDTO selectRoleDTOById(String roleId) {
		return roleDao.selectRoleDTOById(roleId);
	}
	
	public List<RoleDTO> selectRoleDTOList() {
		return roleDao.selectRoleDTOList();
	}
	
	public List<RoleDTO> selectRoleDTOPage(Map<String, Object> query) {
		return roleDao.selectRoleDTOPage(query);
	}
	
	public void insert(RoleDO roleDO) {
		roleDao.insert(roleDO);
	}

	public void deleteById(String roleId) {
		roleDao.deleteById(roleId);
	}

	public void deleteBatchIds(List<String> roleIds) {
		roleDao.deleteBatchIds(roleIds);
	}

	public void updateById(RoleDO roleDO) {
		roleDao.updateById(roleDO);
	}

	public RoleDO selectById(String roleId) {
		return roleDao.selectById(roleId);
	}
	
	public List<RoleDO> selectList() {
		return roleDao.selectList(null);
	}
}
