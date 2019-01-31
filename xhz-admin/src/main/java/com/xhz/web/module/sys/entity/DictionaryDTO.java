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
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>
 * 通用字典
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-30
 */
@ApiModel(value="DictionaryDTO对象", description="通用字典")
public class DictionaryDTO {

    @ApiModelProperty(value = "表ID")
    @NotNull(groups = { UpdateGroup.class }, message = "修改时表ID不能为空")
    private String id;

    @ApiModelProperty(value = "父级ID", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "父级ID不能为空")
    @CharLength(max = 40, groups = { AddGroup.class, UpdateGroup.class }, message = "父级ID最长度不允许超过40")
    private String parentId;

    @ApiModelProperty(value = "字典名称", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "字典名称不能为空")
    @CharLength(max = 50, groups = { AddGroup.class, UpdateGroup.class }, message = "字典名称最长度不允许超过50")
    private String name;

    @ApiModelProperty(value = "字典值", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "字典值不能为空")
    @CharLength(max = 255, groups = { AddGroup.class, UpdateGroup.class }, message = "字典值最长度不允许超过255")
    private String value;

    @ApiModelProperty(value = "字典类型", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "字典类型不能为空")
    private String type;

    @ApiModelProperty(value = "排序号", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "排序号不能为空")
    private String orderNum;

    @ApiModelProperty(value = "备注")
    @CharLength(max = 500, groups = { AddGroup.class, UpdateGroup.class }, message = "备注最长度不允许超过500")
    private String remark;

    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "创建时间不能为空")
    private Date createTime;

    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "修改时间不能为空")
    private Date updateTime;

    @ApiModelProperty(value = "删除标识", required = true)
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "删除标识不能为空")
    private String isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

}
