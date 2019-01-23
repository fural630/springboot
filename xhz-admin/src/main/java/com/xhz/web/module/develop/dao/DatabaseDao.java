package com.xhz.web.module.develop.dao;


import java.util.List;
import java.util.Map;

import com.xhz.web.module.develop.entity.DatabaseDO;
import com.xhz.web.module.develop.entity.DatabaseDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 数据源配置 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */
public interface DatabaseDao extends BaseMapper<DatabaseDO> {
	
	List<DatabaseDTO> selectDatabaseDTOPage(Map<String, Object> query);
	
	DatabaseDTO selectDatabaseDTOById(String id);
	
	List<DatabaseDTO> selectDatabaseDTOList();
}
