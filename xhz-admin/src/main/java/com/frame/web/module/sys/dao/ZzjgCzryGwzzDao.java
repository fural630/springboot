package com.frame.web.module.sys.dao;

import com.frame.web.module.sys.entity.ZzjgCzryGwzzDO;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * InnoDB free: 8192 kB Mapper 接口
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
public interface ZzjgCzryGwzzDao extends BaseMapper<ZzjgCzryGwzzDO> {

	void updateByCzryIdDwId(@Param("czryId") String czryId, @Param("dwId") String dwId, @Param("gwzzid") String gwzzid);

}
