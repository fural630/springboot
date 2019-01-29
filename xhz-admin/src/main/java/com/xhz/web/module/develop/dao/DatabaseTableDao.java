package com.xhz.web.module.develop.dao;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhz.web.module.develop.entity.databasedoc.DatabaseTableDO;


/**
 * <p>
 * 数据库表实体 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-29
 */
public interface DatabaseTableDao extends BaseMapper<DatabaseTableDO> {

	void deleteByDatabaseId(String id);

	List<DatabaseTableDO> selectTableByDatabaseId(String id);
	
}
