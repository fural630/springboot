package com.xhz.web.module.sys.dao;


import java.util.List;
import java.util.Map;

import com.xhz.web.module.sys.entity.RoleDO;
import com.xhz.web.module.sys.entity.RoleDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 角色管理 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-05
 */
public interface RoleDao extends BaseMapper<RoleDO> {
	
	List<RoleDTO> selectRoleDTOPage(Map<String, Object> query);
	
	RoleDTO selectRoleDTOById(String roleId);
	
	List<RoleDTO> selectRoleDTOList();

}
