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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import com.xhz.annotation.SysLog;
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.validator.ValidatorUtils;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xhz.web.module.sys.service.DeptService;
import com.xhz.web.module.sys.entity.DeptDTO;



/**
 * <p>
 * 机构管理 前端控制器 
 * </p>
 *
 * @author zhangzm
 * @since 2019-02-25
 */
 
@RestController
@RequestMapping("/sys")
@Api(tags = { "机构管理" })
public class DeptController {

	@Autowired
	private DeptService deptService;
	
	/**
	 * 新增机构
	 * @param deptDTO 
	 * @return R.ok()
	 */
	@SysLog("新增机构")
	@ApiOperation(value = "新增机构")
	@RequiresPermissions("sys:dept:insert")
	@RequestMapping(value = "/depts", method = RequestMethod.POST)
	public R insert(@RequestBody DeptDTO deptDTO) {
		ValidatorUtils.validateEntity(deptDTO, AddGroup.class);
		deptService.insertDeptDTO(deptDTO);
		return R.ok();
	}
	
	/**
	 * 删除机构
	 * @param deptId 
	 * @return R.ok()
	 */
	@SysLog("删除机构")
	@ApiOperation(value = "删除机构")
	@RequiresPermissions("sys:dept:delete")
	@RequestMapping(value = "/depts/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") String deptId) {
		deptService.deleteById(deptId);
		return R.ok();
	}
	
	/**
	 * 批量删除机构
	 * @param deptIds 
	 * @return R.ok()
	 */
	@SysLog("批量删除机构")
	@ApiOperation(value = "批量删除机构")
	@RequiresPermissions("sys:dept:deleteBatch")
	@RequestMapping(value = "/depts/deleteBatch", method = RequestMethod.POST)
	public R deleteBatch(@RequestBody List<String> deptIds) {
		deptService.deleteBatchIds(deptIds);
		return R.ok();
	}
	
	/**
	 * 修改机构
	 * @param deptDTO 
	 * @return R.ok()
	 */
	@SysLog("修改机构")
	@ApiOperation(value = "修改机构")
	@RequiresPermissions("sys:dept:update")
	@RequestMapping(value = "/depts", method = RequestMethod.PATCH)
	public R update(@RequestBody DeptDTO deptDTO) {
		ValidatorUtils.validateEntity(deptDTO, UpdateGroup.class);
		deptService.updateDeptDTOById(deptDTO);
		return R.ok();
	}
	
	/**
	 * 查询机构
	 * @param deptId 
	 * @return R.ok().put("data", deptDTO)
	 */
	@SysLog("查询机构")
	@ApiOperation(value = "查询机构")
	@RequiresPermissions("sys:dept:info")
	@RequestMapping(value = "/depts/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String deptId) {
		DeptDTO deptDTO = deptService.selectDeptDTOById(deptId);
		return R.ok().put("data", deptDTO);
	}
	
	/**
	 * 查询所有机构
	 * @return R.ok()
	 */
	@SysLog("查询所有机构")
	@ApiOperation(value = "查询所有机构")
	@RequiresPermissions("sys:dept:getAll")
	@RequestMapping(value = "/depts", method = RequestMethod.GET)
	public R getAll() {
		List<DeptDTO> deptDTOList = deptService.selectDeptDTOList();
		return R.ok().put("data", deptDTOList);
	}
	
	/**
	 * 分页查询机构
	 * @param params 
	 * @return R.ok().put("data", pageInfo);
	 */
	@SysLog("分页查询机构")
	@ApiOperation(value = "分页查询机构")
	@RequiresPermissions("sys:dept:page")
	@RequestMapping(value = "/depts/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<DeptDTO> pageInfo = new PageInfo<DeptDTO>(deptService.selectDeptDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

}
