package com.xhz.web.module.sys.dao;

import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.entity.MenuDTO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-11
 */
public interface MenuDao extends BaseMapper<MenuDO> {

	List<MenuDO> queryPage(Map<String, Object> query);

	List<MenuDTO> selectAllMenu();
	
	MenuDTO selectById(Long menuId);

}
