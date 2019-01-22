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
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * 用户 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-21
 */
 
@RestController
@RequestMapping("/sys")
@Api(tags = {"用户管理"})
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 新增
	 * @param UserDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="新增")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public R insert(@RequestBody UserDTO userDTO) {
		ValidatorUtils.validateEntity(userDTO, AddGroup.class);
		userService.insertUserDTO(userDTO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@ApiOperation(value="删除")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="批量删除")
	@RequestMapping(value = "/users/deleteBatch", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<Long> ids) {
		userService.deleteBatchIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param UserDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="修改")
	@RequestMapping(value = "/users", method = RequestMethod.PATCH)
	public R update(@RequestBody UserDTO userDTO) {
		ValidatorUtils.validateEntity(userDTO, UpdateGroup.class);
		userService.updateUserDTOById(userDTO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("data", userDTO)
	 */
	@ApiOperation(value="查询")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") Long id) {
		UserDTO userDTO = userService.selectUserDTOById(id);
		return R.ok().put("data", userDTO);
	}
	
	/**
	 * 查询所有
	 * @return R.ok()
	 */
	@ApiOperation(value="查询所有")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public R getAll() {
		List<UserDTO> userDTOList = userService.selectUserDTOList();
		return R.ok().put("data", userDTOList);
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value="分页查询")
	@RequestMapping(value = "/users/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<UserDTO> pageInfo = new PageInfo<UserDTO>(userService.selectUserDTOPage(query));
		return R.ok().put("data", pageInfo);
	}
	
	/**
	 * 禁用用户
	 * @param id
	 * @return R.ok()
	 */
	@ApiOperation(value="启用用户")
	@RequestMapping(value = "/users/enable/{id}", method = RequestMethod.GET)
	public R enable(@PathVariable("id") Long id) {
		userService.enableById(id);
		return R.ok();
	}
	
	/**
	 * 禁用用户
	 * @param id
	 * @return R.ok()
	 */
	@ApiOperation(value="禁用用户")
	@RequestMapping(value = "/users/disable/{id}", method = RequestMethod.GET)
	public R disable(@PathVariable("id") Long id) {
		userService.disableById(id);
		return R.ok();
	}

}
