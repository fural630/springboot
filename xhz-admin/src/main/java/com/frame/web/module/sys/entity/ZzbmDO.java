package com.frame.web.module.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * InnoDB free: 8192 kB
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
@TableName("xt_zzjg_zzbm")
public class ZzbmDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("ZZID")
	private String zzid;

	@TableField("QYID")
	private String qyid;

	@TableField("BMMC")
	private String bmmc;

	@TableField("SJID")
	private String sjid;

	@TableField("BMDM")
	private String bmdm;

	@TableField("BMLX")
	private Integer bmlx;

	@TableField("LXDZ")
	private String lxdz;

	@TableField("LXDH")
	private String lxdh;

	@TableField("BMJC")
	private String bmjc;

	@TableField("DWQZ")
	private String dwqz;

	@TableField("PYM")
	private String pym;

	@TableField("JKYH")
	private String jkyh;

	@TableField("JKYHZH")
	private String jkyhzh;

	@TableField("FYDW1")
	private String fydw1;

	@TableField("FYDW2")
	private String fydw2;

	@TableField("SSFY")
	private String ssfy;

	@TableField("DFBM")
	private String dfbm;

	@TableField("YB")
	private String yb;

	@TableField("SYBS")
	private Integer sybs;

	@TableField("SEQ")
	private Integer seq;


	/**
	 * 区域名称
	 */
	@TableField(exist = false)
	private String regionName;

	/**
	 * 区域类型
	 */
	@TableField(exist = false)
	private Integer type;

	/**
	 * 行政编号
	 */
	@TableField(exist = false)
	private String agencyCode;

	/**
	 * 上级单位名称
	 */
	@TableField(exist = false)
	private String sjdwmc;

	/**
	 * 上级单位代码
	 */
	@TableField(exist = false)
	private String sjdwdm;

	public String getZzid() {
		return zzid;
	}

	public void setZzid(String zzid) {
		this.zzid = zzid;
	}

	public String getQyid() {
		return qyid;
	}

	public void setQyid(String qyid) {
		this.qyid = qyid;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getSjid() {
		return sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public Integer getBmlx() {
		return bmlx;
	}

	public void setBmlx(Integer bmlx) {
		this.bmlx = bmlx;
	}

	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getBmjc() {
		return bmjc;
	}

	public void setBmjc(String bmjc) {
		this.bmjc = bmjc;
	}

	public String getDwqz() {
		return dwqz;
	}

	public void setDwqz(String dwqz) {
		this.dwqz = dwqz;
	}

	public String getPym() {
		return pym;
	}

	public void setPym(String pym) {
		this.pym = pym;
	}

	public String getJkyh() {
		return jkyh;
	}

	public void setJkyh(String jkyh) {
		this.jkyh = jkyh;
	}

	public String getJkyhzh() {
		return jkyhzh;
	}

	public void setJkyhzh(String jkyhzh) {
		this.jkyhzh = jkyhzh;
	}

	public String getFydw1() {
		return fydw1;
	}

	public void setFydw1(String fydw1) {
		this.fydw1 = fydw1;
	}

	public String getFydw2() {
		return fydw2;
	}

	public void setFydw2(String fydw2) {
		this.fydw2 = fydw2;
	}

	public String getSsfy() {
		return ssfy;
	}

	public void setSsfy(String ssfy) {
		this.ssfy = ssfy;
	}

	public String getDfbm() {
		return dfbm;
	}

	public void setDfbm(String dfbm) {
		this.dfbm = dfbm;
	}

	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}

	public Integer getSybs() {
		return sybs;
	}

	public void setSybs(Integer sybs) {
		this.sybs = sybs;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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

	public String getSjdwmc() {
		return sjdwmc;
	}

	public void setSjdwmc(String sjdwmc) {
		this.sjdwmc = sjdwmc;
	}

	public String getSjdwdm() {
		return sjdwdm;
	}

	public void setSjdwdm(String sjdwdm) {
		this.sjdwdm = sjdwdm;
	}

}
