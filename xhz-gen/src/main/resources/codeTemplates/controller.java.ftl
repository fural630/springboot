package ${package.Controller};

import java.util.List;
import java.util.Map;

<#if cfg.permission>
import org.apache.shiro.authz.annotation.RequiresPermissions;
</#if>

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
<#assign className = table.controllerName?replace("Controller", "") />
<#assign classname = className?lower_case/>
<#assign path = className?uncap_first/>
<#assign requestPath = "${path}s"/>
<#assign entityDTO = "${className}DTO"/>
<#assign entitydto = "${classname}DTO"/>
<#assign serviceName = table.serviceImplName />
<#assign servicename = table.serviceImplName?uncap_first />
<#assign entityName = table.entityName />
<#assign entityname = table.entityName?uncap_first />
<#assign pkName = ''/>
<#assign comment = ''/>
<#assign columnType = ''/>
<#list table.fields as field>
	<#if field.keyFlag>
		<#assign pkName = field.propertyName/>
		<#assign comment = field.comment/>
		<#assign columnType = field.columnType?lower_case?cap_first />
	</#if>
</#list>
<#assign insertMethod = "insert"/>
<#assign deleteMethod = "delete"/>
<#assign deleteBatchMethod = "deleteBatch"/>
<#assign updateMethod = "update"/>
<#assign infoMethod = "info"/>
<#assign getAllMethod = "getAll"/>
<#assign pageMethod = "page"/>
<#assign insertTitle = "新增${cfg.moduleChName!}"/>
<#assign deleteTitle = "删除${cfg.moduleChName!}"/>
<#assign deleteBatchTitle = "批量删除${cfg.moduleChName!}"/>
<#assign updateTitle = "修改${cfg.moduleChName!}"/>
<#assign infoTitle = "查询${cfg.moduleChName!}"/>
<#assign getAllTitle = "查询所有${cfg.moduleChName!}"/>
<#assign pageTitle = "分页查询${cfg.moduleChName!}"/>
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if cfg.sysLog>
import com.xhz.annotation.SysLog;
</#if>
import com.xhz.util.Query;
import com.xhz.util.R;
import com.xhz.validator.ValidatorUtils;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${package.ServiceImpl}.${table.serviceImplName};
import ${package.Entity}.${entityDTO};



/**
 * <p>
 * ${cfg.title!} 前端控制器 
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
 
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>")
<#if swagger2>
@Api(tags = { "${cfg.title!}" })
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

	@Autowired
	private ${serviceName} ${servicename};
	
	/**
	 * ${insertTitle}
	 * @param ${entitydto} 
	 * @return R.ok()
	 */
	<#if cfg.sysLog>
	@SysLog("${insertTitle}")
	</#if>
 	<#if swagger2>
	@ApiOperation(value = "${insertTitle}")
	</#if>
	<#if cfg.permission>
	@RequiresPermissions("${package.ModuleName}:${path}:${insertMethod}")
	</#if>
	@RequestMapping(value = "/${requestPath}", method = RequestMethod.POST)
	public R ${insertMethod}(@RequestBody ${entityDTO} ${entitydto}) {
		ValidatorUtils.validateEntity(${entitydto}, AddGroup.class);
		${servicename}.insert${entityDTO}(${entitydto});
		return R.ok();
	}
	
	/**
	 * ${deleteTitle}
	 * @param ${pkName} 
	 * @return R.ok()
	 */
	<#if cfg.sysLog>
	@SysLog("${deleteTitle}")
	</#if>
	<#if swagger2>
	@ApiOperation(value = "${deleteTitle}")
	</#if>
	<#if cfg.permission>
	@RequiresPermissions("${package.ModuleName}:${path}:${deleteMethod}")
	</#if>
	@RequestMapping(value = "/${requestPath}/{id}", method = RequestMethod.DELETE)
	public R ${deleteMethod}(@PathVariable("id") ${columnType} ${pkName}) {
		${servicename}.deleteById(${pkName});
		return R.ok();
	}
	
	/**
	 * ${deleteBatchTitle}
	 * @param ${pkName}s 
	 * @return R.ok()
	 */
	<#if cfg.sysLog>
	@SysLog("${deleteBatchTitle}")
	</#if>
	<#if swagger2>
	@ApiOperation(value = "${deleteBatchTitle}")
	</#if>
	<#if cfg.permission>
	@RequiresPermissions("${package.ModuleName}:${path}:${deleteBatchMethod}")
	</#if>
	@RequestMapping(value = "/${requestPath}/deleteBatch", method = RequestMethod.POST)
	public R ${deleteBatchMethod}(@RequestBody List<${columnType}> ${pkName}s) {
		${servicename}.deleteBatchIds(${pkName}s);
		return R.ok();
	}
	
	/**
	 * ${updateTitle}
	 * @param ${entitydto} 
	 * @return R.ok()
	 */
	<#if cfg.sysLog>
	@SysLog("${updateTitle}")
	</#if>
	<#if swagger2>
	@ApiOperation(value = "${updateTitle}")
	</#if>
	<#if cfg.permission>
	@RequiresPermissions("${package.ModuleName}:${path}:${updateMethod}")
	</#if>
	@RequestMapping(value = "/${requestPath}", method = RequestMethod.PATCH)
	public R ${updateMethod}(@RequestBody ${entityDTO} ${entitydto}) {
		ValidatorUtils.validateEntity(${entitydto}, UpdateGroup.class);
		${servicename}.update${entityDTO}ById(${entitydto});
		return R.ok();
	}
	
	/**
	 * ${infoTitle}
	 * @param ${pkName} 
	 * @return R.ok().put("data", ${entitydto})
	 */
	<#if cfg.sysLog>
	@SysLog("${infoTitle}")
	</#if>
	<#if swagger2>
	@ApiOperation(value = "${infoTitle}")
	</#if>
	<#if cfg.permission>
	@RequiresPermissions("${package.ModuleName}:${path}:${infoMethod}")
	</#if>
	@RequestMapping(value = "/${requestPath}/{id}", method = RequestMethod.GET)
	public R ${infoMethod}(@PathVariable("id") ${columnType} ${pkName}) {
		${entityDTO} ${entitydto} = ${servicename}.select${entityDTO}ById(${pkName});
		return R.ok().put("data", ${entitydto});
	}
	
	/**
	 * ${getAllTitle}
	 * @return R.ok()
	 */
	<#if cfg.sysLog>
	@SysLog("${getAllTitle}")
	</#if>
	<#if swagger2>
	@ApiOperation(value = "${getAllTitle}")
	</#if>
	<#if cfg.permission>
	@RequiresPermissions("${package.ModuleName}:${path}:${getAllMethod}")
	</#if>
	@RequestMapping(value = "/${requestPath}", method = RequestMethod.GET)
	public R ${getAllMethod}() {
		List<${entityDTO}> ${entitydto}List = ${servicename}.select${entityDTO}List();
		return R.ok().put("data", ${entitydto}List);
	}
	
	/**
	 * ${pageTitle}
	 * @param params 
	 * @return R.ok().put("data", pageInfo);
	 */
	<#if cfg.sysLog>
	@SysLog("${pageTitle}")
	</#if>
	<#if swagger2>
	@ApiOperation(value = "${pageTitle}")
	</#if>
	<#if cfg.permission>
	@RequiresPermissions("${package.ModuleName}:${path}:${pageMethod}")
	</#if>
	@RequestMapping(value = "/${requestPath}/page", method = RequestMethod.GET)
	public R ${pageMethod}(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<${entityDTO}> pageInfo = new PageInfo<${entityDTO}>(${servicename}.select${entityDTO}Page(query));
		return R.ok().put("data", pageInfo);
	}

}
</#if>
