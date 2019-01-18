package com.xhz.web.module.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.constant.Constant.IsDeleted;
import com.xhz.util.CopyUtil;
import com.xhz.web.module.sys.dao.MenuDao;
import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.entity.MenuDTO;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-16
 */
@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	
	public void insertMenuDTO(MenuDTO menuDTO) {
		MenuDO menuDO = CopyUtil.copyProperties(menuDTO, MenuDO.class);
		menuDao.insert(menuDO);
	}
	
	public void updateMenuDTOById(MenuDTO menuDTO) {
		MenuDO menuDO = CopyUtil.copyProperties(menuDTO, MenuDO.class);
		menuDao.updateById(menuDO);
	}
	
	public MenuDTO selectMenuDTOById(Long menuId) {
		return menuDao.selectMenuDTOById(menuId);
	}
	
	public List<MenuDTO> selectMenuDTOList(Map<String, Object> params) {
		return menuDao.selectMenuDTOList(params);
	}
	
	public List<MenuDTO> selectMenuDTOPage(Map<String, Object> query) {
		return menuDao.selectMenuDTOPage(query);
	}
	
	public void insert(MenuDO menuDO) {
		menuDao.insert(menuDO);
	}

	public void deleteById(Long menuId) {
		menuDao.deleteById(menuId);
	}

	public void deleteBatchIds(List<Long> menuIds) {
		menuDao.deleteBatchIds(menuIds);
	}

	public void updateById(MenuDO menuDO) {
		menuDao.updateById(menuDO);
	}

	public MenuDO selectById(Long menuId) {
		return menuDao.selectById(menuId);
	}
	
	public List<MenuDO> selectList() {
		return menuDao.selectList(null);
	}
	
	/**
	 * 禁用包括menuId 目录内的子目录
	 * @param menuId
	 */
	public void disableMenuById(Long menuId) {
		List<MenuDO> allMenuList = new ArrayList<MenuDO>();
		recursiveMenu(menuId, allMenuList);
		if (CollectionUtils.isNotEmpty(allMenuList)) {
			for (MenuDO menuDO : allMenuList) {
				menuDO.setIsDeleted(IsDeleted.YES.getValue());
				menuDao.updateById(menuDO);
			}
		}
	}
	
	/**
	 * 启用包括menuId 目录内的子目录
	 * @param menuId
	 */
	public void enableMenuById(Long menuId) {
		List<MenuDO> allMenuList = new ArrayList<MenuDO>();
		recursiveMenu(menuId, allMenuList);
		if (CollectionUtils.isNotEmpty(allMenuList)) {
			for (MenuDO menuDO : allMenuList) {
				menuDO.setIsDeleted(IsDeleted.NO.getValue());
				menuDao.updateById(menuDO);
			}
		}
	}
	
	/**
	 * 根据parentId查询下一级菜单
	 * @param parentId
	 * @return
	 */
	public List<MenuDO> selectByParentId(Long parentId) {
		return menuDao.selectByParentId(parentId);
	}
	
	/**
	 * 删除菜单包括子级菜单
	 * @param menuId
	 */
	public void deleteMenuById(Long menuId) {
		List<MenuDO> allMenuList = new ArrayList<MenuDO>();
		recursiveMenu(menuId, allMenuList);
		if (CollectionUtils.isNotEmpty(allMenuList)) {
			for (MenuDO menuDO : allMenuList) {
				menuDao.deleteById(menuDO.getMenuId());
			}
		}
	}
	
	/**
	 * 递归查询子目录
	 * @param menuId
	 * @param allMenuList
	 */
	private void recursiveMenu(Long menuId, List<MenuDO> allMenuList) {
		MenuDO menuDO = menuDao.selectById(menuId);
		allMenuList.add(menuDO);
		List<MenuDO> childMenuList = this.selectByParentId(menuId);
		if (CollectionUtils.isNotEmpty(childMenuList)) {
			for (MenuDO childMenuDO : childMenuList) {
				recursiveMenu(childMenuDO.getMenuId(), allMenuList);
			}
		}
	}

	public List<MenuDO> selectEnableMemu() {
		return menuDao.selectEnableMemu();
	}
}
