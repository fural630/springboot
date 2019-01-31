package com.xhz.web.module.sys.entity;

import com.xhz.validator.CharLength;
import javax.validation.constraints.NotNull;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 操作日志记录表
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-31
 */
@ApiModel(value="RecordLogDTO对象", description="操作日志记录表")
public class RecordLogDTO {

    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时主键ID不能为空")
    private String id;

    @ApiModelProperty(value = "操作账号")
    @CharLength(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "操作账号最长度不允许超过50")
    private String account;

    @ApiModelProperty(value = "操作人用户名")
    @CharLength(max = 40, groups = { AddGroup.class, UpdateGroup.class }, message = "操作人用户名最长度不允许超过40")
    private String userName;

    @ApiModelProperty(value = "请求方法")
    @CharLength(max = 200, groups = { AddGroup.class, UpdateGroup.class }, message = "请求方法最长度不允许超过200")
    private String method;

    @ApiModelProperty(value = "操作信息")
    @CharLength(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "操作信息最长度不允许超过50")
    private String operation;

    @ApiModelProperty(value = "请求参数")
    @CharLength(max = 4000, groups = { AddGroup.class, UpdateGroup.class }, message = "请求参数最长度不允许超过4,000")
    private String params;

    @ApiModelProperty(value = "地址信息")
    @CharLength(max = 64, groups = { AddGroup.class, UpdateGroup.class }, message = "地址信息最长度不允许超过64")
    private String ip;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
