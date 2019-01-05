package com.frame.web.module.sys.dao;

import com.frame.web.module.sys.entity.CzryqxDO;

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
public interface CzryqxDao extends BaseMapper<CzryqxDO> {

	List<String> getCzryQx(String czryId);

	void removeByCzryId(String czryId);

}
