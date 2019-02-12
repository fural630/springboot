package com.xhz.web.module.sys.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.xhz.validator.CharLength;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 错误日志
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-12
 */
@ApiModel(value = "ErrorLogDTO对象", description = "错误日志")
public class ErrorLogDTO {

    @ApiModelProperty(value = "ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时ID不能为空")
    private String id;

    @ApiModelProperty(value = "请求方式")
    @CharLength(max = 10, groups = { AddGroup.class, UpdateGroup.class }, message = "请求方式最长度不允许超过10")
    private String method;

    @ApiModelProperty(value = "请求地址")
    @CharLength(max = 255, groups = { AddGroup.class, UpdateGroup.class }, message = "请求地址最长度不允许超过255")
    private String requestUrl;

    @ApiModelProperty(value = "错误详情")
    @CharLength(max = 4000, groups = { AddGroup.class, UpdateGroup.class }, message = "错误详情最长度不允许超过4,000")
    private String message;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
