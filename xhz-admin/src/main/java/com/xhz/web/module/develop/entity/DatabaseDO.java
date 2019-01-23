package com.xhz.web.module.develop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 数据源配置
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */
@TableName("SYS_DATABASE")
public class DatabaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 数据源名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 链接地址
     */
    @TableField("URL")
    private String url;

    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @TableField("PASS_WORD")
    private String passWord;

    /**
     * 数据库类型
     */
    @TableField("DB_TYPE")
    private Double dbType;

    /**
     * 最近测试链接时间
     */
    @TableField("LAST_TEST_TIME")
    private Date lastTestTime;

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
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public Double getDbType() {
        return dbType;
    }

    public void setDbType(Double dbType) {
        this.dbType = dbType;
    }
    public Date getLastTestTime() {
        return lastTestTime;
    }

    public void setLastTestTime(Date lastTestTime) {
        this.lastTestTime = lastTestTime;
    }

    @Override
    public String toString() {
        return "DatabaseDO{" +
        "id=" + id +
        ", name=" + name +
        ", url=" + url +
        ", userName=" + userName +
        ", passWord=" + passWord +
        ", dbType=" + dbType +
        ", lastTestTime=" + lastTestTime +
        "}";
    }
}
