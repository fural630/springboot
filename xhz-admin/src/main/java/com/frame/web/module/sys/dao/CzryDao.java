package com.frame.web.module.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.frame.web.module.sys.entity.CzryDO;

/**
 * <p>
 * InnoDB free: 8192 kB Mapper 接口
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
public interface CzryDao extends BaseMapper<CzryDO> {

	List<CzryDO> queryPage(Map<String, Object> map);

	CzryDO getById(String id);

	String selectMaxBmCzryDm(String bmid);

	int acountUnique(@Param("czryZh") String czryZh, @Param("myCzryZh") String myCzryZh);

}
