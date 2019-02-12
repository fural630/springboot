package com.xhz.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 错误日志
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-12
 */
@TableName("SYS_ERROR_LOG")
public class ErrorLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 请求方式
     */
    @TableField("METHOD")
    private String method;

    /**
     * 请求地址
     */
    @TableField("REQUEST_URL")
    private String requestUrl;

    /**
     * 错误详情
     */
    @TableField("MESSAGE")
    private String message;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
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

    @Override
    public String toString() {
        return "ErrorLogDO{" +
        "id=" + id +
        ", method=" + method +
        ", requestUrl=" + requestUrl +
        ", message=" + message +
        ", createTime=" + createTime +
        "}";
    }
}
