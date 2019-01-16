package ${package.Controller};

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

<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>

<#assign className = table.controllerName?replace("Controller", "") />
<#assign classname = className?lower_case/>
<#assign requestPath = "${classname}s"/>
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
	<#if field.keyIdentityFlag>
		<#assign pkName = field.propertyName/>
		<#assign comment = field.comment/>
		<#assign columnType = field.columnType?lower_case?cap_first />
	</#if>
</#list>


<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
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
 * ${table.comment!} 前端控制器
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
@Api(tags = {"${table.comment!}"})
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

	private static final Logger logger = LoggerFactory.getLogger(${table.controllerName}.class);

	@Autowired
	private ${serviceName} ${servicename};
	
	/**
	 * 新增
	 * @param ${entityDTO}
	 * @return R.ok()
	 */
 	<#if swagger2>
	@ApiOperation(value="新增")
	</#if>
	@RequestMapping(value = "/${requestPath}", method = RequestMethod.POST)
	public R insert(@RequestBody ${entityDTO} ${entitydto}) {
		ValidatorUtils.validateEntity(${entitydto}, AddGroup.class);
		${servicename}.insert${entityDTO}(${entitydto});
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param ${pkName}
	 * @return R.ok()
	 */
	<#if swagger2>
	@ApiOperation(value="删除")
	</#if>
	@RequestMapping(value = "/${requestPath}/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") ${columnType} ${pkName}) {
		${servicename}.deleteById(${pkName});
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ${pkName}s
	 * @return
	 */
	<#if swagger2>
	@ApiOperation(value="批量删除")
	</#if>
	@RequestMapping(value = "/${requestPath}/deleteBatch", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<${columnType}> ${pkName}s) {
		${servicename}.deleteBatchIds(${pkName}s);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param ${entityDTO}
	 * @return R.ok()
	 */
	<#if swagger2>
	@ApiOperation(value="修改")
	</#if>
	@RequestMapping(value = "/${requestPath}", method = RequestMethod.PATCH)
	public R update(@RequestBody ${entityDTO} ${entitydto}) {
		ValidatorUtils.validateEntity(${entitydto}, UpdateGroup.class);
		${servicename}.update${entityDTO}ById(${entitydto});
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param ${pkName}
	 * @return R.ok().put("data", ${entitydto})
	 */
	<#if swagger2>
	@ApiOperation(value="查询")
	</#if>
	@RequestMapping(value = "/${requestPath}/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") ${columnType} ${pkName}) {
		${entityDTO} ${entitydto} = ${servicename}.select${entityDTO}ById(${pkName});
		return R.ok().put("data", ${entitydto});
	}
	
	/**
	 * 查询所有
	 * @return R.ok()
	 */
	<#if swagger2>
	@ApiOperation(value="查询所有")
	</#if>
	@RequestMapping(value = "/${requestPath}", method = RequestMethod.GET)
	public R getAll() {
		List<${entityDTO}> ${entitydto}List = ${servicename}.select${entityDTO}List();
		return R.ok().put("data", ${entitydto}List);
	}
	
	/**
	 * 分页查询
	 * @param params
	 * @return R.ok().put("data", pageInfo);
	 */
	<#if swagger2>
	@ApiOperation(value="分页查询")
	</#if>
	@RequestMapping(value = "/${requestPath}/page", method = RequestMethod.GET)
	public R page(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageHelper.startPage(query.getPage(), query.getLimit());
		PageInfo<${entityDTO}> pageInfo = new PageInfo<${entityDTO}>(${servicename}.select${entityDTO}Page(query));
		return R.ok().put("data", pageInfo);
	}

}
</#if>
