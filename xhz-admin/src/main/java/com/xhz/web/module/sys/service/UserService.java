package com.xhz.web.module.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.web.module.sys.dao.UserDao;
import com.xhz.web.module.sys.entity.UserDO;

/**
 * <p>
 * InnoDB free: 8192 kB 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-16
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void insert(UserDO userDO) {
		userDao.insert(userDO);
	}

	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	public void deleteBatchIds(List<Long> ids) {
		userDao.deleteBatchIds(ids);
	}

	public void updateById(UserDO userDO) {
		userDao.updateById(userDO);
	}

	public UserDO selectById(Long id) {
		return userDao.selectById(id);
	}
	
	public List<UserDO> queryPage(Map<String, Object> query) {
		return userDao.queryPage(query);
	}
}
