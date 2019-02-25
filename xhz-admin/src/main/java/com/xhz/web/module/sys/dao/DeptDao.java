package com.xhz.web.module.sys.dao;


import java.util.List;
import java.util.Map;

import com.xhz.web.module.sys.entity.DeptDO;
import com.xhz.web.module.sys.entity.DeptDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 机构 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-25
 */
public interface DeptDao extends BaseMapper<DeptDO> {
	
	List<DeptDTO> selectDeptDTOPage(Map<String, Object> query);
	
	DeptDTO selectDeptDTOById(String deptId);
	
	List<DeptDTO> selectDeptDTOList();

	String selectMaxCodeByParentId(String parentId);
}
