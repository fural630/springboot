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
@TableName("xt_zzjg_czryqx")
public class CzryqxDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CZRY_ID")
    private String czryId;

    @TableField("JSID")
    private String jsid;

    public String getCzryId() {
        return czryId;
    }

    public void setCzryId(String czryId) {
        this.czryId = czryId;
    }
    public String getJsid() {
        return jsid;
    }

    public void setJsid(String jsid) {
        this.jsid = jsid;
    }

    @Override
    public String toString() {
        return "CzryqxDO{" +
        "czryId=" + czryId +
        ", jsid=" + jsid +
        "}";
    }
}
