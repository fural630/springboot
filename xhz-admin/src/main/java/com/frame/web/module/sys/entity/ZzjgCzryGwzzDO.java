package com.frame.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("xt_zzjg_czry_gwzz")
public class ZzjgCzryGwzzDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CZRYID")
    private String czryid;

    @TableField("GWZZID")
    private String gwzzid;

    @TableField("BMID")
    private String bmid;

    public String getCzryid() {
        return czryid;
    }

    public void setCzryid(String czryid) {
        this.czryid = czryid;
    }
    public String getGwzzid() {
        return gwzzid;
    }

    public void setGwzzid(String gwzzid) {
        this.gwzzid = gwzzid;
    }
    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    @Override
    public String toString() {
        return "ZzjgCzryGwzzDO{" +
        "czryid=" + czryid +
        ", gwzzid=" + gwzzid +
        ", bmid=" + bmid +
        "}";
    }
}
