package com.xhz.web.module.sys.entity;

import java.util.Date;
import java.util.List;

/**
 * 当前登录用户的实体
 * 
 * @author zhangzm
 *
 */
public class LoginUser {

	/**
	 * 用户ID
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 用户账号
	 */
	private String userName;

	/**
	 * 部门ID
	 */
	private String deptId;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 出生日期
	 */
	private Date birthDay;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 身份证号
	 */
	private String idCard;

	/**
	 * 头像ID
	 */
	private String avatarId;

	/**
	 * 删除标识
	 */
	private String isDeleted;

	/**
	 * 登录用户拥有的角色
	 */
	private List<String> roleIdList;

	/**
	 * 登录用户拥有的权限
	 */
	private List<String> permsList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<String> getPermsList() {
		return permsList;
	}

	public void setPermsList(List<String> permsList) {
		this.permsList = permsList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
