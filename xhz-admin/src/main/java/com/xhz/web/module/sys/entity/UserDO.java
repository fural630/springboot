package com.xhz.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */
@TableName("SYS_USER")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 用户名
     */
    @TableField("NAME")
    private String name;

    /**
     * 用户账号
     */
    @TableField("ACCOUNT")
    private String account;

    /**
     * 部门ID
     */
    @TableField("DEPT_ID")
    private String deptId;

    /**
     * 出生日期
     */
    @TableField("BIRTH_DAY")
    private Date birthDay;

    /**
     * 密码
     */
    @TableField("PASS_WORD")
    private String passWord;

    /**
     * 性别
     */
    @TableField("SEX")
    private String sex;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 手机号
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 身份证号
     */
    @TableField("ID_CARD")
    private String idCard;

    /**
     * 头像ID
     */
    @TableField("AVATAR_ID")
    private String avatarId;

    /**
     * 删除标识
     */
    @TableField("IS_DELETED")
    private String isDeleted;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;

    /**
     * 创建人ID
     */
    @TableField("CREATE_USER_ID")
    private String createUserId;

    /**
     * 修改人ID
     */
    @TableField("UPDATE_USER_ID")
    private String updateUserId;

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
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "UserDO{" +
        "id=" + id +
        ", name=" + name +
        ", account=" + account +
        ", deptId=" + deptId +
        ", birthDay=" + birthDay +
        ", passWord=" + passWord +
        ", sex=" + sex +
        ", email=" + email +
        ", phone=" + phone +
        ", idCard=" + idCard +
        ", avatarId=" + avatarId +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        "}";
    }
}
