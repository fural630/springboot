package com.xhz.web.module.develop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.web.module.develop.dao.DatabaseTableFieldDao;
import com.xhz.web.module.develop.entity.databasedoc.DatabaseTableFieldDO;

/**
 * <p>
 * 字段表 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-29
 */
@Service
public class DatabaseTableFieldService {

	@Autowired
	private DatabaseTableFieldDao databaseTableFieldDao;
	
	public void insert(DatabaseTableFieldDO databaseTableFieldDO) {
		databaseTableFieldDao.insert(databaseTableFieldDO);
	}

	public void deleteById(String fieldId) {
		databaseTableFieldDao.deleteById(fieldId);
	}

	public void deleteBatchIds(List<String> fieldIds) {
		databaseTableFieldDao.deleteBatchIds(fieldIds);
	}

	public void updateById(DatabaseTableFieldDO databaseTableFieldDO) {
		databaseTableFieldDao.updateById(databaseTableFieldDO);
	}

	public DatabaseTableFieldDO selectById(String fieldId) {
		return databaseTableFieldDao.selectById(fieldId);
	}
	
	public List<DatabaseTableFieldDO> selectList() {
		return databaseTableFieldDao.selectList(null);
	}

	public void deleteByDatabaseId(String id) {
		databaseTableFieldDao.deleteByDatabaseId(id);
		
	}

	public List<DatabaseTableFieldDO> selectTableFieldByTableId(String id) {
		return databaseTableFieldDao.selectByTableId(id);
	}
}
