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

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.frame.util.Query;
import com.frame.util.R;
import com.frame.validator.ValidatorUtils;
import com.frame.validator.group.AddGroup;
import com.frame.validator.group.UpdateGroup;
import com.frame.web.module.sys.entity.ZzbmDO;
import com.frame.web.module.sys.service.ZzbmService;
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
@RequestMapping("/sys/zzbm")
public class ZzbmController {

	private static final Logger logger = LoggerFactory.getLogger(ZzbmController.class);

	@Autowired
	private ZzbmService zzbmService;
	
	/**
	 * 新增
	 * @param ZzbmDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public R save(@RequestBody ZzbmDO zzbmDO) {
		ValidatorUtils.validateEntity(zzbmDO, AddGroup.class);
		zzbmService.save(zzbmDO);
		return R.ok().put("id", zzbmDO.getZzid());
	}
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public R delete(@PathVariable("id") String id) {
		List<ZzbmDO> zzbmDOList = zzbmService.selectByParentId(id);
		if (CollectionUtils.isNotEmpty(zzbmDOList)) {
			return R.error("该节点下有子节点， 请先删除子节点！");
		}
		zzbmService.removeById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		zzbmService.removeByIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param ZzbmDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public R update(@RequestBody ZzbmDO zzbmDO) {
		ValidatorUtils.validateEntity(zzbmDO, UpdateGroup.class);
		zzbmService.updateById(zzbmDO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("zzbm", zzbmDO)
	 */
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		ZzbmDO zzbmDO = zzbmService.getById(id);
		return R.ok().put("zzbm", zzbmDO);
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
		PageInfo<ZzbmDO> pageInfo = new PageInfo<ZzbmDO>(zzbmService.queryPage(query));
		return R.ok().put("page", pageInfo);
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public R list() {
		List<ZzbmDO> zzbmDOList = zzbmService.selectAll();
		return R.ok().put("list", zzbmDOList);
	}

}
