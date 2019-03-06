package com.xhz.web.module.sys.dao;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhz.web.module.sys.entity.RoleMenuDO;


/**
 * <p>
 * 角色菜单 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-06
 */
public interface RoleMenuDao extends BaseMapper<RoleMenuDO> {

	List<String> selectByRoleId(String roleId);

	void deleteByRoleId(String roleId);
	
}
