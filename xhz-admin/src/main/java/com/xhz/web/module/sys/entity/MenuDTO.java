package com.xhz.web.module.sys.entity;

import javax.validation.constraints.NotNull;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-21
 */
@ApiModel(value="MenuDTO对象", description="菜单管理")
public class MenuDTO {


    @ApiModelProperty(value = "菜单ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时菜单ID不能为空")
    private Long menuId;

    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "父菜单ID，一级菜单为0不能为空")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "菜单名称不能为空")
    @Length(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "菜单名称最长度不允许超过50")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "菜单URL不能为空")
    @Length(max = 200, groups = { AddGroup.class, UpdateGroup.class }, message = "菜单URL最长度不允许超过200")
    private String url;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    @Length(max = 500, groups = { AddGroup.class, UpdateGroup.class }, message = "授权(多个用逗号分隔，如：user:list,user:create)最长度不允许超过500")
    private String perms;

    @ApiModelProperty(value = "类型   0：目录   1：菜单   2：按钮")
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "类型   0：目录   1：菜单   2：按钮不能为空")
    private Integer type;

    @ApiModelProperty(value = "菜单图标")
    @Length(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "菜单图标最长度不允许超过50")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "1删除，0未删除")
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "1删除，0未删除不能为空")
    private Integer isDeleted;
    
    /**
     * 父级名称
     */
    private String parentName;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
