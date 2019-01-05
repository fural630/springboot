package com.frame.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.frame.validator.group.AddGroup;
import com.frame.validator.group.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 8192 kB
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-08
 */
@TableName("sys_user")
@ApiModel(value = "user对象", description = "用户对象")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value="用户名",name="username",example="xingguo")
    private String name;

    /**
     * 出生日期
     */
    private Date birthDay;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 性别 1-男、0-女、其他-未知
     */
    @ApiModelProperty(value="性别",name="sex",required=true)
    private Integer sex;

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
    private Long avatarId;

    /**
     * 部门ID
     */
    private Long departmentCid;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 状态：1-正常，0-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 修改人ID
     */
    private Long updateUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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
    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }
    public Long getDepartmentCid() {
        return departmentCid;
    }

    public void setDepartmentCid(Long departmentCid) {
        this.departmentCid = departmentCid;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "UserDO{" +
        "id=" + id +
        ", name=" + name +
        ", birthDay=" + birthDay +
        ", passWord=" + passWord +
        ", sex=" + sex +
        ", email=" + email +
        ", phone=" + phone +
        ", idCard=" + idCard +
        ", avatarId=" + avatarId +
        ", departmentCid=" + departmentCid +
        ", account=" + account +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        "}";
    }
}
