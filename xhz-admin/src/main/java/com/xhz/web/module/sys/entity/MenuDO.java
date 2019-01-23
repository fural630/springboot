package com.xhz.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */
@TableName("SYS_MENU")
public class MenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "MENU_ID", type = IdType.UUID)
    private String menuId;

    /**
     * 父菜单ID
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 菜单名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 菜单URL
     */
    @TableField("URL")
    private String url;

    /**
     * 授权标识
     */
    @TableField("PERMS")
    private String perms;

    /**
     * 菜单类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 菜单图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 排序号
     */
    @TableField("ORDER_NUM")
    private String orderNum;

    /**
     * 删除标识
     */
    @TableField("IS_DELETED")
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

    @Override
    public String toString() {
        return "MenuDO{" +
        "menuId=" + menuId +
        ", parentId=" + parentId +
        ", name=" + name +
        ", url=" + url +
        ", perms=" + perms +
        ", type=" + type +
        ", icon=" + icon +
        ", orderNum=" + orderNum +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
