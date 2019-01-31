package com.xhz.web.module.sys.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xhz.web.module.sys.entity.DictionaryDO;
import com.xhz.web.module.sys.entity.DictionaryDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 通用字典 Dao 接口
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-30
 */
public interface DictionaryDao extends BaseMapper<DictionaryDO> {
	
	List<DictionaryDTO> selectDictionaryDTOPage(Map<String, Object> query);
	
	DictionaryDTO selectDictionaryDTOById(String id);
	
	List<DictionaryDTO> selectDictionaryDTOList(Map<String, Object> params);

	int checkCanSave(DictionaryDO dictionaryDO);

	List<DictionaryDO> selectByParentId(String parentId);

	DictionaryDO selectDictonaryById(String id);

	void updateIsDeleted(@Param("id") String id, @Param("isDeleted") String isDeleted);
}
