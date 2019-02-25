package com.xhz.web.module.sys.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.constant.Constant;
import com.xhz.util.CopyUtil;
import com.xhz.util.PinyinUtils;
import com.xhz.web.module.sys.dao.DeptDao;
import com.xhz.web.module.sys.entity.DeptDO;
import com.xhz.web.module.sys.entity.DeptDTO;

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
	
	// 最大代码
	private static final Integer MAX_DM = 100;
	// 最大个位数
	private static final Integer MAX_SINGLE_DIGIT = 9;
	// 代码截取长度
	private static final Integer DM_LENGTH = 2;

	@Autowired
	private DeptDao deptDao;
	
	public void insertDeptDTO(DeptDTO deptDTO) {
		DeptDO deptDO = CopyUtil.copyProperties(deptDTO, DeptDO.class);
		if (StringUtils.isNoneBlank(deptDO.getName())) {
			deptDO.setPym(PinyinUtils.parse(deptDO.getName()));
			deptDO.setIsDeleted(Constant.IsDeleted.NO.getValue());
		}
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
	
	private String createCode(String parentId) {
		String code = "01";
		String maxCode = deptDao.selectMaxCodeByParentId(parentId);
		if (StringUtils.isNoneEmpty(maxCode)) {
			
		}
		return code;
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
