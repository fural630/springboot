package com.xhz.web.module.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.xhz.web.module.sys.entity.DictionaryDO;
import com.xhz.web.module.sys.entity.DictionaryDTO;
import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.dao.DictionaryDao;
import com.xhz.constant.Constant.IsDeleted;
import com.xhz.util.CopyUtil;
import com.xhz.util.RRException;

import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 通用字典 服务实现类
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-30
 */
@Service
public class DictionaryService {

	@Autowired
	private DictionaryDao dictionaryDao;
	
	public void insertDictionaryDTO(DictionaryDTO dictionaryDTO) {
		DictionaryDO dictionaryDO = CopyUtil.copyProperties(dictionaryDTO, DictionaryDO.class);
		checkCanSave(dictionaryDO);
		dictionaryDao.insert(dictionaryDO);
	}
	
	public void updateDictionaryDTOById(DictionaryDTO dictionaryDTO) {
		DictionaryDO dictionaryDO = CopyUtil.copyProperties(dictionaryDTO, DictionaryDO.class);
		checkCanSave(dictionaryDO);
		dictionaryDO.setUpdateTime(new Date());
		dictionaryDao.updateById(dictionaryDO);
	}
	
	public DictionaryDTO selectDictionaryDTOById(String id) {
		return dictionaryDao.selectDictionaryDTOById(id);
	}
	
	public List<DictionaryDTO> selectDictionaryDTOList(Map<String, Object> params) {
		if (null != params.get("value")) {	//递归查询子字典
			
		}
		return dictionaryDao.selectDictionaryDTOList(params);
	}
	
	public List<DictionaryDTO> selectDictionaryDTOPage(Map<String, Object> query) {
		return dictionaryDao.selectDictionaryDTOPage(query);
	}
	
	public void insert(DictionaryDO dictionaryDO) {
		checkCanSave(dictionaryDO);
		dictionaryDao.insert(dictionaryDO);
	}

	public void deleteById(String id) {
		dictionaryDao.deleteById(id);
	}

	public void deleteBatchIds(List<String> ids) {
		dictionaryDao.deleteBatchIds(ids);
	}

	public void updateById(DictionaryDO dictionaryDO) {
		checkCanSave(dictionaryDO);
		dictionaryDao.updateById(dictionaryDO);
	}

	public DictionaryDO selectById(String id) {
		return dictionaryDao.selectById(id);
	}
	
	public List<DictionaryDO> selectList() {
		return dictionaryDao.selectList(null);
	}
	
	private void checkCanSave(DictionaryDO dictionaryDO) {
		int count = dictionaryDao.checkCanSave(dictionaryDO);
		if (count > 0) {
			throw new RRException("值 " + dictionaryDO.getValue() +" 已经存在，不允许重复保存！");
		}
	}

	public void disableDictionaryById(String id) {
		List<DictionaryDO> allList = new ArrayList<DictionaryDO>();
		recursiveDictionary(id, allList);
		if (CollectionUtils.isNotEmpty(allList)) {
			for (DictionaryDO dictionaryDO : allList) {
				dictionaryDao.updateIsDeleted(dictionaryDO.getId(), IsDeleted.YES.getValue());
			}
		}
	}

	public void enableDictionaryById(String id) {
		List<DictionaryDO> allList = new ArrayList<DictionaryDO>();
		recursiveDictionary(id, allList);
		if (CollectionUtils.isNotEmpty(allList)) {
			for (DictionaryDO dictionaryDO : allList) {
				dictionaryDao.updateIsDeleted(dictionaryDO.getId(), IsDeleted.NO.getValue());
			}
		}
	}
	
	public DictionaryDO selectDictonaryById(String id) {
		return dictionaryDao.selectDictonaryById(id);
	}
	
	/**
	 * 根据parentId查询下一级字典
	 * @param parentId
	 * @return
	 */
	public List<DictionaryDO> selectByParentId(String parentId) {
		return dictionaryDao.selectByParentId(parentId);
	}
	
	/**
	 * 递归查询子目录
	 * @param menuId
	 * @param allMenuList
	 */
	private void recursiveDictionary(String id, List<DictionaryDO> allDictonaryList) {
		DictionaryDO dictionaryDO = this.selectDictonaryById(id);
		allDictonaryList.add(dictionaryDO);
		List<DictionaryDO> childDictionaryList = this.selectByParentId(id);
		if (CollectionUtils.isNotEmpty(childDictionaryList)) {
			for (DictionaryDO childDictionaryDO : childDictionaryList) {
				recursiveDictionary(childDictionaryDO.getId(), allDictonaryList);
			}
		}
	}
}
