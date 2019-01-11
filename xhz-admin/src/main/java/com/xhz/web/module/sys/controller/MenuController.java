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
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.validator.ValidatorUtils;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xhz.web.module.sys.service.MenuService;
import com.xhz.web.module.sys.entity.MenuDO;
import com.xhz.web.module.sys.entity.MenuDTO;



/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-11
 */
 
@RestController
@RequestMapping("/sys")
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;
	
	/**
	 * 新增
	 * @param MenuDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public R insert(@RequestBody MenuDO menuDO) {
		ValidatorUtils.validateEntity(menuDO, AddGroup.class);
		menuService.insert(menuDO);
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param menuId
	 * @return R.ok()
	 */
	@RequestMapping(value = "/menu/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") Long menuId) {
		menuService.deleteById(menuId);
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param menuIds
	 * @return
	 */
	@RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<Long> menuIds) {
		menuService.deleteBatchIds(menuIds);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param MenuDO
	 * @return R.ok()
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.PATCH)
	public R update(@RequestBody MenuDO menuDO) {
		ValidatorUtils.validateEntity(menuDO, UpdateGroup.class);
		menuService.updateById(menuDO);
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param menuId
	 * @return R.ok().put("menu", menuDO)
	 */
	@RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") Long menuId) {
		MenuDO menuDO = menuService.selectById(menuId);
		return R.ok().put("menu", menuDO);
	}
	
	/**
	 * 查询所有
	 * @return R.ok()
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public R getAll() {
		List<MenuDTO> menuDTOList = menuService.selectAllMenu();
		return R.ok().put("data", menuDTOList);
		
//		List<MenuDO> menuDOList = menuService.selectList();
//		return R.ok().put("data", menuDOList);
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
		PageInfo<MenuDO> pageInfo = new PageInfo<MenuDO>(menuService.queryPage(query));
		return R.ok().put("page", pageInfo);
	}

}
