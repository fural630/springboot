package com.frame.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 8192 kB
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
@TableName("xt_region")
public class RegionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("REGION_ID")
    private String regionId;

    @TableField("REGION_NAME")
    private String regionName;

    @TableField("PARENT_ID")
    private String parentId;

    @TableField("TYPE")
    private Integer type;

    @TableField("AGENCY_CODE")
    private String agencyCode;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    @Override
    public String toString() {
        return "RegionDO{" +
        "regionId=" + regionId +
        ", regionName=" + regionName +
        ", parentId=" + parentId +
        ", type=" + type +
        ", agencyCode=" + agencyCode +
        "}";
    }
}
