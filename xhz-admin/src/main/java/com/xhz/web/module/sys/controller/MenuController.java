package com.xhz.web.module.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.xhz.constant.Constant;
import com.xhz.constant.Constant.YESNO;
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.validator.ValidatorUtils;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.entity.MenuDTO;
import com.xhz.web.module.sys.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */

@RestController
@RequestMapping("/sys")
@Api(tags = { "菜单管理" })
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 新增
	 * 
	 * @param MenuDTO
	 * @return R.ok()
	 */
	@SysLog("新增菜单")
	@ApiOperation(value = "新增")
	@RequestMapping(value = "/menus", method = RequestMethod.POST)
	@RequiresPermissions("sys:menu:insert")
	public R insert(@RequestBody MenuDTO menuDTO) {
		ValidatorUtils.validateEntity(menuDTO, AddGroup.class);
		menuService.insertMenuDTO(menuDTO);
		return R.ok();
	}

	/**
	 * 删除
	 * 
	 * @param menuId
	 * @return R.ok()
	 */
	@SysLog("删除菜单")
	@ApiOperation(value = "删除")
	@RequestMapping(value = "/menus/{id}", method = RequestMethod.DELETE)
	@RequiresPermissions("sys:menu:delete")
	public R delete(@PathVariable("id") String menuId) {
		menuService.deleteById(menuId);
		return R.ok();
	}

	/**
	 * 批量删除
	 * 
	 * @param menuIds
	 * @return
	 */
	@SysLog("批量删除菜单")
	@ApiOperation(value = "批量删除")
	@RequestMapping(value = "/menus/deleteBatch", method = RequestMethod.POST)
	@RequiresPermissions("sys:menu:deleteBatch")
	public R deleteBatchByIds(@RequestBody List<String> menuIds) {
		menuService.deleteBatchIds(menuIds);
		return R.ok();
	}

	/**
	 * 修改
	 * 
	 * @param MenuDTO
	 * @return R.ok()
	 */
	@SysLog("修改菜单")
	@ApiOperation(value = "修改")
	@RequestMapping(value = "/menus", method = RequestMethod.PATCH)
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody MenuDTO menuDTO) {
		ValidatorUtils.validateEntity(menuDTO, UpdateGroup.class);
		menuService.updateMenuDTOById(menuDTO);
		return R.ok();
	}

	/**
	 * 查询
	 * 
	 * @param menuId
	 * @return R.ok().put("data", menuDTO)
	 */
	@SysLog("查询菜单")
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/menus/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:info")
	public R info(@PathVariable("id") String menuId) {
		MenuDTO menuDTO = menuService.selectMenuDTOById(menuId);
		return R.ok().put("data", menuDTO);
	}

	/**
	 * 查询所有
	 * 
	 * @return R.ok()
	 */
	@ApiOperation(value = "查询所有")
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:getAll")
	public R getAll(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<MenuDTO> menuDTOList = menuService.selectMenuDTOList(query);
		return R.ok().put("data", menuDTOList);
	}

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	@ApiOperation(value = "分页查询")
	@RequestMapping(value = "/menus/page", method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:page")
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<MenuDTO> pageInfo = new PageInfo<MenuDTO>(menuService.selectMenuDTOPage(query));
		return R.ok().put("data", pageInfo);
	}

	/**
	 * 禁用菜单
	 * 
	 * @param menuId
	 * @return R.ok()
	 */
	@SysLog("禁用菜单")
	@ApiOperation(value = "禁用菜单", notes = "禁用菜单，子菜单都将被禁用")
	@RequestMapping(value = "/menus/disable/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:disable")
	public R disable(@PathVariable("id") String menuId) {
		menuService.disableMenuById(menuId);
		return R.ok();
	}

	/**
	 * 禁用菜单
	 * 
	 * @param menuId
	 * @return R.ok()
	 */
	@SysLog("启用菜单")
	@ApiOperation(value = "启用菜单", notes = "启用菜单,子菜单都将被启用")
	@RequestMapping(value = "/menus/enable/{id}", method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:enable")
	public R enable(@PathVariable("id") String menuId) {
		menuService.enableMenuById(menuId);
		return R.ok();
	}

	/**
	 * 查询所有启用菜单
	 * 
	 * @param menuId
	 * @return R.ok()
	 */
	@ApiOperation(value = "查询所有启用菜单", notes = "查询所有启用菜单")
	@RequestMapping(value = "/menus/enable", method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:enableMemu")
	public R enableMemu() {
		List<MenuDO> menuDOList = menuService.selectEnableMemu();
		return R.ok().put("data", menuDOList);
	}

	/**
	 * 批量修改按钮权限
	 * 
	 * @param menuDTOList
	 * @return R.ok()
	 */
	@SysLog("批量修改按钮权限")
	@ApiOperation(value = "批量修改按钮权限", notes = "批量修改按钮权限")
	@RequestMapping(value = "/menus", method = RequestMethod.PUT)
	@RequiresPermissions("sys:menu:updatePerms")
	public R updatePerms(@RequestBody List<MenuDTO> menuDTOList) {
		if (CollectionUtils.isNotEmpty(menuDTOList)) {
			String parentId = menuDTOList.get(0).getParentId();
			List<MenuDO> childMenuDTOList = menuService.selectByParentId(parentId);
			if (CollectionUtils.isNotEmpty(childMenuDTOList)) {
				for (MenuDO menuDO : childMenuDTOList) {
					menuService.deleteById(menuDO.getMenuId());
				}
			}
			for (MenuDTO menuDTO : menuDTOList) {
				menuDTO.setIsDeleted(YESNO.NO.getValue());
				menuDTO.setType(Constant.MenuType.BUTTON.getValue());
				menuService.insertMenuDTO(menuDTO);
			}
		}
		return R.ok();
	}
	
	@ApiOperation(value = "查询子菜单", notes = "查询子菜单")
	@RequiresPermissions("sys:menu:querychildMenu")
	@RequestMapping(value = "/menus/child/{id}", method = RequestMethod.GET)
	public R querychildMenu(@PathVariable("id") String parentId) {
		List<MenuDO> menuDOList = menuService.selectByParentId(parentId);
		return R.ok().put("data", menuDOList);
	}

}
