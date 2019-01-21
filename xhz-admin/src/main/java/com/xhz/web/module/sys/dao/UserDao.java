package com.xhz.web.module.sys.dao;


import java.util.List;
import java.util.Map;

import com.xhz.web.module.sys.entity.UserDO;
import com.xhz.web.module.sys.entity.UserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 用户 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-21
 */
public interface UserDao extends BaseMapper<UserDO> {
	
	List<UserDTO> selectUserDTOPage(Map<String, Object> query);
	
	UserDTO selectUserDTOById(Long id);
	
	List<UserDTO> selectUserDTOList();
}
