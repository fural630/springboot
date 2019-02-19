package com.xhz.web.module.develop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.config.rules.PropertyInfo;
import com.xhz.CustomDbMysqlQuery;
import com.xhz.CustomDbOracleQuery;
import com.xhz.constant.Constant.DbType;
import com.xhz.constant.Constant.YESNO;
import com.xhz.util.DatabaseUtil;
import com.xhz.util.RRException;
import com.xhz.web.module.develop.entity.DatabaseDO;
import com.xhz.web.module.develop.entity.DatabaseDocDTO;
import com.xhz.web.module.develop.entity.databasedoc.CustomITypeConvert;
import com.xhz.web.module.develop.entity.databasedoc.CustomMySqlTypeConvert;
import com.xhz.web.module.develop.entity.databasedoc.CustomOracleTypeConvert;
import com.xhz.web.module.develop.entity.databasedoc.DatabaseTableDO;
import com.xhz.web.module.develop.entity.databasedoc.DatabaseTableFieldDO;

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

	private static final Logger logger = LoggerFactory.getLogger(DatabaseDocService.class);

	@Autowired
	private DatabaseService databaseService;
	@Autowired
	private DatabaseTableService databaseTableService;
	@Autowired
	private DatabaseTableFieldService databaseTableFieldService;

	public DatabaseDocDTO selectDatabaseDocById(String id) {
		DatabaseDO databaseDO = databaseService.selectById(id);
		checkDatabaseConnect(databaseDO);
		List<DatabaseTableDO> databaseTableDOList = databaseTableService.selectTableByDatabaseId(id);
		if (CollectionUtils.isNotEmpty(databaseTableDOList)) {
			for (DatabaseTableDO databaseTableDO : databaseTableDOList) {
				String tableId = databaseTableDO.getTableId();
				List<DatabaseTableFieldDO> databaseTableFieldDOList = databaseTableFieldService
						.selectTableFieldByTableId(tableId);
				if (CollectionUtils.isNotEmpty(databaseTableFieldDOList)) {
					databaseTableDO.setDatabaseTableFieldDOList(databaseTableFieldDOList);
				}
			}
		} else {
			throw new RRException("未查询到数据，首次查询，请先拉取数据！");
		}
		DatabaseDocDTO databaseDocDTO = new DatabaseDocDTO();
		databaseDocDTO.setDatabaseDO(databaseDO);
		databaseDocDTO.setDatabaseTableDOList(databaseTableDOList);
		return databaseDocDTO;
	}

	public void pullDataByDatabaseId(String id) {
		DatabaseDO databaseDO = databaseService.selectById(id);
		checkDatabaseConnect(databaseDO);
		IDbQuery dbQuery = null;
		CustomITypeConvert typeConvert = null;
		if (databaseDO.getDbType().equals(DbType.Mysql.getValue())) {
			String schema = DatabaseUtil.getSchemaByUrl(databaseDO.getUrl());
			dbQuery = new CustomDbMysqlQuery(schema);
			typeConvert = new CustomMySqlTypeConvert();
		} else if (databaseDO.getDbType().equals(DbType.Oracle.getValue())) {
			dbQuery = new CustomDbOracleQuery();
			typeConvert = new CustomOracleTypeConvert();
		}
		Connection connection = getConn(databaseDO);
		PreparedStatement preparedStatement = null;
		List<DatabaseTableDO> databaseTableList = new ArrayList<DatabaseTableDO>();
		try {
			String tablesSql = dbQuery.tablesSql();
			if (databaseDO.getDbType().equals(DbType.Oracle.getValue())) {
				String schema = databaseDO.getUserName().toUpperCase();
				tablesSql = String.format(tablesSql, schema);
			}
			logger.info("tablesSql is {}", tablesSql);
			preparedStatement = connection.prepareStatement(tablesSql);
			ResultSet results = preparedStatement.executeQuery();
			DatabaseTableDO databaseTable;
			while (results.next()) {
				String tableName = results.getString(dbQuery.tableName());
				String comment = results.getString(dbQuery.tableComment());
				if (StringUtils.isNotEmpty(tableName)) {
					if (!excludeTableList(tableName)) {
						databaseTable = new DatabaseTableDO();
						databaseTable.setName(tableName);
						if (StringUtils.isNotEmpty(comment)) {
							databaseTable.setRemark(comment);
						}
						databaseTableList.add(databaseTable);
					}
				} else {
					logger.error("当前数据库为空！！！");
				}
			}
			if (CollectionUtils.isNotEmpty(databaseTableList)) {
				removeAllTableByDatabaseId(id);
				for (DatabaseTableDO databaseTableDO : databaseTableList) {
					databaseTableDO.setDatabaseId(id);
					databaseTableService.insert(databaseTableDO);
				}
				convertTableFields(databaseTableList, dbQuery, databaseDO, connection, typeConvert);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void removeAllTableByDatabaseId(String id) {
		databaseTableService.deleteByDatabaseId(id);
		databaseTableFieldService.deleteByDatabaseId(id);
	}

	private void convertTableFields(List<DatabaseTableDO> dataBaseTableList, IDbQuery dbQuery, DatabaseDO databaseDO,
			Connection connection, CustomITypeConvert typeConvert) {
		for (DatabaseTableDO databaseTable : dataBaseTableList) {
			boolean haveId = false;
			try {
				String tableFieldsSql = dbQuery.tableFieldsSql();
				if (com.baomidou.mybatisplus.annotation.DbType.ORACLE == dbQuery.dbType()) {
					tableFieldsSql = String.format(
							tableFieldsSql.replace("#schema", databaseDO.getUserName().toUpperCase()),
							databaseTable.getName());
				} else {
					tableFieldsSql = String.format(tableFieldsSql, databaseTable.getName());
				}
				logger.info("tableFieldsSql : {}", tableFieldsSql);
				PreparedStatement preparedStatement = connection.prepareStatement(tableFieldsSql);
				ResultSet results = preparedStatement.executeQuery();
				while (results.next()) {
					DatabaseTableFieldDO field = new DatabaseTableFieldDO();
					field.setTableId(databaseTable.getTableId());
					field.setDatabaseId(databaseTable.getDatabaseId());
					String key = results.getString(dbQuery.fieldKey());
					boolean isId = StringUtils.isNotEmpty(key) && "PRI".equals(key.toUpperCase());
					if (isId && !haveId) {
						field.setKeyFlag(YESNO.YES.getValue());
						haveId = true;
					} else {
						field.setKeyFlag(YESNO.NO.getValue());
					}
					String nullable = results.getString("Null").equals("NO") ? YESNO.NO.getValue()
							: YESNO.YES.getValue();
					field.setNullable(nullable);
					String dataLength = results.getString("DataLength");
					if (StringUtils.isNotEmpty(dataLength)) {
						field.setDataLength(Integer.parseInt(dataLength));
					}
					field.setName(results.getString(dbQuery.fieldName()));
					field.setType(results.getString(dbQuery.fieldType()));
					field.setPropertyName(processPropertyName(field.getName()));
					PropertyInfo propertyInfo = processPropertyType(typeConvert, field.getType());
					field.setPropertyType(propertyInfo.getType());
					field.setRemark(results.getString(dbQuery.fieldComment()));
					databaseTableFieldService.insert(field);
				}
			} catch (Exception e) {
				logger.error("Exception： {}", e.getMessage());
			}
		}
	}

	private PropertyInfo processPropertyType(CustomITypeConvert typeConvert, String fieldType) {
		return typeConvert.processTypeConvert(fieldType);
	}

	private String processPropertyName(String name) {
		return NamingStrategy.underlineToCamel(name);
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
		return DatabaseUtil.getConn(databaseDO.getUrl(), databaseDO.getUserName(), databaseDO.getPassWord(),
				driverName);
	}

	private boolean excludeTableList(String tableName) {
		String[] excludeTable = new String[] { "SYS_DATABASE", "SYS_DATABASE_TABLE", "SYS_DATABASE_TABLE_FIELD" };
		for (String s : excludeTable) {
			if (s.equalsIgnoreCase(tableName))
				return true;
		}
		return false;
	}
}
