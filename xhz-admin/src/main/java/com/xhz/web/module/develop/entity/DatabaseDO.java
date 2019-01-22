package com.xhz.web.module.develop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 数据源管理
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-22
 */
@TableName("sys_database")
public class DatabaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 数据库类型
     */
    private Integer dbType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Integer getDbType() {
        return dbType;
    }

    public void setDbType(Integer dbType) {
        this.dbType = dbType;
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
        "}";
    }
}
