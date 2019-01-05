package com.frame.web.module.sys.service;

import com.frame.web.module.sys.entity.BaryxxDO;
import com.frame.util.Query;
import com.frame.web.module.sys.dao.BaryxxDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

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
public class BaryxxService extends ServiceImpl<BaryxxDao, BaryxxDO> {

	public List<BaryxxDO> queryPage(Query query) {
		// TODO Auto-generated method stub
		return null;
	}

}
