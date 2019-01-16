package ${package.Mapper};

<#assign className = table.mapperName?replace("Dao", "") />
<#assign classname = className?lower_case/>
<#assign entityDTO = "${className}DTO"/>
<#assign entitydto = "${classname}DTO"/>
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

import java.util.List;
import java.util.Map;

import ${package.Entity}.${entity};
import ${package.Entity}.${entityDTO};
import ${superMapperClassPackage};


/**
 * <p>
 * ${table.comment!} Dao 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
	
	List<${entityDTO}> select${entityDTO}Page(Map<String, Object> query);
	
	${entityDTO} select${entityDTO}ById(${columnType} ${pkName});
	
	List<${entityDTO}> select${entityDTO}List();
}
</#if>
