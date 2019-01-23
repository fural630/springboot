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
    public static final int SUPER_ADMIN = 1;
    
    /**
     * 通用成功code
     */
    public static final int SUCCESS_CODE = 200;
    
    
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

}
