package com.xhz;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.baomidou.mybatisplus.generator.config.querys.OracleQuery;

/**
 * 自定义mysql的查询实现
 * 
 * @author zhangzm
 *
 */
public class CustomDbOracleQuery extends OracleQuery implements IDbQuery {

	@Override
	public DbType dbType() {
		return DbType.ORACLE;
	}

	@Override
	public String tablesSql() {
		return super.tablesSql();
	}

	@Override
	public String tableFieldsSql() {
		return "SELECT CASE WHEN A.DATA_TYPE = 'VARCHAR2' OR A .DATA_TYPE = 'CHAR' THEN A.DATA_LENGTH ELSE NULL END \"DataLength\", "
				+ "CASE WHEN A.NULLABLE = 'N' THEN 'NO' WHEN A.NULLABLE = 'Y' THEN 'YES' ELSE A.NULLABLE END \"Null\", "
				+ "A.COLUMN_NAME, CASE WHEN A.DATA_TYPE='NUMBER' THEN "
				+ "(CASE WHEN A.DATA_PRECISION IS NULL THEN A.DATA_TYPE "
				+ "WHEN NVL(A.DATA_SCALE, 0) > 0 THEN A.DATA_TYPE||'('||A.DATA_PRECISION||','||A.DATA_SCALE||')' "
				+ "ELSE A.DATA_TYPE||'('||A.DATA_PRECISION||')' END) "
				+ "ELSE A.DATA_TYPE END DATA_TYPE, B.COMMENTS,DECODE(C.POSITION, '1', 'PRI') KEY "
				+ "FROM ALL_TAB_COLUMNS A "
				+ " INNER JOIN ALL_COL_COMMENTS B ON A.TABLE_NAME = B.TABLE_NAME AND A.COLUMN_NAME = B.COLUMN_NAME AND B.OWNER = '#schema'"
				+ " LEFT JOIN ALL_CONSTRAINTS D ON D.TABLE_NAME = A.TABLE_NAME AND D.CONSTRAINT_TYPE = 'P' AND D.OWNER = '#schema'"
				+ " LEFT JOIN ALL_CONS_COLUMNS C ON C.CONSTRAINT_NAME = D.CONSTRAINT_NAME AND C.COLUMN_NAME=A.COLUMN_NAME AND C.OWNER = '#schema'"
				+ " WHERE A.OWNER = '#schema' AND A.TABLE_NAME = '%s' ORDER BY A.COLUMN_ID ";
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
		return new String[] { "Null", "DataLength" };
	}
}
