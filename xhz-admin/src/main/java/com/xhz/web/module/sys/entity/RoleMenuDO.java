package com.xhz.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 角色菜单
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-06
 */
@TableName("SYS_ROLE_MENU")
public class RoleMenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField("MENU_ID")
    private String menuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuDO{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", menuId=" + menuId +
        "}";
    }
}
