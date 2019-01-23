package com.xhz.web.module.sys.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */
@ApiModel(value="UserDTO对象", description="用户信息")
public class UserDTO {

    @ApiModelProperty(value = "用户ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时用户ID不能为空")
    private String id;

    @ApiModelProperty(value = "用户名")
    @Length(max = 20, groups = { AddGroup.class, UpdateGroup.class }, message = "用户名最长度不允许超过20")
    private String name;

    @ApiModelProperty(value = "用户账号")
    @Length(max = 127, groups = { AddGroup.class, UpdateGroup.class }, message = "用户账号最长度不允许超过127")
    private String account;

    @ApiModelProperty(value = "部门ID")
    @Length(max = 20, groups = { AddGroup.class, UpdateGroup.class }, message = "部门ID最长度不允许超过20")
    private String deptId;

    @ApiModelProperty(value = "出生日期")
    private Date birthDay;

    @ApiModelProperty(value = "密码")
    @Length(max = 25, groups = { AddGroup.class, UpdateGroup.class }, message = "密码最长度不允许超过25")
    private String passWord;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "邮箱")
    @Length(max = 30, groups = { AddGroup.class, UpdateGroup.class }, message = "邮箱最长度不允许超过30")
    private String email;

    @ApiModelProperty(value = "手机号")
    @Length(max = 15, groups = { AddGroup.class, UpdateGroup.class }, message = "手机号最长度不允许超过15")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    @Length(max = 10, groups = { AddGroup.class, UpdateGroup.class }, message = "身份证号最长度不允许超过10")
    private String idCard;

    @ApiModelProperty(value = "头像ID")
    @Length(max = 127, groups = { AddGroup.class, UpdateGroup.class }, message = "头像ID最长度不允许超过127")
    private String avatarId;

    @ApiModelProperty(value = "删除标识")
    private String isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人ID")
    @Length(max = 20, groups = { AddGroup.class, UpdateGroup.class }, message = "创建人ID最长度不允许超过20")
    private String createUserId;

    @ApiModelProperty(value = "修改人ID")
    @Length(max = 20, groups = { AddGroup.class, UpdateGroup.class }, message = "修改人ID最长度不允许超过20")
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

}
