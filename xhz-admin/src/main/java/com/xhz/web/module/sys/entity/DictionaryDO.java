package com.xhz.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 通用字典
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-30
 */
@TableName("SYS_DICTIONARY")
public class DictionaryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 父级ID
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 字典名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 字典值
     */
    @TableField("VALUE")
    private String value;

    /**
     * 字典类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 排序号
     */
    @TableField("ORDER_NUM")
    private String orderNum;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableField("IS_DELETED")
    @TableLogic
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

    @Override
    public String toString() {
        return "DictionaryDO{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", name=" + name +
        ", value=" + value +
        ", type=" + type +
        ", orderNum=" + orderNum +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
