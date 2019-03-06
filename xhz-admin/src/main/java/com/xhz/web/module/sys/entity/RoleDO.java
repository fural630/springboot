package com.xhz.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 角色管理
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-05
 */
@TableName("SYS_ROLE")
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID", type = IdType.UUID)
    private String roleId;

    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    private String roleName;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 创建人ID
     */
    @TableField("CREATE_USER_ID")
    private String createUserId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

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
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
        "roleId=" + roleId +
        ", roleName=" + roleName +
        ", remark=" + remark +
        ", createUserId=" + createUserId +
        ", createTime=" + createTime +
        "}";
    }
}
