package com.xhz.web.module.sys.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.xhz.validator.CharLength;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 角色管理
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-05
 */
@ApiModel(value="RoleDTO对象", description="角色管理")
public class RoleDTO {

    @ApiModelProperty(value = "角色ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时角色ID不能为空")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    @CharLength(max = 100, groups = { AddGroup.class, UpdateGroup.class }, message = "角色名称最长度不允许超过100")
    private String roleName;

    @ApiModelProperty(value = "备注")
    @CharLength(max = 255, groups = { AddGroup.class, UpdateGroup.class }, message = "备注最长度不允许超过255")
    private String remark;
    
    /**
     * 菜单ID
     */
    private List<String> menuIdList;
    
    /**
     * 创建人名称
     */
    private String createUser;
    
    /**
     * 创建时间
     */
    private String createTime;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public List<String> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
