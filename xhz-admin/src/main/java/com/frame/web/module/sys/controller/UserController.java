package com.frame.web.module.sys.controller;

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
import com.frame.util.Query;
import com.frame.util.R;
import com.frame.validator.ValidatorUtils;
import com.frame.validator.group.AddGroup;
import com.frame.validator.group.UpdateGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.frame.web.module.sys.service.UserService;
import com.frame.web.module.sys.entity.UserDO;



/**
 * <p>
 * InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2018-12-16
 */
 
@RestController
@RequestMapping("/sys/user")
@Api(tags = {"用户管理"})
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 新增
	 * @param UserDO
	 * @return R.ok()
	 */
    @ApiOperation(value="添加用户")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public R save(@RequestBody UserDO userDO) {
		ValidatorUtils.validateEntity(userDO, AddGroup.class);
		userService.insert(userDO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return R.ok()
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public R delete(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<Long> ids) {
		userService.deleteBatchIds(ids);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param UserDO
	 * @return R.ok()
	 */
	@ApiOperation(value="修改用户")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public R update(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true)UserDO userDO) {
		ValidatorUtils.validateEntity(userDO, UpdateGroup.class);
		userService.updateById(userDO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("user", userDO)
	 */
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") Long id) {
		UserDO userDO = userService.selectById(id);
		return R.ok().put("user", userDO);
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return R.ok().put("page", pageInfo);
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<UserDO> pageInfo = new PageInfo<UserDO>(userService.queryPage(query));
		return R.ok().put("page", pageInfo);
	}

}
