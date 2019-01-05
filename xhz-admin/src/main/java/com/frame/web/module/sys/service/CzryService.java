package com.frame.web.module.sys.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.util.ChineseToEnglish;
import com.frame.util.Md5Encryption;
import com.frame.util.Query;
import com.frame.web.module.sys.dao.CzryDao;
import com.frame.web.module.sys.entity.BaryxxDO;
import com.frame.web.module.sys.entity.CzryDO;
import com.frame.web.module.sys.entity.CzryqxDO;
import com.frame.web.module.sys.entity.ZzbmDO;
import com.frame.web.module.sys.entity.ZzjgCzryGwzzDO;

/**
 * <p>
 * InnoDB free: 8192 kB 服务实现类11
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
@Service
public class CzryService extends ServiceImpl<CzryDao, CzryDO> {
	
	// 最大代码
	private static final Integer MAX_DM = 100;
	// 最大个位数
	private static final Integer MAX_SINGLE_DIGIT = 9;
	// 代码截取长度
	private static final Integer DM_LENGTH = 2;
	
	@Autowired
	private CzryDao czryDao;
	
	@Autowired
	private CzryqxService czryqxService;
	
	@Autowired
	private ZzjgCzryGwzzService zzjgCzryGwzzService;
	
	@Autowired
	private BaryxxService baryxxService;
	
	@Autowired
	private ZzbmService zzbmService;

	public List<CzryDO> queryPage(Query query) {
		return czryDao.queryPage(query);
	}
	
	public CzryDO getById(String id) {
		CzryDO czryDO = czryDao.getById(id);
		List<String> jsIdList = czryqxService.getCzryQx(id);
		czryDO.setJsIdList(jsIdList);
		return czryDO;
	}
	
	@Transactional
	public boolean updateById(CzryDO czryDO) {
		String czryId = czryDO.getCzryId();
		if (StringUtils.isNoneBlank(czryDO.getGwzzid())) {
			String dwId = czryDO.getDwid();
			zzjgCzryGwzzService.updateByCzryIdDwId(czryId, dwId, czryDO.getGwzzid());
		}
		czryqxService.removeByCzryId(czryId);
		if (CollectionUtils.isNotEmpty(czryDO.getJsIdList())) {
			for (String jsId : czryDO.getJsIdList()) {
				CzryqxDO czryqxDO = new CzryqxDO();
				czryqxDO.setCzryId(czryId);
				czryqxDO.setJsid(jsId);
				czryqxService.save(czryqxDO);
				if (jsId.equals("2fcb67cdfb9f416b8aff4941754a3f18")) {
					BaryxxDO baryxxDO = new BaryxxDO();
					baryxxDO.setId(czryId);
					baryxxDO.setZfzh(czryDO.getZfzh());
					baryxxDO.setZjyxksrq(czryDO.getZjyxksrq());
					baryxxDO.setZjyxjzrq(czryDO.getZjyxjzrq());
					baryxxService.updateById(baryxxDO);
				}
			}
		}
		czryDao.updateById(czryDO);
		return true;
	}
	
	@Transactional
	public void insert(CzryDO czryDO) {
		czryDO.setCzryId(UUID.randomUUID().toString().replace("-", ""));
		czryDO.setPym(ChineseToEnglish.getPinYinHeadChar(czryDO.getCzryMc()));
		
		String bmid = czryDO.getSzbmId();
		if (StringUtils.isEmpty(bmid)) {
			throw new RuntimeException("部门ID不能为空！");
		}
		String czryDm = getNewCzryDm(bmid);
		czryDO.setCzryDm(czryDm);
		Md5Encryption md5 = new Md5Encryption();
		try {
			String password = md5.Md5("123456");
			czryDO.setCzryPass(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		czryDao.insert(czryDO);
		String czryId = czryDO.getCzryId();
		List<String> jsIdList = czryDO.getJsIdList();
		if (CollectionUtils.isNotEmpty(jsIdList)) {
			for (String jsId : jsIdList) {
				CzryqxDO czryqxDO = new CzryqxDO();
				czryqxDO.setCzryId(czryId);
				czryqxDO.setJsid(jsId);
				czryqxService.save(czryqxDO);
				if (jsId.equals("2fcb67cdfb9f416b8aff4941754a3f18")) {
					BaryxxDO baryxxDO = new BaryxxDO();
					baryxxDO.setId(czryId);
					baryxxDO.setZfzh(czryDO.getZfzh());
					baryxxDO.setZjyxksrq(czryDO.getZjyxksrq());
					baryxxDO.setZjyxjzrq(czryDO.getZjyxjzrq());
					baryxxService.save(baryxxDO);
				}
			}
		}
		if (StringUtils.isNoneBlank(czryDO.getGwzzid())) {
			ZzjgCzryGwzzDO zzjgCzryGwzzDO = new ZzjgCzryGwzzDO();
			zzjgCzryGwzzDO.setCzryid(czryId);
			zzjgCzryGwzzDO.setBmid(czryDO.getDwid());
			zzjgCzryGwzzDO.setGwzzid(czryDO.getGwzzid());
			zzjgCzryGwzzService.save(zzjgCzryGwzzDO);
		}
	}
	
	/**
	 * 检查账号唯一性
	 * 
	 * @param czry
	 *            操作人员实体
	 */
	public int isUniqueAccoumt(CzryDO czry) {
		String czryId = czry.getCzryId();
		String czryZh = czry.getCzryZh();
		if (StringUtils.isEmpty(czryZh)) {
			throw new RuntimeException("账号不能为空！");
		}
		// 如果是修改，可以将账号修改为原来的账号
		String myCzryZh = null;
		if (StringUtils.isNoneEmpty(czryId)) { // 修改情况
			CzryDO oldCzry = czryDao.getById(czryId);
			myCzryZh = oldCzry.getCzryZh();
		}
		int count = czryDao.acountUnique(czryZh, myCzryZh);
		return count;
	}

	private String getNewCzryDm(String bmid) {
		if (StringUtils.isEmpty(bmid)) {
			throw new RuntimeException("创建失败，参数部门ID不能为空！");
		}
		// 获取部门中最大的人员编号
		String currentMaxCzryDm = czryDao.selectMaxBmCzryDm(bmid);
		ZzbmDO zzbm = zzbmService.getById(bmid);
		// 获取要添加人员所在的部门编号
		String bmdm = zzbm.getBmdm();
		// 如果该部门下暂无任何人，则设置初始编号
		if (StringUtils.isEmpty(currentMaxCzryDm)) {
			return bmdm + "01";
		} else {
			// 截取最后两位用于累加编号
			String lastSeq = currentMaxCzryDm.substring(currentMaxCzryDm.length() - DM_LENGTH,
					currentMaxCzryDm.length());
			// 将编号加1
			Integer dmInteger = Integer.parseInt(lastSeq) + 1;
			// 不支持大于100的编号
			if (dmInteger >= MAX_DM) {
				throw new RuntimeException("创建失败，本部门已超过最大容纳" + MAX_DM + "人！");
			}
			// 编号为个位数时，要补充0在前面，变长度为2的编号
			if (dmInteger <= MAX_SINGLE_DIGIT) {
				return bmdm + "0" + String.valueOf(dmInteger);
			} else {
				return bmdm + String.valueOf(dmInteger);
			}
		}
	}

}
