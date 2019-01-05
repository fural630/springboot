package com.frame.web.module.sys.service;

import com.frame.web.module.sys.entity.CzryqxDO;
import com.frame.util.Query;
import com.frame.web.module.sys.dao.CzryqxDao;
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
public class CzryqxService extends ServiceImpl<CzryqxDao, CzryqxDO> {
	
	@Autowired
	private CzryqxDao czryqxDao;

	public List<CzryqxDO> queryPage(Query query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getCzryQx(String czryId) {
		return czryqxDao.getCzryQx(czryId);
	}

	public void removeByCzryId(String czryId) {
		czryqxDao.removeByCzryId(czryId);
	}

}
