package com.xhz.web.module.develop.entity.databasedoc;

import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.PropertyInfo;

public class CustomMySqlTypeConvert implements CustomITypeConvert {

	@Override
	public PropertyInfo processTypeConvert(String fieldType) {
		String t = fieldType.toLowerCase();
		if (t.contains("char") || t.contains("text")) {
			return DbColumnType.STRING;
		} else if (t.contains("bigint")) {
			return DbColumnType.LONG;
		} else if (t.contains("tinyint(1)")) {
			return DbColumnType.BOOLEAN;
		} else if (t.contains("int")) {
			return DbColumnType.INTEGER;
		} else if (t.contains("text")) {
			return DbColumnType.STRING;
		} else if (t.contains("bit")) {
			return DbColumnType.BOOLEAN;
		} else if (t.contains("decimal")) {
			return DbColumnType.BIG_DECIMAL;
		} else if (t.contains("clob")) {
			return DbColumnType.CLOB;
		} else if (t.contains("blob")) {
			return DbColumnType.BLOB;
		} else if (t.contains("binary")) {
			return DbColumnType.BYTE_ARRAY;
		} else if (t.contains("float")) {
			return DbColumnType.FLOAT;
		} else if (t.contains("double")) {
			return DbColumnType.DOUBLE;
		} else if (t.contains("json") || t.contains("enum")) {
			return DbColumnType.STRING;
		} else if (t.contains("date") || t.contains("time") || t.contains("year")) {
			return DbColumnType.DATE;
		}
		return DbColumnType.STRING;
	}
}
