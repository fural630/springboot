package com.frame.web.module.sys.service;

import com.frame.web.module.sys.entity.ZzjgCzryGwzzDO;
import com.sun.tracing.dtrace.ProviderAttributes;
import com.frame.util.Query;
import com.frame.web.module.sys.dao.ZzjgCzryGwzzDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 8192 kB 服务实现类11
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
@Service
public class ZzjgCzryGwzzService extends ServiceImpl<ZzjgCzryGwzzDao, ZzjgCzryGwzzDO> {
	
	@Autowired
	private ZzjgCzryGwzzDao zzjgCzryGwzzDao;

	public List<ZzjgCzryGwzzDO> queryPage(Query query) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateByCzryIdDwId(String czryId, String dwId, String gwzzid) {
		zzjgCzryGwzzDao.updateByCzryIdDwId(czryId, dwId, gwzzid);
	}

}
