package com.xhz.web.module.develop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xhz.util.R;
import com.xhz.web.module.develop.service.DatabaseDocService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * <p>
 * 数据库文档 前端控制器
 * </p>
 *
 * @author zhangzm
 * @since 2019-01-23
 */
 
@RestController
@RequestMapping("/develop")
@Api(tags = {"数据库文档"})
public class DatabaseDocController {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseDocController.class);

	@Autowired
	private DatabaseDocService databaseDocService;
	
	
	/**
	 * 查询
	 * @param id
	 * @return R.ok().put("data", databaseDTO)
	 */
	@ApiOperation(value="查询")
	@RequestMapping(value = "/databaseDocs/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") String id) {
		databaseDocService.selectDatabaseDocById(id);
		return R.ok().put("data", null);
	}
	

}
