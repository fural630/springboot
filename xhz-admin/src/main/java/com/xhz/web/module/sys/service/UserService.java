package com.xhz.web.module.sys.service;

import java.util.List;
import java.util.Map;


import com.xhz.web.module.sys.entity.UserDO;
import com.xhz.web.module.sys.entity.UserDTO;
import com.xhz.web.module.sys.dao.UserDao;
import com.xhz.constant.Constant.IsDeleted;
import com.xhz.util.CopyUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-21
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void insertUserDTO(UserDTO userDTO) {
		UserDO userDO = CopyUtil.copyProperties(userDTO, UserDO.class);
		userDao.insert(userDO);
	}
	
	public void updateUserDTOById(UserDTO userDTO) {
		UserDO userDO = CopyUtil.copyProperties(userDTO, UserDO.class);
		userDao.updateById(userDO);
	}
	
	public UserDTO selectUserDTOById(Long id) {
		return userDao.selectUserDTOById(id);
	}
	
	public List<UserDTO> selectUserDTOList() {
		return userDao.selectUserDTOList();
	}
	
	public List<UserDTO> selectUserDTOPage(Map<String, Object> query) {
		return userDao.selectUserDTOPage(query);
	}
	
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
	
	public List<UserDO> selectList() {
		return userDao.selectList(null);
	}

	public void enableById(Long id) {
		UserDO userDO = new UserDO();
		userDO.setId(id);
		userDO.setIsDeleted(IsDeleted.NO.getValue());
		userDao.updateById(userDO);
	}

	public void disableById(Long id) {
		UserDO userDO = new UserDO();
		userDO.setId(id);
		userDO.setIsDeleted(IsDeleted.YES.getValue());
		userDao.updateById(userDO);
	}
}
