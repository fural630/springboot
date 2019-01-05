package com.frame.web.module.sys.dao;

import com.frame.web.module.sys.entity.RegionDO;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * InnoDB free: 8192 kB Mapper 接口
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
public interface RegionDao extends BaseMapper<RegionDO> {
	
	List<RegionDO> selectByPid(String parentId);
}
