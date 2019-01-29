package com.xhz.web.module.develop.entity;

import java.util.List;

import com.xhz.web.module.develop.entity.databasedoc.DatabaseTableDO;

public class DatabaseDocDTO {
	
	private DatabaseDO databaseDO;
	
	private List<DatabaseTableDO> databaseTableDOList;

	public DatabaseDO getDatabaseDO() {
		return databaseDO;
	}

	public void setDatabaseDO(DatabaseDO databaseDO) {
		this.databaseDO = databaseDO;
	}

	public List<DatabaseTableDO> getDatabaseTableDOList() {
		return databaseTableDOList;
	}

	public void setDatabaseTableDOList(List<DatabaseTableDO> databaseTableDOList) {
		this.databaseTableDOList = databaseTableDOList;
	}
}
