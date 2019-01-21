package com.xhz.web.module.sys.entity;

import javax.validation.constraints.NotNull;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 用户
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-21
 */
@ApiModel(value="UserDTO对象", description="用户")
public class UserDTO {

    @ApiModelProperty(value = "用户ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时用户ID不能为空")
    private Long id;

    @ApiModelProperty(value = "用户名")
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "用户名不能为空")
    @Length(max = 20, groups = { AddGroup.class, UpdateGroup.class }, message = "用户名最长度不允许超过20")
    private String name;

    @ApiModelProperty(value = "出生日期")
    private Date birthDay;

    @ApiModelProperty(value = "密码")
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "密码不能为空")
    @Length(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "密码最长度不允许超过50")
    private String passWord;

    @ApiModelProperty(value = "性别 1-男、0-女、其他-未知")
    private Integer sex;

    @ApiModelProperty(value = "邮箱")
    @Length(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "邮箱最长度不允许超过50")
    private String email;

    @ApiModelProperty(value = "手机号")
    @Length(max = 30, groups = { AddGroup.class, UpdateGroup.class }, message = "手机号最长度不允许超过30")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    @Length(max = 20, groups = { AddGroup.class, UpdateGroup.class }, message = "身份证号最长度不允许超过20")
    private String idCard;

    @ApiModelProperty(value = "头像ID")
    private Long avatarId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentCid;

    @ApiModelProperty(value = "用户账号")
    @Length(max = 40, groups = { AddGroup.class, UpdateGroup.class }, message = "用户账号最长度不允许超过40")
    private String account;

    @ApiModelProperty(value = "状态：1-正常，0-删除")
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "状态：1-正常，0-删除不能为空")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createUserId;

    @ApiModelProperty(value = "修改人ID")
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
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
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

}
