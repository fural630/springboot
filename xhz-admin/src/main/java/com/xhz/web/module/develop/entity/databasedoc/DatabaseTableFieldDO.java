package com.xhz.web.module.develop.entity.databasedoc;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 字段表
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-29
 */
@TableName("SYS_DATABASE_TABLE_FIELD")
public class DatabaseTableFieldDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表字段ID
     */
    @TableId(value = "FIELD_ID", type = IdType.UUID)
    private String fieldId;

    /**
     * 表ID
     */
    @TableField("TABLE_ID")
    private String tableId;

    /**
     * 数据源ID
     */
    @TableField("DATABASE_ID")
    private String databaseId;

    /**
     * 主键标识
     */
    @TableField("KEY_FLAG")
    private String keyFlag;

    /**
     * 字段名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 字段类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 属性名称
     */
    @TableField("PROPERTY_NAME")
    private String propertyName;

    /**
     * 属性类型
     */
    @TableField("PROPERTY_TYPE")
    private String propertyType;

    /**
     * 备注
     */
    @TableField("COMMENT")
    private String comment;

    /**
     * 是否为空
     */
    @TableField("NULLABLE")
    private String nullable;

    /**
     * 字段长度
     */
    @TableField("DATA_LENGTH")
    private Integer dataLength;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }
    public String getKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(String keyFlag) {
        this.keyFlag = keyFlag;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }
    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    @Override
    public String toString() {
        return "DatabaseTableFieldDO{" +
        "fieldId=" + fieldId +
        ", tableId=" + tableId +
        ", databaseId=" + databaseId +
        ", keyFlag=" + keyFlag +
        ", name=" + name +
        ", type=" + type +
        ", propertyName=" + propertyName +
        ", propertyType=" + propertyType +
        ", comment=" + comment +
        ", nullable=" + nullable +
        ", dataLength=" + dataLength +
        "}";
    }
}
