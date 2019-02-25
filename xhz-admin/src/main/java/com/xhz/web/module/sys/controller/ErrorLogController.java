package com.xhz.web.module.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhz.annotation.SysLog;
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.web.module.sys.entity.ErrorLogDTO;
import com.xhz.web.module.sys.service.ErrorLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 错误日志 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-13
 */

@RestController
@RequestMapping("/sys")
@Api(tags = { "错误日志" })
public class ErrorLogController {

	@Autowired
	private ErrorLogService errorLogService;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return R.ok()
	 */
	@SysLog("删除错误日志")
	@ApiOperation(value = "删除错误日志")
	@RequestMapping(value = "/errorLogs/{id}", method = RequestMethod.DELETE)
	@RequiresPermissions("sys:errorLog:delete")
	public R delete(@PathVariable("id") String id) {
		errorLogService.deleteById(id);
		return R.ok();
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@SysLog("批量删除错误日志")
	@ApiOperation(value = "批量删除错误日志")
	@RequestMapping(value = "/errorLogs/deleteBatch", method = RequestMethod.POST)
	@RequiresPermissions("sys:errorLog:deleteBatch")
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		errorLogService.deleteBatchIds(ids);
		return R.ok();
	}

	/**
	 * 查询
	 * 
	 * @param id
	 * @return R.ok().put("data", errorlogDTO)
	 */
	@SysLog("查询错误日志")
	@ApiOperation(value = "查询错误日志")
	@RequestMapping(value = "/errorLogs/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:errorLog:info")
	public R info(@PathVariable("id") String id) {
		ErrorLogDTO errorlogDTO = errorLogService.selectErrorLogDTOById(id);
		return R.ok().put("data", errorlogDTO);
	}

	/**
	 * 查询所有
	 * 
	 * @return R.ok()
	 */
	@ApiOperation(value = "查询所有")
	@RequestMapping(value = "/errorLogs", method = RequestMethod.GET)
	@RequiresPermissions("sys:errorLog:getAll")
	public R getAll() {
		List<ErrorLogDTO> errorlogDTOList = errorLogService.selectErrorLogDTOList();
		return R.ok().put("data", errorlogDTOList);
	}

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value = "分页查询")
	@RequestMapping(value = "/errorLogs/page", method = RequestMethod.GET)
	@RequiresPermissions("sys:errorLog:page")
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<ErrorLogDTO> pageInfo = new PageInfo<ErrorLogDTO>(errorLogService.selectErrorLogDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

}
