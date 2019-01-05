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
import com.frame.web.module.sys.entity.RegionDO;
import com.frame.web.module.sys.service.RegionService;
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
@RequestMapping("/sys/region")
public class RegionController {

	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

	@Autowired
	private RegionService regionService;
	
	/**
	 * 新增
	 * @param RegionDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public R save(@RequestBody RegionDO regionDO) {
		ValidatorUtils.validateEntity(regionDO, AddGroup.class);
		regionService.save(regionDO);
		return R.ok();
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public R delete(@PathVariable("id") String id) {
		regionService.removeById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param userIds
	 * @return
	 */
	@RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		regionService.removeByIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param userDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public R update(@RequestBody RegionDO regionDO) {
		ValidatorUtils.validateEntity(regionDO, UpdateGroup.class);
		regionService.updateById(regionDO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("user", userDO)
	 */
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		RegionDO regionDO = regionService.getById(id);
		return R.ok().put("region", regionDO);
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
		PageInfo<RegionDO> pageInfo = new PageInfo<RegionDO>(regionService.queryPage(query));
		return R.ok().put("page", pageInfo);
	}

}
