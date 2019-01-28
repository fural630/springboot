package com.xhz.web.module.develop.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.xhz.CustomDbMysqlQuery;
import com.xhz.CustomDbOracleQuery;
import com.xhz.constant.Constant.DbType;
import com.xhz.util.DatabaseUtil;
import com.xhz.util.RRException;
import com.xhz.web.module.develop.entity.DatabaseDO;

/**
 * <p>
 * 数据库文档 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */
@Service
public class DatabaseDocService {

	@Autowired
	private DatabaseService databaseService;

	public void selectDatabaseDocById(String id) {
		DatabaseDO databaseDO = databaseService.selectById(id);
		checkDatabaseConnect(databaseDO);
		IDbQuery dbQuery = null;
		if (databaseDO.getDbType().equals(DbType.Mysql.getValue())) {
			String schema = DatabaseUtil.getSchemaByUrl(databaseDO.getUrl());
			dbQuery = new CustomDbMysqlQuery(schema);
		} else if (databaseDO.getDbType().equals(DbType.Oracle.getValue())) {
			dbQuery = new CustomDbOracleQuery();
		}
		Connection connection = getConn(databaseDO);
		PreparedStatement preparedStatement = null;
		try {
			String tablesSql = dbQuery.tablesSql();
			if (databaseDO.getDbType().equals(DbType.Oracle.getValue())) {
				String schema = databaseDO.getUserName().toUpperCase();
				tablesSql = String.format(tablesSql, schema);
			}
			preparedStatement = connection.prepareStatement(tablesSql);
			ResultSet results = preparedStatement.executeQuery();
			while (results.next()) {
				String tableName = results.getString(dbQuery.tableName());
				System.out.println(tableName);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	/**
	 * 验证数据源是否存在，以及是否可链接
	 * 
	 * @param databaseDO
	 */
	private void checkDatabaseConnect(DatabaseDO databaseDO) {
		if (null == databaseDO) {
			throw new RRException("找不到数据源，请确认数据源是否被删除！");
		} else {
			boolean flag = databaseService.connectTest(databaseDO.getId());
			if (!flag) {
				throw new RRException("获取失败，链接不到数据源，请确认数据源配置是否正确或网络是否正常！");
			}
		}
	}

	
	private Connection getConn(DatabaseDO databaseDO) {
		if (null == databaseDO) {
			return null;
		}
		String driverName = null;
		if (databaseDO.getDbType().equals(DbType.Mysql.getValue())) {
			driverName = "com.mysql.jdbc.Driver";
		} else if (databaseDO.getDbType().equals(DbType.Oracle.getValue())) {
			driverName = "oracle.jdbc.driver.OracleDriver";
		}
		return DatabaseUtil.getConn(databaseDO.getUrl(), databaseDO.getUserName(), databaseDO.getPassWord(), driverName);
	}
}
