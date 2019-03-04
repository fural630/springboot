package com.xhz.constant;

/**
 * 
 * @author zhangzm
 *
 */
public class Constant {

	/**
	 * 超级管理员ID
	 */
	public static final String SUPER_ADMIN = "1";

	/**
	 * 通用成功code
	 */
	public static final int SUCCESS_CODE = 200;

	/**
	 * 错误提示模板
	 */
	public static final String ERROR_TOOLTIP = "此操作发生未知异常,请联系管理员！<br/>错误代码: %s";

	/**
	 * 未授权提示模板
	 */
	public static final String UNAUTHORIZED_TOOLTIP = "您没有此操作权限，如有需要请联系管理员！";

	/**
	 * session超时提示模板
	 */
	public static final String SESSION_TIMEOUT_TOOLTIP = "登录超时，点击确定重新进行登录！";

	/**
	 * 当前登录用户
	 */
	public static final String CURRENT_USER = "curUser";
	
	/**
     * 权限前缀
     */
    public static final String PERMS_LIST = "permsList_";
    

	/**
	 * 菜单类型
	 */
	public enum MenuType {
		/**
		 * 目录
		 */
		CATALOG("0"),
		/**
		 * 菜单
		 */
		MENU("1"),
		/**
		 * 按钮
		 */
		BUTTON("2");

		private String value;

		private MenuType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 通用是否删除
	 * 
	 * @author zhangzm
	 *
	 */
	public enum IsDeleted {
		/**
		 * 已删除
		 */
		YES("1"),

		/*
		 * 未删除
		 */
		NO("0");

		private String value;

		private IsDeleted(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 数据库类型
	 * 
	 * @author zhangzm
	 *
	 */
	public enum DbType {

		Mysql("0"),

		Oracle("1");

		private String value;

		private DbType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 是否枚举
	 * 
	 * @author zhangzm
	 *
	 */
	public enum YESNO {
		YES("1"),

		NO("0");

		private String value;

		private YESNO(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

}
