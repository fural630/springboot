package com.xhz.web.module.develop.entity;

import javax.validation.constraints.NotNull;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 数据源管理
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-22
 */
@ApiModel(value="DatabaseDTO对象", description="数据源管理")
public class DatabaseDTO {

    @ApiModelProperty(value = "数据源ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时数据源ID不能为空")
    private Integer id;

    @ApiModelProperty(value = "数据源名称", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "数据源名称不能为空")
    @Length(max = 20, groups = { AddGroup.class, UpdateGroup.class }, message = "数据源名称最长度不允许超过20")
    private String name;

    @ApiModelProperty(value = "链接地址", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "链接地址不能为空")
    @Length(max = 255, groups = { AddGroup.class, UpdateGroup.class }, message = "链接地址最长度不允许超过255")
    private String url;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "用户名不能为空")
    @Length(max = 100, groups = { AddGroup.class, UpdateGroup.class }, message = "用户名最长度不允许超过100")
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "密码不能为空")
    @Length(max = 100, groups = { AddGroup.class, UpdateGroup.class }, message = "密码最长度不允许超过100")
    private String passWord;

    @ApiModelProperty(value = "数据库类型", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "数据库类型不能为空")
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

}
