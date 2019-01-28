package com.xhz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

public class DatabaseUtil {
	
	/**
	 * mysql时截取出数据库名称
	 * @param url
	 * @return 数据库名称
	 */
	public static String getSchemaByUrl(String url) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}
		String schema = url.contains("?") ? url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("?"))
				: url.substring(url.lastIndexOf("/") + 1, url.length());
		return schema;
	}
	
	/**
	 * 获取数据库链接
	 * @param url
	 * @param username
	 * @param password
	 * @param driverName
	 * @return
	 */
	public static Connection getConn(String url, String username, String password, String driverName) {
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
