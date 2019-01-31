package com.xhz.web.module.develop.dao;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhz.web.module.develop.entity.databasedoc.DatabaseTableFieldDO;


/**
 * <p>
 * 字段表 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-29
 */
public interface DatabaseTableFieldDao extends BaseMapper<DatabaseTableFieldDO> {

	void deleteByDatabaseId(String id);

	List<DatabaseTableFieldDO> selectByTableId(String id);

	void saveBatch(List<DatabaseTableFieldDO> databaseTableFieldDOList);
	
}
