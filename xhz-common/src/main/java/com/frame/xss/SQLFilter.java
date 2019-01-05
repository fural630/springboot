package com.frame.xss;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL注入过滤
 * @author zhangzm
 *
 */

public class SQLFilter {
	
	public static String sqlInject(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		
		// 替换一些符号
		str = StringUtils.replace(str, "'", "");
		str = StringUtils.replace(str, "\"", "");
		str = StringUtils.replace(str, ";", "");
		str = StringUtils.replace(str, "\\", "");
		
		// 转换成小写
		str = str.toLowerCase();
		
		// 非法字符
		String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};
		
		//判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new IllegalStateException("包含非法字符");
            }
        }
        return str;
	}

}
