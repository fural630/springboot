package com.frame.web.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

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
@TableName("xt_zzjg_czry")
public class CzryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CZRY_ID")
    private String czryId;

    @TableField("CZRY_DM")
    private String czryDm;

    @TableField("CZRY_MC")
    private String czryMc;

    @TableField("CZRY_PASS")
    private String czryPass;

    @TableField("SZBM_ID")
    private String szbmId;

    @TableField("EMAIL")
    private String email;

    @TableField("SJHM")
    private String sjhm;

    @TableField("XBDM")
    private Integer xbdm;

    @TableField("JG")
    private String jg;

    @TableField("MZ")
    private String mz;

    @TableField("SFZHM")
    private String sfzhm;

    @TableField("LZYG")
    private Integer lzyg;

    @TableField("YGTX")
    private String ygtx;

    @TableField("CZRY_ZH")
    private String czryZh;

    @TableField("XZRQ")
    private Date xzrq;

    @TableField("QM")
    private String qm;

    @TableField("PYM")
    private String pym;
    
    @TableField(exist = false)
    private String gwzzid;
    
    @TableField(exist = false)
    private String zfzh;

    @TableField(exist = false)
    private Date zjyxjzrq;

    @TableField(exist = false)
    private Date zjyxksrq;
    
    /**
	 * 所在单位名称
	 */
    @TableField(exist = false)
	private String dwmc;

	/**
	 * 所在单位编号
	 */
    @TableField(exist = false)
	private String dwdm;

	/**
	 * 所在单位ID
	 */
    @TableField(exist = false)
	private String dwid;

	/**
	 * 所在部门名称
	 */
    @TableField(exist = false)
	private String bmmc;

	/**
	 * 所在部门代码
	 */
	@TableField(exist = false)
	private String bmdm;
	
	/**
	 * 岗位职责名称
	 */
	@TableField(exist = false)
	private String gwzzmc;
	
	@TableField(exist = false)
	private List<String> jsIdList;

    public String getCzryId() {
        return czryId;
    }

    public void setCzryId(String czryId) {
        this.czryId = czryId;
    }
    public String getCzryDm() {
        return czryDm;
    }

    public void setCzryDm(String czryDm) {
        this.czryDm = czryDm;
    }
    public String getCzryMc() {
        return czryMc;
    }

    public void setCzryMc(String czryMc) {
        this.czryMc = czryMc;
    }
    public String getCzryPass() {
        return czryPass;
    }

    public void setCzryPass(String czryPass) {
        this.czryPass = czryPass;
    }
    public String getSzbmId() {
        return szbmId;
    }

    public void setSzbmId(String szbmId) {
        this.szbmId = szbmId;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }
    public Integer getXbdm() {
        return xbdm;
    }

    public void setXbdm(Integer xbdm) {
        this.xbdm = xbdm;
    }
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }
    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }
    public Integer getLzyg() {
        return lzyg;
    }

    public void setLzyg(Integer lzyg) {
        this.lzyg = lzyg;
    }
    public String getYgtx() {
        return ygtx;
    }

    public void setYgtx(String ygtx) {
        this.ygtx = ygtx;
    }
    public String getCzryZh() {
        return czryZh;
    }

    public void setCzryZh(String czryZh) {
        this.czryZh = czryZh;
    }
    public Date getXzrq() {
        return xzrq;
    }

    public void setXzrq(Date xzrq) {
        this.xzrq = xzrq;
    }
    public String getQm() {
        return qm;
    }

    public void setQm(String qm) {
        this.qm = qm;
    }
    public String getPym() {
        return pym;
    }

    public void setPym(String pym) {
        this.pym = pym;
    }

    public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getDwdm() {
		return dwdm;
	}

	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}

	public String getDwid() {
		return dwid;
	}

	public void setDwid(String dwid) {
		this.dwid = dwid;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	
	public String getGwzzid() {
		return gwzzid;
	}

	public void setGwzzid(String gwzzid) {
		this.gwzzid = gwzzid;
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
        return "CzryDO{" +
        "czryId=" + czryId +
        ", czryDm=" + czryDm +
        ", czryMc=" + czryMc +
        ", czryPass=" + czryPass +
        ", szbmId=" + szbmId +
        ", email=" + email +
        ", sjhm=" + sjhm +
        ", xbdm=" + xbdm +
        ", jg=" + jg +
        ", mz=" + mz +
        ", sfzhm=" + sfzhm +
        ", lzyg=" + lzyg +
        ", ygtx=" + ygtx +
        ", czryZh=" + czryZh +
        ", xzrq=" + xzrq +
        ", qm=" + qm +
        ", pym=" + pym +
        "}";
    }

	public List<String> getJsIdList() {
		return jsIdList;
	}

	public void setJsIdList(List<String> jsIdList) {
		this.jsIdList = jsIdList;
	}

	public String getGwzzmc() {
		return gwzzmc;
	}

	public void setGwzzmc(String gwzzmc) {
		this.gwzzmc = gwzzmc;
	}
	
}
