package com.xhz.web.module.sys.controller;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.web.module.sys.entity.ErrorLogDTO;
import com.xhz.web.module.sys.service.ErrorLogService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@Api(tags = { "错误日志" })
public class ErrorLogController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorLogController.class);

	@Autowired
	private ErrorLogService errorLogService;

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "批量删除")
	@RequestMapping(value = "/errorlogs/deleteBatch", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			errorLogService.deleteBatchIds(ids);
		} else {
			errorLogService.deleteAll();
		}
		return R.ok();
	}

	/**
	 * 查询
	 * 
	 * @param id
	 * @return R.ok().put("data", errorlogDTO)
	 */
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/errorlogs/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		ErrorLogDTO errorlogDTO = errorLogService.selectErrorLogDTOById(id);
		return R.ok().put("data", errorlogDTO);
	}

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value = "分页查询")
	@RequestMapping(value = "/errorlogs/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<ErrorLogDTO> pageInfo = new PageInfo<ErrorLogDTO>(errorLogService.selectErrorLogDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

}
