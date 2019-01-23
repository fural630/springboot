package com.xhz.web.module.develop.controller;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



import org.springframework.web.bind.annotation.RestController;
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.validator.ValidatorUtils;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xhz.web.module.develop.service.DatabaseService;
import com.xhz.web.module.develop.entity.DatabaseDTO;



/**
 * <p>
 * 数据源配置 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */
 
@RestController
@RequestMapping("/develop")
@Api(tags = {"数据源配置"})
public class DatabaseController {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseController.class);

	@Autowired
	private DatabaseService databaseService;
	
	/**
	 * 新增
	 * @param DatabaseDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="新增")
	@RequestMapping(value = "/databases", method = RequestMethod.POST)
	public R insert(@RequestBody DatabaseDTO databaseDTO) {
		ValidatorUtils.validateEntity(databaseDTO, AddGroup.class);
		databaseService.insertDatabaseDTO(databaseDTO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@ApiOperation(value="删除")
	@RequestMapping(value = "/databases/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") String id) {
		databaseService.deleteById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="批量删除")
	@RequestMapping(value = "/databases/deleteBatch", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		databaseService.deleteBatchIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param DatabaseDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="修改")
	@RequestMapping(value = "/databases", method = RequestMethod.PATCH)
	public R update(@RequestBody DatabaseDTO databaseDTO) {
		ValidatorUtils.validateEntity(databaseDTO, UpdateGroup.class);
		databaseService.updateDatabaseDTOById(databaseDTO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("data", databaseDTO)
	 */
	@ApiOperation(value="查询")
	@RequestMapping(value = "/databases/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		DatabaseDTO databaseDTO = databaseService.selectDatabaseDTOById(id);
		return R.ok().put("data", databaseDTO);
	}
	
	/**
	 * 查询所有
	 * @return R.ok()
	 */
	@ApiOperation(value="查询所有")
	@RequestMapping(value = "/databases", method = RequestMethod.GET)
	public R getAll() {
		List<DatabaseDTO> databaseDTOList = databaseService.selectDatabaseDTOList();
		return R.ok().put("data", databaseDTOList);
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value="分页查询")
	@RequestMapping(value = "/databases/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<DatabaseDTO> pageInfo = new PageInfo<DatabaseDTO>(databaseService.selectDatabaseDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

}
