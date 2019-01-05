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
import com.frame.web.module.sys.entity.CzryDO;
import com.frame.web.module.sys.service.CzryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



/**
 * <p>
 * InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-10
 */
 
@RestController
@RequestMapping("/sys/czry")
public class CzryController {

	private static final Logger logger = LoggerFactory.getLogger(CzryController.class);

	@Autowired
	private CzryService czryService;
	
	/**
	 * 新增
	 * @param CzryDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public R save(@RequestBody CzryDO czryDO) {
		ValidatorUtils.validateEntity(czryDO, AddGroup.class);
		int count = czryService.isUniqueAccoumt(czryDO);
		// 如果大于零则账号不允许使用
		if (count > 0) {
			return R.error("该账号已存在，请填写另外的账号！");
		}
		czryService.insert(czryDO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public R delete(@PathVariable("id") String id) {
		czryService.removeById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		czryService.removeByIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param CzryDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public R update(@RequestBody CzryDO czryDO) {
		ValidatorUtils.validateEntity(czryDO, UpdateGroup.class);
		int count = czryService.isUniqueAccoumt(czryDO);
		// 如果大于零则账号不允许使用
		if (count > 0) {
			return R.error("该账号已存在，请填写另外的账号！");
		}
		czryService.updateById(czryDO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("czry", czryDO)
	 */
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		CzryDO czryDO = czryService.getById(id);
		return R.ok().put("czry", czryDO);
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
		PageInfo<CzryDO> pageInfo = new PageInfo<CzryDO>(czryService.queryPage(query));
		return R.ok().put("page", pageInfo);
	}

}
