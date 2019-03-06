package com.xhz.web.module.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xhz.constant.Constant;
import com.xhz.util.R;
import com.xhz.web.controller.AbstractController;
import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.service.MenuService;
import com.xhz.web.module.sys.service.RoleMenuService;



/**
 * <p>
 * 角色菜单管理 前端控制器 
 * </p>
 *
 * @author zhangzm
 * @since 2019-03-06
 */
 
@RestController
@RequestMapping("/sys")
public class RoleMenuController extends AbstractController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuService roleMenuService;
	
	/**
	 * 查询当前登录人所拥有的菜单
	 * @return R.ok()
	 */
	@RequestMapping(value = "/roleMenus", method = RequestMethod.GET)
	public R getAll() {
		List<MenuDO> menuDOList = new ArrayList<MenuDO>();
		if (getUserId().equals(Constant.SUPER_ADMIN)) {
			menuDOList = menuService.selectEnableMemu();
		}
		return R.ok().put("data", menuDOList);
	}
	
	/**
	 * 查询
	 * 
	 * @param id
	 * @return R.ok().put("data", recordlogDTO)
	 */
	@RequestMapping(value = "/roleMenus/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		List<String> menuIdList = roleMenuService.selectByRoleId(id);
		return R.ok().put("data", menuIdList);
	}
	
	
	
}
