package com.xhz.web.module.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 操作日志记录表
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-31
 */
@TableName("SYS_RECORD_LOG")
public class RecordLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID")
    private String id;

    /**
     * 操作账号
     */
    @TableField("ACCOUNT")
    private String account;

    /**
     * 操作人用户名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 请求方法
     */
    @TableField("METHOD")
    private String method;

    /**
     * 操作信息
     */
    @TableField("OPERATION")
    private String operation;

    /**
     * 请求参数
     */
    @TableField("PARAMS")
    private String params;

    /**
     * 地址信息
     */
    @TableField("IP")
    private String ip;

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
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

    @Override
    public String toString() {
        return "RecordLogDO{" +
        "id=" + id +
        ", account=" + account +
        ", userName=" + userName +
        ", method=" + method +
        ", operation=" + operation +
        ", params=" + params +
        ", ip=" + ip +
        ", createDate=" + createDate +
        "}";
    }
}
