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
@TableName("xt_zzjg_jsxx")
public class JsxxDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("JSID")
    private String jsid;

    @TableField("JSBM")
    private String jsbm;

    @TableField("JSMC")
    private String jsmc;

    @TableField("XYBS")
    private Integer xybs;

    @TableField("SSBM_ID")
    private String ssbmId;

    public String getJsid() {
        return jsid;
    }

    public void setJsid(String jsid) {
        this.jsid = jsid;
    }
    public String getJsbm() {
        return jsbm;
    }

    public void setJsbm(String jsbm) {
        this.jsbm = jsbm;
    }
    public String getJsmc() {
        return jsmc;
    }

    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }
    public Integer getXybs() {
        return xybs;
    }

    public void setXybs(Integer xybs) {
        this.xybs = xybs;
    }
    public String getSsbmId() {
        return ssbmId;
    }

    public void setSsbmId(String ssbmId) {
        this.ssbmId = ssbmId;
    }

    @Override
    public String toString() {
        return "JsxxDO{" +
        "jsid=" + jsid +
        ", jsbm=" + jsbm +
        ", jsmc=" + jsmc +
        ", xybs=" + xybs +
        ", ssbmId=" + ssbmId +
        "}";
    }
}
