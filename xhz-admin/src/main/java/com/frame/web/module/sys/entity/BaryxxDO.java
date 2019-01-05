package com.frame.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
@TableName("bazx_bary_baryxx")
public class BaryxxDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ZFZH")
    private String zfzh;

    @TableField("ZJYXJZRQ")
    private Date zjyxjzrq;

    @TableField("ZJYXKSRQ")
    private Date zjyxksrq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getZfzh() {
        return zfzh;
    }

    public void setZfzh(String zfzh) {
        this.zfzh = zfzh;
    }
    public Date getZjyxjzrq() {
        return zjyxjzrq;
    }

    public void setZjyxjzrq(Date zjyxjzrq) {
        this.zjyxjzrq = zjyxjzrq;
    }
    public Date getZjyxksrq() {
        return zjyxksrq;
    }

    public void setZjyxksrq(Date zjyxksrq) {
        this.zjyxksrq = zjyxksrq;
    }

    @Override
    public String toString() {
        return "BaryxxDO{" +
        "id=" + id +
        ", zfzh=" + zfzh +
        ", zjyxjzrq=" + zjyxjzrq +
        ", zjyxksrq=" + zjyxksrq +
        "}";
    }
}
