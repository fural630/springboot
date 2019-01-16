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

import com.xhz.web.module.sys.service.MenuService;
import com.xhz.web.module.sys.entity.MenuDTO;



/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-16
 */
 
@RestController
@RequestMapping("/sys")
@Api(tags = {"菜单管理"})
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;
	
	/**
	 * 新增
	 * @param MenuDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="新增")
	@RequestMapping(value = "/menus", method = RequestMethod.POST)
	public R insert(@RequestBody MenuDTO menuDTO) {
		ValidatorUtils.validateEntity(menuDTO, AddGroup.class);
		menuService.insertMenuDTO(menuDTO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param menuId
	 * @return R.ok()
	 */
	@ApiOperation(value="删除")
	@RequestMapping(value = "/menus/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") Long menuId) {
		menuService.deleteById(menuId);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param menuIds
	 * @return
	 */
	@ApiOperation(value="批量删除")
	@RequestMapping(value = "/menus/deleteBatch", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<Long> menuIds) {
		menuService.deleteBatchIds(menuIds);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param MenuDTO
	 * @return R.ok()
	 */
	@ApiOperation(value="修改")
	@RequestMapping(value = "/menus", method = RequestMethod.PATCH)
	public R update(@RequestBody MenuDTO menuDTO) {
		ValidatorUtils.validateEntity(menuDTO, UpdateGroup.class);
		menuService.updateMenuDTOById(menuDTO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param menuId
	 * @return R.ok().put("data", menuDTO)
	 */
	@ApiOperation(value="查询")
	@RequestMapping(value = "/menus/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") Long menuId) {
		MenuDTO menuDTO = menuService.selectMenuDTOById(menuId);
		return R.ok().put("data", menuDTO);
	}
	
	/**
	 * 查询所有
	 * @return R.ok()
	 */
	@ApiOperation(value="查询所有")
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public R getAll(@RequestParam(required = false) Map<String, Object> params) {
		List<MenuDTO> menuDTOList = menuService.selectMenuDTOList(params);
		return R.ok().put("data", menuDTOList);
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value="分页查询")
	@RequestMapping(value = "/menus/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<MenuDTO> pageInfo = new PageInfo<MenuDTO>(menuService.selectMenuDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

}