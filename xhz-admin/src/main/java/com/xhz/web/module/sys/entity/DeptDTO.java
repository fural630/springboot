package com.xhz.web.module.sys.entity;

import javax.validation.constraints.NotNull;

import com.xhz.validator.CharLength;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 机构
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-25
 */
@ApiModel(value = "DeptDTO对象", description = "机构")
public class DeptDTO {

	@ApiModelProperty(value = "机构ID")
	@NotNull(groups = { UpdateGroup.class }, message = "修改时机构ID不能为空")
	private String deptId;

	@ApiModelProperty(value = "机构父ID")
	@CharLength(max = 40, groups = { AddGroup.class, UpdateGroup.class }, message = "机构父ID最长度不允许超过40")
	private String parentId;

	@ApiModelProperty(value = "机构名称")
	@CharLength(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "机构名称最长度不允许超过50")
	private String name;

	@ApiModelProperty(value = "排序号")
	@CharLength(max = 2, groups = { AddGroup.class, UpdateGroup.class }, message = "排序号最长度不允许超过2")
	private String orderNum;

	/**
	 * 上级机构名称
	 */
	private String parentDeptName;
	
	/**
	 * 机构编号
	 */
	private String code;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
