package com.xhz;

import java.sql.ResultSet;

import java.sql.SQLException;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;

/**
 * 自定义mysql的查询实现
 * @author zhangzm
 *
 */
public class CustomDbMysqlQuery extends MySqlQuery implements IDbQuery {

	@Override
	public DbType dbType() {
		return super.dbType();
	}

	@Override
	public String tablesSql() {
		return super.tablesSql();
	}

	@Override
	public String tableFieldsSql() {
		return super.tableFieldsSql();
	}

	@Override
	public String tableName() {
		return super.tableName();
	}

	@Override
	public String tableComment() {
		return super.tableComment();
	}

	@Override
	public String fieldName() {
		return super.fieldName();
	}

	@Override
	public String fieldType() {
		return super.fieldType();
	}

	@Override
	public String fieldComment() {
		return super.fieldComment();
	}

	@Override
	public String fieldKey() {
		return super.fieldKey();
	}

	@Override
	public boolean isKeyIdentity(ResultSet results) throws SQLException {
		return super.isKeyIdentity(results);
	}

	@Override
	public String[] fieldCustom() {
		return new String[]{"Null"} ;
	}

	

}
