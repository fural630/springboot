package com.frame.web.module.sys.dao;

import com.frame.web.module.sys.entity.UserDO;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * InnoDB free: 8192 kB Mapper 接口
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-08
 */
public interface UserDao extends BaseMapper<UserDO> {

	List<UserDO> queryPage(Map<String, Object> query);

}
