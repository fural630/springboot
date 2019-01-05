package com.frame.web.module.sys.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import com.frame.util.Query;
import com.frame.util.R;
import com.frame.validator.ValidatorUtils;
import com.frame.validator.group.AddGroup;
import com.frame.validator.group.UpdateGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.frame.web.module.sys.service.CzryqxService;
import com.frame.web.module.sys.entity.CzryqxDO;



/**
 * <p>
 * InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
 
@RestController
@RequestMapping("/sys/czryqx")
public class CzryqxController {

	private static final Logger logger = LoggerFactory.getLogger(CzryqxController.class);

	@Autowired
	private CzryqxService czryqxService;
	
	/**
	 * 新增
	 * @param CzryqxDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public R save(@RequestBody CzryqxDO czryqxDO) {
		ValidatorUtils.validateEntity(czryqxDO, AddGroup.class);
		czryqxService.save(czryqxDO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public R delete(@PathVariable("id") Long id) {
		czryqxService.removeById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		czryqxService.removeByIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param CzryqxDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public R update(@RequestBody CzryqxDO czryqxDO) {
		ValidatorUtils.validateEntity(czryqxDO, UpdateGroup.class);
		czryqxService.updateById(czryqxDO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("czryqx", czryqxDO)
	 */
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		CzryqxDO czryqxDO = czryqxService.getById(id);
		return R.ok().put("czryqx", czryqxDO);
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return R.ok().put("page", pageInfo);
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<CzryqxDO> pageInfo = new PageInfo<CzryqxDO>(czryqxService.queryPage(query));
		return R.ok().put("page", pageInfo);
	}

}
