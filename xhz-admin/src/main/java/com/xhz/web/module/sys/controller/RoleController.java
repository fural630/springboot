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
import com.xhz.util.Dumper;
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.validator.ValidatorUtils;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xhz.web.module.sys.service.RoleService;
import com.xhz.web.controller.AbstractController;
import com.xhz.web.module.sys.entity.RoleDTO;



/**
 * <p>
 * 角色管理 前端控制器 
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-05
 */
 
@RestController
@RequestMapping("/sys")
@Api(tags = { "角色管理" })
public class RoleController extends AbstractController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * 新增角色
	 * @param roleDTO 
	 * @return R.ok()
	 */
	@SysLog("新增角色")
	@ApiOperation(value = "新增角色")
	@RequiresPermissions("sys:role:insert")
	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public R insert(@RequestBody RoleDTO roleDTO) {
		ValidatorUtils.validateEntity(roleDTO, AddGroup.class);
		roleService.insertRoleDTO(roleDTO);
		return R.ok();
	}
	
	/**
	 * 删除角色
	 * @param roleId 
	 * @return R.ok()
	 */
	@SysLog("删除角色")
	@ApiOperation(value = "删除角色")
	@RequiresPermissions("sys:role:delete")
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") String roleId) {
		roleService.deleteById(roleId);
		return R.ok();
	}
	
	/**
	 * 批量删除角色
	 * @param roleIds 
	 * @return R.ok()
	 */
	@SysLog("批量删除角色")
	@ApiOperation(value = "批量删除角色")
	@RequiresPermissions("sys:role:deleteBatch")
	@RequestMapping(value = "/roles/deleteBatch", method = RequestMethod.POST)
	public R deleteBatch(@RequestBody List<String> roleIds) {
		roleService.deleteBatchIds(roleIds);
		return R.ok();
	}
	
	/**
	 * 修改角色
	 * @param roleDTO 
	 * @return R.ok()
	 */
	@SysLog("修改角色")
	@ApiOperation(value = "修改角色")
	@RequiresPermissions("sys:role:update")
	@RequestMapping(value = "/roles", method = RequestMethod.PATCH)
	public R update(@RequestBody RoleDTO roleDTO) {
		ValidatorUtils.validateEntity(roleDTO, UpdateGroup.class);
		roleService.updateRoleDTOById(roleDTO);
		return R.ok();
	}
	
	/**
	 * 查询角色
	 * @param roleId 
	 * @return R.ok().put("data", roleDTO)
	 */
	@SysLog("查询角色")
	@ApiOperation(value = "查询角色")
	@RequiresPermissions("sys:role:info")
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String roleId) {
		RoleDTO roleDTO = roleService.selectRoleDTOById(roleId);
		return R.ok().put("data", roleDTO);
	}
	
	/**
	 * 查询所有角色
	 * @return R.ok()
	 */
	@SysLog("查询所有角色")
	@ApiOperation(value = "查询所有角色")
	@RequiresPermissions("sys:role:getAll")
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public R getAll() {
		List<RoleDTO> roleDTOList = roleService.selectRoleDTOList();
		return R.ok().put("data", roleDTOList);
	}
	
	/**
	 * 分页查询角色
	 * @param params 
	 * @return R.ok().put("data", pageInfo);
	 */
	@SysLog("分页查询角色")
	@ApiOperation(value = "分页查询角色")
	@RequiresPermissions("sys:role:page")
	@RequestMapping(value = "/roles/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Dumper.dump(getUser());
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<RoleDTO> pageInfo = new PageInfo<RoleDTO>(roleService.selectRoleDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

}
