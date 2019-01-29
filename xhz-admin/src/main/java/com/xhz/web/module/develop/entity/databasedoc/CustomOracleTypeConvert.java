package com.xhz.web.module.develop.entity.databasedoc;

import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.PropertyInfo;

public class CustomOracleTypeConvert implements CustomITypeConvert {

	@Override
	public PropertyInfo processTypeConvert(String fieldType) {
		String t = fieldType.toLowerCase();
		if (t.contains("char")) {
			return DbColumnType.STRING;
		} else if (t.contains("date") || t.contains("timestamp")) {
			return DbColumnType.DATE;
		} else if (t.contains("number")) {
			if (t.matches("number\\(+\\d\\)")) {
				return DbColumnType.INTEGER;
			} else if (t.matches("number\\(+\\d{2}+\\)")) {
				return DbColumnType.LONG;
			}
			return DbColumnType.DOUBLE;
		} else if (t.contains("float")) {
			return DbColumnType.FLOAT;
		} else if (t.contains("clob")) {
			return DbColumnType.CLOB;
		} else if (t.contains("blob")) {
			return DbColumnType.BLOB;
		} else if (t.contains("binary")) {
			return DbColumnType.BYTE_ARRAY;
		} else if (t.contains("raw")) {
			return DbColumnType.BYTE_ARRAY;
		}
		return DbColumnType.STRING;
	}
}
