package com.xhz.web.module.develop.service;

import java.util.List;
import java.util.Map;


import com.xhz.web.module.develop.entity.DatabaseDO;
import com.xhz.web.module.develop.entity.DatabaseDTO;
import com.xhz.web.module.develop.dao.DatabaseDao;
import com.xhz.util.CopyUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 数据源管理 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-22
 */
@Service
public class DatabaseService {

	@Autowired
	private DatabaseDao databaseDao;
	
	public void insertDatabaseDTO(DatabaseDTO databaseDTO) {
		DatabaseDO databaseDO = CopyUtil.copyProperties(databaseDTO, DatabaseDO.class);
		databaseDao.insert(databaseDO);
	}
	
	public void updateDatabaseDTOById(DatabaseDTO databaseDTO) {
		DatabaseDO databaseDO = CopyUtil.copyProperties(databaseDTO, DatabaseDO.class);
		databaseDao.updateById(databaseDO);
	}
	
	public DatabaseDTO selectDatabaseDTOById(Integer id) {
		return databaseDao.selectDatabaseDTOById(id);
	}
	
	public List<DatabaseDTO> selectDatabaseDTOList() {
		return databaseDao.selectDatabaseDTOList();
	}
	
	public List<DatabaseDTO> selectDatabaseDTOPage(Map<String, Object> query) {
		return databaseDao.selectDatabaseDTOPage(query);
	}
	
	public void insert(DatabaseDO databaseDO) {
		databaseDao.insert(databaseDO);
	}

	public void deleteById(Integer id) {
		databaseDao.deleteById(id);
	}

	public void deleteBatchIds(List<Integer> ids) {
		databaseDao.deleteBatchIds(ids);
	}

	public void updateById(DatabaseDO databaseDO) {
		databaseDao.updateById(databaseDO);
	}

	public DatabaseDO selectById(Integer id) {
		return databaseDao.selectById(id);
	}
	
	public List<DatabaseDO> selectList() {
		return databaseDao.selectList(null);
	}
}
