package com.xhz.web.module.sys.service;

import java.util.List;
import java.util.Map;


import com.xhz.web.module.sys.entity.DeptDO;
import com.xhz.web.module.sys.entity.DeptDTO;
import com.xhz.web.module.sys.dao.DeptDao;
import com.xhz.util.CopyUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 机构 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-25
 */
@Service
public class DeptService {

	@Autowired
	private DeptDao deptDao;
	
	public void insertDeptDTO(DeptDTO deptDTO) {
		DeptDO deptDO = CopyUtil.copyProperties(deptDTO, DeptDO.class);
		this.insert(deptDO);
	}
	
	public void updateDeptDTOById(DeptDTO deptDTO) {
		DeptDO deptDO = CopyUtil.copyProperties(deptDTO, DeptDO.class);
		this.updateById(deptDO);
	}
	
	public DeptDTO selectDeptDTOById(String deptId) {
		return deptDao.selectDeptDTOById(deptId);
	}
	
	public List<DeptDTO> selectDeptDTOList() {
		return deptDao.selectDeptDTOList();
	}
	
	public List<DeptDTO> selectDeptDTOPage(Map<String, Object> query) {
		return deptDao.selectDeptDTOPage(query);
	}
	
	public void insert(DeptDO deptDO) {
		deptDao.insert(deptDO);
	}

	public void deleteById(String deptId) {
		deptDao.deleteById(deptId);
	}

	public void deleteBatchIds(List<String> deptIds) {
		deptDao.deleteBatchIds(deptIds);
	}

	public void updateById(DeptDO deptDO) {
		deptDao.updateById(deptDO);
	}

	public DeptDO selectById(String deptId) {
		return deptDao.selectById(deptId);
	}
	
	public List<DeptDO> selectList() {
		return deptDao.selectList(null);
	}
}
