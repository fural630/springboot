package com.xhz.web.module.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.constant.Constant;
import com.xhz.constant.Constant.IsDeleted;
import com.xhz.util.CopyUtil;
import com.xhz.util.PinyinUtils;
import com.xhz.util.RRException;
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
	private static final Integer MAX_CODE = 100;
	// 最大个位数
	private static final Integer MAX_SINGLE_DIGIT = 9;
	// 代码截取长度
	private static final Integer CODE_LENGTH = 2;

	@Autowired
	private DeptDao deptDao;
	
	public synchronized void insertDeptDTO(DeptDTO deptDTO) {
		DeptDO deptDO = CopyUtil.copyProperties(deptDTO, DeptDO.class);
		if (StringUtils.isNoneBlank(deptDO.getName())) {
			deptDO.setPym(PinyinUtils.parse(deptDO.getName()));
			deptDO.setIsDeleted(Constant.IsDeleted.NO.getValue());
			deptDO.setCode(this.createCode(deptDO.getParentId()));
		}
		this.insert(deptDO);
	}
	
	public void updateDeptDTOById(DeptDTO deptDTO) {
		DeptDO deptDO = CopyUtil.copyProperties(deptDTO, DeptDO.class);
		if (StringUtils.isNoneBlank(deptDO.getName())) {
			deptDO.setPym(PinyinUtils.parse(deptDO.getName()));
		}
		this.updateById(deptDO);
	}
	
	public DeptDTO selectDeptDTOById(String deptId) {
		return deptDao.selectDeptDTOById(deptId);
	}
	
	public List<DeptDTO> selectDeptDTOList(Map<String, Object> params) {
		return deptDao.selectDeptDTOList(params);
	}
	
	public List<DeptDTO> selectDeptDTOPage(Map<String, Object> query) {
		return deptDao.selectDeptDTOPage(query);
	}
	
	private String createCode(String parentId) {
		String maxCode = deptDao.selectMaxCodeByParentId(parentId);
		DeptDO deptDO = deptDao.selectById(parentId);
		String code = null == deptDO ? "" : deptDO.getCode();
		if (StringUtils.isEmpty(maxCode)) {
			return code + "01";
		} else {
			String lastCode = maxCode.substring(maxCode.length() - CODE_LENGTH, maxCode.length());
			Integer codeInteger = Integer.parseInt(lastCode) + 1;
			// 不支持大于100的编号
			if (codeInteger >= MAX_CODE) {
				throw new RRException("创建失败，本节点已超过最大容纳" + MAX_CODE + "个节点！");
			}
			// 编号为个位数时，要补充0在前面，变长度为2的编号
			if (codeInteger <= MAX_SINGLE_DIGIT) {
				return code + "0" + String.valueOf(codeInteger);
			} else {
				return code + String.valueOf(codeInteger);
			}
		}
	}
	/**
	 * 禁用部门，子部门都将被禁用
	 * @param deptId
	 */
	public void disableDeptById(String deptId) {
		List<DeptDO> allDepList = new ArrayList<DeptDO>();
		recursiveMenu(deptId, allDepList);
		if (CollectionUtils.isNotEmpty(allDepList)) {
			for (DeptDO deptDO : allDepList) {
				DeptDO updateDeptDO = new DeptDO();
				updateDeptDO.setIsDeleted(IsDeleted.YES.getValue());
				updateDeptDO.setDeptId(deptDO.getDeptId());
				this.updateById(updateDeptDO);
			}
		}
	}
	
	/**
	 * 递归查询子目录
	 * 
	 * @param menuId
	 * @param allMenuList
	 */
	private void recursiveMenu(String deptId, List<DeptDO> allDeptList) {
		DeptDO deptDO = deptDao.selectById(deptId);
		allDeptList.add(deptDO);
		List<DeptDO> childList = this.selectDeptByParentId(deptId);
		if (CollectionUtils.isNotEmpty(childList)) {
			for (DeptDO childdeptDO: childList) {
				recursiveMenu(childdeptDO.getDeptId(), allDeptList);
			}
		}
	}
	
	public List<DeptDO> selectDeptByParentId(String parentId){
		return deptDao.selectDeptByParentId(parentId);
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
