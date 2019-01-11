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
import ${package.Entity}.${entity};



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
<#assign className = table.controllerName?replace("Controller", "") />
<#assign classname = className?lower_case>
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
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>")
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
	 * @param ${entityName}
	 * @return R.ok()
	 */
	@RequestMapping(value = "/${classname}", method = RequestMethod.POST)
	public R insert(@RequestBody ${entityName} ${entityname}) {
		ValidatorUtils.validateEntity(${entityname}, AddGroup.class);
		${servicename}.insert(${entityname});
		return R.ok();
	}
	
	/**
	 * 删除
	 * @param ${pkName}
	 * @return R.ok()
	 */
	@RequestMapping(value = "/${classname}/{id}", method = RequestMethod.DELETE)
	public R delete(@PathVariable("id") ${columnType} ${pkName}) {
		${servicename}.deleteById(${pkName});
		return R.ok();
	}
	
	/**
	 * 批量删除
	 * @param ${pkName}s
	 * @return
	 */
	@RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
	public R deleteBatchByIds(@RequestBody List<${columnType}> ${pkName}s) {
		${servicename}.deleteBatchIds(${pkName}s);
		return R.ok();
	}
	
	/**
	 * 修改
	 * @param ${entityName}
	 * @return R.ok()
	 */
	@RequestMapping(value = "/${classname}", method = RequestMethod.PATCH)
	public R update(@RequestBody ${entityName} ${entityname}) {
		ValidatorUtils.validateEntity(${entityname}, UpdateGroup.class);
		${servicename}.updateById(${entityname});
		return R.ok();
	}
	
	/**
	 * 查询
	 * @param ${pkName}
	 * @return R.ok().put("${classname}", ${entityname})
	 */
	@RequestMapping(value = "/${classname}/{id}", method = RequestMethod.GET)
	public R info(@PathVariable("id") ${columnType} ${pkName}) {
		${entityName} ${entityname} = ${servicename}.selectById(${pkName});
		return R.ok().put("${classname}", ${entityname});
	}
	
	/**
	 * 查询所有
	 * @return R.ok()
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public R getAll() {
		List<${entityName}> ${entityname}List = ${servicename}.selectList();
		return R.ok().put("data", ${entityname}List);
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
		PageInfo<${entityName}> pageInfo = new PageInfo<${entityName}>(${servicename}.queryPage(query));
		return R.ok().put("page", pageInfo);
	}

}
</#if>
