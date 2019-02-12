package com.xhz.web.module.sys.service;

import java.util.List;
import java.util.Map;

import com.xhz.web.module.sys.entity.ErrorLogDO;
import com.xhz.web.module.sys.entity.ErrorLogDTO;
import com.xhz.web.module.sys.dao.ErrorLogDao;
import com.xhz.util.CopyUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 错误日志 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-12
 */
@Service
public class ErrorLogService {

	@Autowired
	private ErrorLogDao errorLogDao;

	public void insertErrorLogDTO(ErrorLogDTO errorlogDTO) {
		ErrorLogDO errorLogDO = CopyUtil.copyProperties(errorlogDTO, ErrorLogDO.class);
		this.insert(errorLogDO);
	}

	public void updateErrorLogDTOById(ErrorLogDTO errorlogDTO) {
		ErrorLogDO errorLogDO = CopyUtil.copyProperties(errorlogDTO, ErrorLogDO.class);
		this.updateById(errorLogDO);
	}

	public ErrorLogDTO selectErrorLogDTOById(String id) {
		return errorLogDao.selectErrorLogDTOById(id);
	}

	public List<ErrorLogDTO> selectErrorLogDTOList() {
		return errorLogDao.selectErrorLogDTOList();
	}

	public List<ErrorLogDTO> selectErrorLogDTOPage(Map<String, Object> query) {
		return errorLogDao.selectErrorLogDTOPage(query);
	}

	public void insert(ErrorLogDO errorLogDO) {
		errorLogDao.insert(errorLogDO);
	}

	public void deleteById(String id) {
		errorLogDao.deleteById(id);
	}

	public void deleteBatchIds(List<String> ids) {
		errorLogDao.deleteBatchIds(ids);
	}

	public void updateById(ErrorLogDO errorLogDO) {
		errorLogDao.updateById(errorLogDO);
	}

	public ErrorLogDO selectById(String id) {
		return errorLogDao.selectById(id);
	}

	public List<ErrorLogDO> selectList() {
		return errorLogDao.selectList(null);
	}
}
