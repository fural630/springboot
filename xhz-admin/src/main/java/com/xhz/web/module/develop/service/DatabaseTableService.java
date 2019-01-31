package com.xhz.web.module.develop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhz.web.module.develop.dao.DatabaseTableDao;
import com.xhz.web.module.develop.entity.databasedoc.DatabaseTableDO;

/**
 * <p>
 * 数据库表实体 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-29
 */
@Service
public class DatabaseTableService extends ServiceImpl<DatabaseTableDao, DatabaseTableDO>{

	@Autowired
	private DatabaseTableDao databaseTableDao;
	
	public void insert(DatabaseTableDO databaseTableDO) {
		databaseTableDao.insert(databaseTableDO);
	}

	public void deleteById(String tableId) {
		databaseTableDao.deleteById(tableId);
	}

	public void deleteBatchIds(List<String> tableIds) {
		databaseTableDao.deleteBatchIds(tableIds);
	}

	public DatabaseTableDO selectById(String tableId) {
		return databaseTableDao.selectById(tableId);
	}
	
	public List<DatabaseTableDO> selectList() {
		return databaseTableDao.selectList(null);
	}
	
	public void deleteByDatabaseId(String id) {
		databaseTableDao.deleteByDatabaseId(id);
	}

	public List<DatabaseTableDO> selectTableByDatabaseId(String id) {
		return databaseTableDao.selectTableByDatabaseId(id);
	}
}
