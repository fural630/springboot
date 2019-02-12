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

import com.xhz.web.module.sys.service.ErrorLogService;
import com.xhz.web.module.sys.entity.ErrorLogDTO;



/**
 * <p>
 * 错误日志 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-12
 */
 
@RestController
@RequestMapping("/sys")
@Api(tags = {"错误日志"})
public class ErrorLogController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorLogController.class);

	@Autowired
	private ErrorLogService errorLogService;
	
	/**
	 * 新增
	 * @param ErrorLogDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="新增")
	@RequestMapping(value = "/errorlogs", method = RequestMethod.POST)
	public R insert(@RequestBody ErrorLogDTO errorlogDTO) {
		ValidatorUtils.validateEntity(errorlogDTO, AddGroup.class);
		errorLogService.insertErrorLogDTO(errorlogDTO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@ApiOperation(value="删除")
	@RequestMapping(value = "/errorlogs/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") String id) {
		errorLogService.deleteById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="批量删除")
	@RequestMapping(value = "/errorlogs/deleteBatch", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		errorLogService.deleteBatchIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param ErrorLogDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="修改")
	@RequestMapping(value = "/errorlogs", method = RequestMethod.PATCH)
	public R update(@RequestBody ErrorLogDTO errorlogDTO) {
		ValidatorUtils.validateEntity(errorlogDTO, UpdateGroup.class);
		errorLogService.updateErrorLogDTOById(errorlogDTO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("data", errorlogDTO)
	 */
	@ApiOperation(value="查询")
	@RequestMapping(value = "/errorlogs/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		ErrorLogDTO errorlogDTO = errorLogService.selectErrorLogDTOById(id);
		return R.ok().put("data", errorlogDTO);
	}
	
	/**
	 * 查询所有
	 * @return R.ok()
	 */
	@ApiOperation(value="查询所有")
	@RequestMapping(value = "/errorlogs", method = RequestMethod.GET)
	public R getAll() {
		List<ErrorLogDTO> errorlogDTOList = errorLogService.selectErrorLogDTOList();
		return R.ok().put("data", errorlogDTOList);
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value="分页查询")
	@RequestMapping(value = "/errorlogs/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<ErrorLogDTO> pageInfo = new PageInfo<ErrorLogDTO>(errorLogService.selectErrorLogDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

}
