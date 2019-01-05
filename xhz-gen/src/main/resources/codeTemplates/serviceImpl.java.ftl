package ${package.ServiceImpl};

import java.util.List;
import java.util.Map;

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#assign daoName = table.mapperName />
<#assign daoname = table.mapperName?uncap_first />
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
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} {

	@Autowired
	private ${daoName} ${daoname};
	
	public void insert(${entityName} ${entityname}) {
		${daoname}.insert(${entityname});
	}

	public void deleteById(${columnType} ${pkName}) {
		${daoname}.deleteById(${pkName});
	}

	public void deleteBatchIds(List<${columnType}> ${pkName}s) {
		${daoname}.deleteBatchIds(${pkName}s);
	}

	public void updateById(${entityName} ${entityname}) {
		${daoname}.updateById(${entityname});
	}

	public ${entityName} selectById(${columnType} ${pkName}) {
		return ${daoname}.selectById(${pkName});
	}
	
	public List<${entityName}> queryPage(Map<String, Object> query) {
		return ${daoname}.queryPage(query);
	}
}
</#if>
