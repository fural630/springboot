package com.xhz.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 机构
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-25
 */
@TableName("SYS_DEPT")
public class DeptDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机构ID
     */
    @TableId(value = "DEPT_ID", type = IdType.UUID)
    private String deptId;

    /**
     * 机构父ID
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 机构名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 机构编号
     */
    @TableField("CODE")
    private String code;

    /**
     * 拼音码
     */
    @TableField("PYM")
    private String pym;

    /**
     * 排序号
     */
    @TableField("ORDER_NUM")
    private String orderNum;

    /**
     * 删除标识
     */
    @TableField("IS_DELETED")
    private String isDeleted;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getPym() {
        return pym;
    }

    public void setPym(String pym) {
        this.pym = pym;
    }
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "DeptDO{" +
        "deptId=" + deptId +
        ", parentId=" + parentId +
        ", name=" + name +
        ", code=" + code +
        ", pym=" + pym +
        ", orderNum=" + orderNum +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
