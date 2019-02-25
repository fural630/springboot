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
import com.xhz.validator.ValidatorUtils;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import com.xhz.web.module.sys.entity.UserDTO;
import com.xhz.web.module.sys.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */

@RestController
@RequestMapping("/sys")
@Api(tags = { "用户信息" })
public class UserController {


	@Autowired
	private UserService userService;

	/**
	 * 新增
	 * 
	 * @param UserDTO
	 * @return R.ok()
	 */
	@SysLog("新增用户")
	@ApiOperation(value = "新增用户")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@RequiresPermissions("sys:user:insert")
	public R insert(@RequestBody UserDTO userDTO) {
		ValidatorUtils.validateEntity(userDTO, AddGroup.class);
		userService.insertUserDTO(userDTO);
		return R.ok();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return R.ok()
	 */
	@SysLog("删除用户")
	@ApiOperation(value = "删除用户")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@RequiresPermissions("sys:user:delete")
	public R delete(@PathVariable("id") String id) {
		userService.deleteById(id);
		return R.ok();
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@SysLog("批量删除用户")
	@ApiOperation(value = "批量删除用户")
	@RequestMapping(value = "/users/deleteBatch", method = RequestMethod.POST)
	@RequiresPermissions("sys:user:deleteBatch")
	public R deleteBatchByIds(@RequestBody List<String> ids) {
		userService.deleteBatchIds(ids);
		return R.ok();
	}

	/**
	 * 修改
	 * 
	 * @param UserDTO
	 * @return R.ok()
	 */
	@SysLog("修改用户")
	@ApiOperation(value = "修改用户")
	@RequestMapping(value = "/users", method = RequestMethod.PATCH)
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody UserDTO userDTO) {
		ValidatorUtils.validateEntity(userDTO, UpdateGroup.class);
		userService.updateUserDTOById(userDTO);
		return R.ok();
	}

	/**
	 * 查询
	 * 
	 * @param id
	 * @return R.ok().put("data", userDTO)
	 */
	@SysLog("查询用户")
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("id") String id) {
		UserDTO userDTO = userService.selectUserDTOById(id);
		return R.ok().put("data", userDTO);
	}

	/**
	 * 查询所有
	 * 
	 * @return R.ok()
	 */
	@ApiOperation(value = "查询所有")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@RequiresPermissions("sys:user:getAll")
	public R getAll() {
		List<UserDTO> userDTOList = userService.selectUserDTOList();
		return R.ok().put("data", userDTOList);
	}

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value = "分页查询")
	@RequestMapping(value = "/users/page", method = RequestMethod.GET)
	@RequiresPermissions("sys:user:page")
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<UserDTO> pageInfo = new PageInfo<UserDTO>(userService.selectUserDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

	/**
	 * 启用用户
	 * 
	 * @param id
	 * @return R.ok()
	 */
	@SysLog("启用用户")
	@ApiOperation(value = "启用用户")
	@RequestMapping(value = "/users/enable/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:user:enable")
	public R enable(@PathVariable("id") String id) {
		userService.enableById(id);
		return R.ok();
	}

	/**
	 * 禁用用户
	 * 
	 * @param id
	 * @return R.ok()
	 */
	@SysLog("禁用用户")
	@ApiOperation(value = "禁用用户")
	@RequestMapping(value = "/users/disable/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:user:disable")
	public R disable(@PathVariable("id") String id) {
		userService.disableById(id);
		return R.ok();
	}

}
