package com.xhz.web.module.develop.entity.databasedoc;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 数据库表实体
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-29
 */
@TableName("SYS_DATABASE_TABLE")
public class DatabaseTableDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    @TableId(value = "TABLE_ID", type = IdType.UUID)
    private String tableId;

    /**
     * 数据源ID
     */
    @TableField("DATABASE_ID")
    private String databaseId;

    /**
     * 表名
     */
    @TableField("NAME")
    private String name;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;
    
    @TableField(exist = false)
    private List<DatabaseTableFieldDO> databaseTableFieldDOList;
    
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DatabaseTableFieldDO> getDatabaseTableFieldDOList() {
		return databaseTableFieldDOList;
	}

	public void setDatabaseTableFieldDOList(List<DatabaseTableFieldDO> databaseTableFieldDOList) {
		this.databaseTableFieldDOList = databaseTableFieldDOList;
	}
	
}
