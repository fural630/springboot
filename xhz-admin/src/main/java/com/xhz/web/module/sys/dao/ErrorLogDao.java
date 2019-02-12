package com.xhz.web.module.sys.dao;


import java.util.List;
import java.util.Map;

import com.xhz.web.module.sys.entity.ErrorLogDO;
import com.xhz.web.module.sys.entity.ErrorLogDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 错误日志 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-12
 */
public interface ErrorLogDao extends BaseMapper<ErrorLogDO> {
	
	List<ErrorLogDTO> selectErrorLogDTOPage(Map<String, Object> query);
	
	ErrorLogDTO selectErrorLogDTOById(String id);
	
	List<ErrorLogDTO> selectErrorLogDTOList();
}
