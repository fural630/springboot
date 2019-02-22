package com.xhz.web.module.sys.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.validator.ValidatorUtils;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import com.xhz.web.module.sys.entity.DictionaryDTO;
import com.xhz.web.module.sys.service.DictionaryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 通用字典 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-30
 */

@RestController
@RequestMapping("/sys")
@Api(tags = { "通用字典" })
public class DictionaryController {

	private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 新增
	 * 
	 * @param DictionaryDTO
	 * @return R.ok()
	 */
	@ApiOperation(value = "新增")
	@RequestMapping(value = "/dictionarys", method = RequestMethod.POST)
	@RequiresPermissions("sys:dictionary:insert")
	public R insert(@RequestBody DictionaryDTO dictionaryDTO) {
		dictionaryDTO.setCreateTime(new Date());
		dictionaryDTO.setUpdateTime(new Date());
		ValidatorUtils.validateEntity(dictionaryDTO, AddGroup.class);
		dictionaryService.insertDictionaryDTO(dictionaryDTO);
		return R.ok();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return R.ok()
	 */
	@ApiOperation(value = "删除")
	@RequestMapping(value = "/dictionarys/{id}", method = RequestMethod.DELETE)
	@RequiresPermissions("sys:dictionary:delete")
	public R delete(@PathVariable("id") String id) {
		dictionaryService.deleteById(id);
		return R.ok();
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "批量删除")
	@RequestMapping(value = "/dictionarys/deleteBatch", method = RequestMethod.POST)
	@RequiresPermissions("")
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		dictionaryService.deleteBatchIds(ids);
		return R.ok();
	}

	/**
	 * 修改
	 * 
	 * @param DictionaryDTO
	 * @return R.ok()
	 */
	@ApiOperation(value = "修改")
	@RequestMapping(value = "/dictionarys", method = RequestMethod.PATCH)
	@RequiresPermissions("sys:dictionary:update")
	public R update(@RequestBody DictionaryDTO dictionaryDTO) {
		dictionaryDTO.setUpdateTime(new Date());
		ValidatorUtils.validateEntity(dictionaryDTO, UpdateGroup.class);
		dictionaryService.updateDictionaryDTOById(dictionaryDTO);
		return R.ok();
	}

	/**
	 * 查询
	 * 
	 * @param id
	 * @return R.ok().put("data", dictionaryDTO)
	 */
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/dictionarys/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:dictionary:info")
	public R info(@PathVariable("id") String id) {
		DictionaryDTO dictionaryDTO = dictionaryService.selectDictionaryDTOById(id);
		return R.ok().put("data", dictionaryDTO);
	}

	/**
	 * 查询所有
	 * 
	 * @return R.ok()
	 */
	@ApiOperation(value = "查询所有")
	@RequestMapping(value = "/dictionarys", method = RequestMethod.GET)
	@RequiresPermissions("sys:dictionary:getAll")
	public R getAll(@RequestParam Map<String, Object> params) {
		List<DictionaryDTO> dictionaryDTOList = dictionaryService.selectDictionaryDTOList(params);
		return R.ok().put("data", dictionaryDTOList);
	}

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value = "分页查询")
	@RequestMapping(value = "/dictionarys/page", method = RequestMethod.GET)
	@RequiresPermissions("sys:dictionary:page")
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<DictionaryDTO> pageInfo = new PageInfo<DictionaryDTO>(
				dictionaryService.selectDictionaryDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

	/**
	 * 禁用字典
	 * 
	 * @param menuId
	 * @return R.ok()
	 */
	@ApiOperation(value = "禁用字典", notes = "禁用字典，子字典都将被禁用")
	@RequestMapping(value = "/dictionarys/disable/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:dictionary:disable")
	public R disable(@PathVariable("id") String menuId) {
		dictionaryService.disableDictionaryById(menuId);
		return R.ok();
	}

	/**
	 * 禁用字典
	 * 
	 * @param menuId
	 * @return R.ok()
	 */
	@ApiOperation(value = "启用字典", notes = "启用字典,子字典都将被启用")
	@RequestMapping(value = "/dictionarys/enable/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:dictionary:enable")
	public R enable(@PathVariable("id") String menuId) {
		dictionaryService.enableDictionaryById(menuId);
		return R.ok();
	}

}
