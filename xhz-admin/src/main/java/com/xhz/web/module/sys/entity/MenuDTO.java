package com.xhz.web.module.sys.entity;

import javax.validation.constraints.NotNull;

import com.xhz.validator.CharLength;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-25
 */
@ApiModel(value="MenuDTO对象", description="菜单管理")
public class MenuDTO {

    @ApiModelProperty(value = "菜单ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时菜单ID不能为空")
    private String menuId;

    @ApiModelProperty(value = "父菜单ID", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "父菜单ID不能为空")
    @CharLength(max = 40, groups = { AddGroup.class, UpdateGroup.class }, message = "父菜单ID最长度不允许超过40")
    private String parentId;

    @ApiModelProperty(value = "菜单名称", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "菜单名称不能为空")
    @CharLength(max = 20, groups = { AddGroup.class, UpdateGroup.class }, message = "菜单名称最长度不允许超过20")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    @CharLength(max = 255, groups = { AddGroup.class, UpdateGroup.class }, message = "菜单URL最长度不允许超过255")
    private String url;

    @ApiModelProperty(value = "授权标识")
    @CharLength(max = 255, groups = { AddGroup.class, UpdateGroup.class }, message = "授权标识最长度不允许超过255")
    private String perms;

    @ApiModelProperty(value = "菜单类型", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "菜单类型不能为空")
    private String type;

    @ApiModelProperty(value = "菜单图标")
    @CharLength(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "菜单图标最长度不允许超过30")
    private String icon;

    @ApiModelProperty(value = "排序号", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "排序号不能为空")
    private String orderNum;

    @ApiModelProperty(value = "删除标识", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "删除标识不能为空")
    private String isDeleted;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

}
