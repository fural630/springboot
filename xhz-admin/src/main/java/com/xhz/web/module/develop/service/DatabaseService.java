package com.xhz.web.module.develop.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.constant.Constant.DbType;
import com.xhz.util.CopyUtil;
import com.xhz.web.module.develop.dao.DatabaseDao;
import com.xhz.web.module.develop.entity.DatabaseDO;
import com.xhz.web.module.develop.entity.DatabaseDTO;

/**
 * <p>
 * 数据源配置 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
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
	
	public DatabaseDTO selectDatabaseDTOById(String id) {
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

	public void deleteById(String id) {
		databaseDao.deleteById(id);
	}

	public void deleteBatchIds(List<String> ids) {
		databaseDao.deleteBatchIds(ids);
	}

	public void updateById(DatabaseDO databaseDO) {
		databaseDao.updateById(databaseDO);
	}

	public DatabaseDO selectById(String id) {
		return databaseDao.selectById(id);
	}
	
	public List<DatabaseDO> selectList() {
		return databaseDao.selectList(null);
	}

	public boolean connectTest(String id) {
		DatabaseDO databaseDO = selectById(id);
		String driverName = null;
		if (databaseDO.getDbType().equals(DbType.Mysql.getValue())) {
			driverName = "com.mysql.jdbc.Driver";
		} else if (databaseDO.getDbType().equals(DbType.Oracle.getValue())) {
			driverName = "oracle.jdbc.driver.OracleDriver";
		}
		String username = databaseDO.getUserName();
		String password = databaseDO.getPassWord();
		String url = databaseDO.getUrl();
		Connection conn = null;
		boolean flag = false;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
            flag = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                	conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        databaseDO.setLastTestTime(new Date());
        databaseDao.updateById(databaseDO);
        return flag;
	}
}
