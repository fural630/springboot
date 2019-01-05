package com.xhz.web.module.sys.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhz.web.module.sys.entity.UserDO;

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
