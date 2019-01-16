package com.xhz.web.module.sys.service;

import java.util.List;
import java.util.Map;


import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.entity.MenuDTO;
import com.xhz.web.module.sys.dao.MenuDao;
import com.xhz.util.CopyUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
}
