package com.xhz.web.module.sys.dao;


import java.util.List;
import java.util.Map;

import com.xhz.web.module.sys.entity.RecordLogDO;
import com.xhz.web.module.sys.entity.RecordLogDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 操作日志记录表 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-31
 */
public interface RecordLogDao extends BaseMapper<RecordLogDO> {
	
	List<RecordLogDTO> selectRecordLogDTOPage(Map<String, Object> query);
	
	RecordLogDTO selectRecordLogDTOById(String id);
	
	List<RecordLogDTO> selectRecordLogDTOList();
}
