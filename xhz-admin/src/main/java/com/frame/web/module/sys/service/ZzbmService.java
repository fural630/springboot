package com.frame.web.module.sys.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.util.ChineseToEnglish;
import com.frame.util.Query;
import com.frame.web.module.sys.dao.ZzbmDao;
import com.frame.web.module.sys.entity.ZzbmDO;

/**
 * <p>
 * InnoDB free: 8192 kB 服务实现类11
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
@Service
public class ZzbmService extends ServiceImpl<ZzbmDao, ZzbmDO> {
	
	// 最大代码
	private static final Integer MAX_DM = 100;
	// 最大个位数
	private static final Integer MAX_SINGLE_DIGIT = 9;
	// 代码截取长度
	private static final Integer DM_LENGTH = 2;
	
	@Autowired
	private ZzbmDao zzbmDao;

	public List<ZzbmDO> queryPage(Query query) {
		return zzbmDao.queryPage(query);
	}
	
	public boolean save(ZzbmDO zzbmDO) {
		zzbmDO.setZzid(UUID.randomUUID().toString().replace("-", ""));
		zzbmDO.setSybs(0);
		String sjid = zzbmDO.getSjid();
		String currentMaxZzbmDm = null;
		if (sjid.equals("0")) {
			currentMaxZzbmDm = "01";
		} else {
			currentMaxZzbmDm = getMaxZzbmDm(sjid);
		}
		zzbmDO.setBmdm(currentMaxZzbmDm);
		zzbmDO.setPym(ChineseToEnglish.getPinYinHeadChar(zzbmDO.getBmmc()));
		zzbmDao.insert(zzbmDO);
		return true;
	}
	
	/**
	 * 获取bmid下最大的组织部门编号
	 * 
	 * @param bmid
	 *            组织ID
	 * @return 最大组织编号
	 */
	private String getMaxZzbmDm(String bmid) {
		if (StringUtils.isEmpty(bmid)) {
			throw new RuntimeException("创建失败，参数组织ID不能为空！");
		}
		String currentMaxZzbmDm = zzbmDao.selectMaxZzbmDm(bmid);
		ZzbmDO zzbm = zzbmDao.selectById(bmid);
		String bmdm = zzbm.getBmdm();
		if (StringUtils.isEmpty(currentMaxZzbmDm)) {
			return bmdm + "01";
		} else {
			// 截取最后两位用于累加编号
			String lastSeq = currentMaxZzbmDm.substring(currentMaxZzbmDm.length() - DM_LENGTH,
					currentMaxZzbmDm.length());
			// 将编号加1
			Integer dmInteger = Integer.parseInt(lastSeq) + 1;
			// 不支持大于100的编号
			if (dmInteger >= MAX_DM) {
				throw new RuntimeException("创建失败，本节点已超过最大容纳" + MAX_DM + "个节点！");
			}
			// 编号为个位数时，要补充0在前面，变长度为2的编号
			if (dmInteger <= MAX_SINGLE_DIGIT) {
				return bmdm + "0" + String.valueOf(dmInteger);
			} else {
				return bmdm + String.valueOf(dmInteger);
			}
		}
	}
	
	public List<ZzbmDO> selectByParentId(String zzid) {
		return zzbmDao.selectByParentId(zzid);
	}
	
	public ZzbmDO getById(String zzid) {
		return zzbmDao.selectById(zzid);
	}

	public List<ZzbmDO> selectAll() {
		return zzbmDao.selectAll();
	}

}
