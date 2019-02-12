package ${package.ServiceImpl};

import java.util.List;
import java.util.Map;

<#assign className = table.serviceImplName?replace("Service", "") />
<#assign classname = className?lower_case/>
<#assign entityDTO = "${className}DTO"/>
<#assign entitydto = "${classname}DTO"/>
<#assign daoName = table.mapperName />
<#assign daoname = table.mapperName?uncap_first />
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

import ${package.Entity}.${entity};
import ${package.Entity}.${entityDTO};
import ${package.Mapper}.${table.mapperName};
import com.xhz.util.CopyUtil;

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
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} {

	@Autowired
	private ${daoName} ${daoname};
	
	public void insert${entityDTO}(${entityDTO} ${entitydto}) {
		${entityName} ${entityname} = CopyUtil.copyProperties(${entitydto}, ${entityName}.class);
		this.insert(${entityname});
	}
	
	public void update${entityDTO}ById(${entityDTO} ${entitydto}) {
		${entityName} ${entityname} = CopyUtil.copyProperties(${entitydto}, ${entityName}.class);
		this.updateById(${entityname});
	}
	
	public ${entityDTO} select${entityDTO}ById(${columnType} ${pkName}) {
		return ${daoname}.select${entityDTO}ById(${pkName});
	}
	
	public List<${entityDTO}> select${entityDTO}List() {
		return ${daoname}.select${entityDTO}List();
	}
	
	public List<${entityDTO}> select${entityDTO}Page(Map<String, Object> query) {
		return ${daoname}.select${entityDTO}Page(query);
	}
	
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
	
	public List<${entityName}> selectList() {
		return ${daoname}.selectList(null);
	}
}
</#if>
