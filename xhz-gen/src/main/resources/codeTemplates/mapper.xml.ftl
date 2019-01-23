<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#assign className = table.mapperName?replace("Dao", "") />
<#assign classname = className?lower_case/>
<#assign entityDTO = "${className}DTO"/>
<#assign entitydto = "${classname}DTO"/>
<#assign pkName = ''/>
<#assign pkname = ''/>
<#assign comment = ''/>
<#assign columnType = ''/>
<#list table.fields as field>
	<#if field.keyFlag>
		<#assign pkName = field.name/>
		<#assign pkname = field.propertyName/>
		<#assign comment = field.comment/>
		<#assign columnType = field.columnType?lower_case?cap_first />
	</#if>
</#list>

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
    <result column="${field.name}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
<#list table.commonFields as field>
        ${field.name},
</#list>
        ${table.fieldNames}
    </sql>

</#if>

	<select id="select${entityDTO}Page" resultType="${package.Entity}.${entityDTO}">
    	select * from ${table.name}
    	<where>
    		1=1
    	</where>
    	<choose>
            <when test="field != null and field.trim() != ''">
                order by ${r"${field}"} ${r"${order}"}
            </when>
            <otherwise>
                order by ${pkName} desc
            </otherwise>
        </choose>
    </select>
    
    <select id="select${entityDTO}List" resultType="${package.Entity}.${entityDTO}">
		select * from ${table.name}
	</select>
	
	<select id="select${entityDTO}ById" resultType="${package.Entity}.${entityDTO}">
		select * from ${table.name} where ${pkName} = ${r"#{"}${pkname}${r"}"}
	</select>

</mapper>
