package com.xhz.web.module.sys.controller;

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

import com.xhz.web.module.sys.service.RecordLogService;
import com.xhz.web.module.sys.entity.RecordLogDTO;



/**
 * <p>
 * 操作日志记录表 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-31
 */
 
@RestController
@RequestMapping("/sys")
@Api(tags = {"操作日志记录表"})
public class RecordLogController {

	private static final Logger logger = LoggerFactory.getLogger(RecordLogController.class);

	@Autowired
	private RecordLogService recordLogService;
	
	/**
	 * 新增
	 * @param RecordLogDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="新增")
	@RequestMapping(value = "/recordlogs", method = RequestMethod.POST)
	public R insert(@RequestBody RecordLogDTO recordlogDTO) {
		ValidatorUtils.validateEntity(recordlogDTO, AddGroup.class);
		recordLogService.insertRecordLogDTO(recordlogDTO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@ApiOperation(value="删除")
	@RequestMapping(value = "/recordlogs/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") String id) {
		recordLogService.deleteById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="批量删除")
	@RequestMapping(value = "/recordlogs/deleteBatch", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		recordLogService.deleteBatchIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param RecordLogDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="修改")
	@RequestMapping(value = "/recordlogs", method = RequestMethod.PATCH)
	public R update(@RequestBody RecordLogDTO recordlogDTO) {
		ValidatorUtils.validateEntity(recordlogDTO, UpdateGroup.class);
		recordLogService.updateRecordLogDTOById(recordlogDTO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("data", recordlogDTO)
	 */
	@ApiOperation(value="查询")
	@RequestMapping(value = "/recordlogs/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		RecordLogDTO recordlogDTO = recordLogService.selectRecordLogDTOById(id);
		return R.ok().put("data", recordlogDTO);
	}
	
	/**
	 * 查询所有
	 * @return R.ok()
	 */
	@ApiOperation(value="查询所有")
	@RequestMapping(value = "/recordlogs", method = RequestMethod.GET)
	public R getAll() {
		List<RecordLogDTO> recordlogDTOList = recordLogService.selectRecordLogDTOList();
		return R.ok().put("data", recordlogDTOList);
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value="分页查询")
	@RequestMapping(value = "/recordlogs/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<RecordLogDTO> pageInfo = new PageInfo<RecordLogDTO>(recordLogService.selectRecordLogDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

}
