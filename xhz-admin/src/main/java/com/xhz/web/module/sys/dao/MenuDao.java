package com.xhz.web.module.sys.dao;


import java.util.List;
import java.util.Map;

import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.entity.MenuDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 菜单管理 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-16
 */
public interface MenuDao extends BaseMapper<MenuDO> {
	
	List<MenuDTO> selectMenuDTOPage(Map<String, Object> query);
	
	MenuDTO selectMenuDTOById(Long menuId);
	
	List<MenuDTO> selectMenuDTOList(Map<String, Object> params);

	List<MenuDO> selectByParentId(Long parentId);

	List<MenuDO> selectEnableMemu();
}
