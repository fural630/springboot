package ${package.Entity};

import javax.validation.constraints.NotNull;
import com.xhz.validator.group.AddGroup;
import com.xhz.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>

<#assign className = entity?replace("DO", "") />
<#assign classname = className?lower_case/>
<#assign entityDTO = "${className}DTO"/>
<#assign entitydto = "${classname}DTO"/>

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
    <#if superEntityClass??>
@EqualsAndHashCode(callSuper = true)
    <#else>
@EqualsAndHashCode(callSuper = false)
    </#if>
@Accessors(chain = true)
</#if>
<#if swagger2>
@ApiModel(value="${entityDTO}对象", description="${table.comment!}")
</#if>
<#if superEntityClass??>
public class ${entityDTO} extends ${superEntityClass}<#if activeRecord><${entityDTO}></#if> {
<#elseif activeRecord>
public class ${entityDTO} extends Model<${entityDTO}> {
<#else>
public class ${entityDTO} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
	<#assign isNull = field.customMap.Null />
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    <#if swagger2>
    @ApiModelProperty(value = "${field.comment}"<#if isNull == "NO">, required = true</#if>)
    <#else>
    /**
     * ${field.comment}
     */
    </#if>
    </#if>
    <#if !field.keyFlag>
    	<#if isNull == "NO">
    @NotNull(groups = { AddGroup.class, UpdateGroup.class }, message = "${field.comment!''}不能为空")
    	</#if>
   		<#if field.customMap.DataLength??>
    @Length(max = ${field.customMap.DataLength}, groups = { AddGroup.class, UpdateGroup.class }, message = "${field.comment!''}最长度不允许超过${field.customMap.DataLength}")
    	</#if>
    <#else>
    @NotNull(groups = { UpdateGroup.class }, message = "修改时${field.comment!''}不能为空")
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

        <#if entityBuilderModel>
    public ${entityDTO} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
        return this;
        </#if>
    }
    </#list>
</#if>

<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
}
