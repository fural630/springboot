package com.xhz.web.module.sys.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.util.CopyUtil;
import com.xhz.web.module.sys.dao.RecordLogDao;
import com.xhz.web.module.sys.entity.RecordLogDO;
import com.xhz.web.module.sys.entity.RecordLogDTO;

/**
 * <p>
 * 操作日志记录表 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-31
 */
@Service
public class RecordLogService {

	@Autowired
	private RecordLogDao recordLogDao;
	
	public void insertRecordLogDTO(RecordLogDTO recordlogDTO) {
		RecordLogDO recordLogDO = CopyUtil.copyProperties(recordlogDTO, RecordLogDO.class);
		this.insert(recordLogDO);
	}
	
	public void updateRecordLogDTOById(RecordLogDTO recordlogDTO) {
		RecordLogDO recordLogDO = CopyUtil.copyProperties(recordlogDTO, RecordLogDO.class);
		this.updateById(recordLogDO);
	}
	
	public RecordLogDTO selectRecordLogDTOById(String id) {
		return recordLogDao.selectRecordLogDTOById(id);
	}
	
	public List<RecordLogDTO> selectRecordLogDTOList() {
		return recordLogDao.selectRecordLogDTOList();
	}
	
	public List<RecordLogDTO> selectRecordLogDTOPage(Map<String, Object> query) {
		return recordLogDao.selectRecordLogDTOPage(query);
	}
	
	public void insert(RecordLogDO recordLogDO) {
		recordLogDO.setId(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		recordLogDO.setCreateDate(new Date());
		recordLogDao.insert(recordLogDO);
	}

	public void deleteById(String id) {
		recordLogDao.deleteById(id);
	}

	public void deleteBatchIds(List<String> ids) {
		recordLogDao.deleteBatchIds(ids);
	}

	public void updateById(RecordLogDO recordLogDO) {
		recordLogDao.updateById(recordLogDO);
	}

	public RecordLogDO selectById(String id) {
		return recordLogDao.selectById(id);
	}
	
	public List<RecordLogDO> selectList() {
		return recordLogDao.selectList(null);
	}
}
