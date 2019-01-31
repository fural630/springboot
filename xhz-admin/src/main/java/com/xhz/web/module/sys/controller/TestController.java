package com.xhz.web.module.sys.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xhz.annotation.SysLog;
import com.xhz.util.R;
import com.xhz.util.RRException;
import com.xhz.web.module.sys.entity.MenuDTO;
import com.xhz.web.module.sys.service.TestService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sys")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	/**
	 * 新增
	 * @param MenuDTO
	 * @return R.ok() 
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@SysLog("新增")
	public R insert(@RequestBody MenuDTO menuDTO) {
		String aString = "0";
		System.out.println(1/Integer.parseInt(aString));
		testService.test();
		System.out.println("=====> 28");
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param menuId
	 * @return R.ok().put("data", menuDTO)
	 */
	@ApiOperation(value="查询")
	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	@SysLog("查询")
	public R info(@PathVariable("id") String menuId) {
		String aString = "0";
//		System.out.println(1/Integer.parseInt(aString));
//		if (aString.equals("0")) {
//			throw new RRException("aString 不能为0");
//		}
		testService.test();
		System.out.println("=====> 49");
		return R.ok();
	}

}
