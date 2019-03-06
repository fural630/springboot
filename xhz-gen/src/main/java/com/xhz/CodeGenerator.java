package com.xhz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.xhz.util.DatabaseUtil;

public class CodeGenerator {

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		ResourceBundle rb = ResourceBundle.getBundle("codeGenerator");
		String author = rb.getString("author");
		boolean fileOverride = Boolean.parseBoolean(rb.getString("fileOverride"));
		boolean open = Boolean.parseBoolean(rb.getString("open"));
		boolean baseResultMap = Boolean.parseBoolean(rb.getString("baseResultMap"));
		boolean baseColumnList = Boolean.parseBoolean(rb.getString("baseColumnList"));
		String entityName = rb.getString("entityName");
		String mapperName = rb.getString("mapperName");
		String xmlName = rb.getString("xmlName");
		String serviceName = rb.getString("serviceName");
		String serviceImplName = rb.getString("serviceImplName");
		String controllerName = rb.getString("controllerName");

		String url = rb.getString("url");
		String driverName = rb.getString("driverName");
		String username = rb.getString("username");
		String password = rb.getString("password");

		String parent = rb.getString("parent");
		String moduleName = rb.getString("moduleName");
		String entity = rb.getString("entity");
		String service = rb.getString("service");
		String serviceImpl = rb.getString("serviceImpl");
		String mapper = rb.getString("mapper");
		String xml = rb.getString("xml");
		String controller = rb.getString("controller");

		String superEntityClass = rb.getString("superEntityClass");
		String superEntityColumns = rb.getString("superEntityColumns");
		String superControllerClass = rb.getString("superControllerClass");
		boolean entityBooleanColumnRemoveIsPrefix = Boolean
				.parseBoolean(rb.getString("entityBooleanColumnRemoveIsPrefix"));
		boolean restControllerStyle = Boolean.parseBoolean(rb.getString("restControllerStyle"));
		boolean controllerMappingHyphenStyle = Boolean.parseBoolean(rb.getString("controllerMappingHyphenStyle"));
		boolean entityTableFieldAnnotationEnable = Boolean
				.parseBoolean(rb.getString("entityTableFieldAnnotationEnable"));
		String include = rb.getString("include");
		String exclude = rb.getString("exclude");
		String tablePrefix = rb.getString("tablePrefix");

		String logicDeleteFieldName = rb.getString("logicDeleteFieldName");

		String dir = rb.getString("outputDirectory");

		boolean swagger2 = Boolean.parseBoolean(rb.getString("swagger2"));
		
		boolean permission = Boolean.parseBoolean(rb.getString("permission"));
		
		boolean sysLog = Boolean.parseBoolean(rb.getString("sysLog"));
		
		String moduleChName = rb.getString("moduleChName");
		
		String title = rb.getString("title");
		
		

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		if (!projectPath.endsWith("\\")) {
			projectPath = projectPath + "\\";
		}
		
		if (StringUtils.isNoneBlank(dir)) {
			String pathArry[] = projectPath.split("\\\\");
			String result = "";
			for (int i = 0; i < pathArry.length - 1; i++) {
				result += pathArry[i] + "\\";
			}
			projectPath = result + dir;
		}
		final String outputProjectPath = projectPath;
		gc.setOutputDir(outputProjectPath + "/src/main/java");
		gc.setFileOverride(fileOverride);
		gc.setAuthor(author);
		gc.setOpen(open);
		gc.setDateType(DateType.ONLY_DATE);
		gc.setBaseColumnList(baseColumnList);
		gc.setBaseResultMap(baseResultMap);
		gc.setSwagger2(swagger2);
		if (StringUtils.isNoneBlank(entityName)) {
			gc.setEntityName(entityName);
		}
		if (StringUtils.isNoneBlank(mapperName)) {
			gc.setMapperName(mapperName);
		}
		if (StringUtils.isNoneBlank(xmlName)) {
			gc.setXmlName(xmlName);
		}
		if (StringUtils.isNoneBlank(serviceName)) {
			gc.setServiceName(serviceName);
		}
		if (StringUtils.isNoneBlank(serviceImplName)) {
			gc.setServiceImplName(serviceImplName);
		}
		if (StringUtils.isNoneBlank(controllerName)) {
			gc.setControllerName(controllerName);
		}

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(url);
		dsc.setDriverName(driverName);
		dsc.setUsername(username);
		dsc.setPassword(password);
		dsc.getDbType();
		DbType dbType = dsc.getDbType();
		if (dbType == DbType.MYSQL) {
			String schema = DatabaseUtil.getSchemaByUrl(dsc.getUrl());
			dsc.setDbQuery(new CustomDbMysqlQuery(schema));
		}
		if (dbType == DbType.ORACLE) {
			dsc.setDbQuery(new CustomDbOracleQuery());
			gc.setIdType(IdType.UUID);
		}
		mpg.setDataSource(dsc);
		mpg.setGlobalConfig(gc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(moduleName);
		pc.setParent(parent);
		pc.setEntity(entity);
		pc.setService(service);
		pc.setServiceImpl(serviceImpl);
		pc.setMapper(mapper);
		pc.setXml(xml);
		pc.setController(controller);
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> customParamMap = new HashMap<String, Object>();
				customParamMap.put("permission", permission);
				if (StringUtils.isNoneBlank(moduleChName)) {
					customParamMap.put("moduleChName", moduleChName);
				}
				if (StringUtils.isNoneBlank(title)) {
					customParamMap.put("title", title);
				}
				customParamMap.put("sysLog", sysLog);
				this.setMap(customParamMap);
			}
		};
		
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/codeTemplates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return outputProjectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
						+ tableInfo.getMapperName() + StringPool.DOT_XML;
			}
		});
		focList.add(new FileOutConfig("/codeTemplates/controller.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String parentPath = pc.getParent().replace(".", "/");
				String path = outputProjectPath + "/src/main/java/" + parentPath + "/" + pc.getController() + "/"
						+ tableInfo.getControllerName() + StringPool.DOT_JAVA;
				return path;
			}
		});
		focList.add(new FileOutConfig("/codeTemplates/serviceImpl.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String parentPath = pc.getParent().replace(".", "/");
				String path = outputProjectPath + "/src/main/java/" + parentPath + "/"
						+ pc.getServiceImpl().replace(".", "/") + "/" + tableInfo.getServiceImplName()
						+ StringPool.DOT_JAVA;
				return path;
			}
		});
		focList.add(new FileOutConfig("/codeTemplates/mapper.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String parentPath = pc.getParent().replace(".", "/");
				String path = outputProjectPath + "/src/main/java/" + parentPath + "/"
						+ pc.getMapper().replace(".", "/") + "/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
				return path;
			}
		});
		focList.add(new FileOutConfig("/codeTemplates/entity.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String parentPath = pc.getParent().replace(".", "/");
				String path = outputProjectPath + "/src/main/java/" + parentPath + "/"
						+ pc.getEntity().replace(".", "/") + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
				return path;
			}
		});
		focList.add(new FileOutConfig("/codeTemplates/entityDTO.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String parentPath = pc.getParent().replace(".", "/");
				String path = outputProjectPath + "/src/main/java/" + parentPath + "/"
						+ pc.getEntity().replace(".", "/") + "/" + tableInfo.getEntityName().replace("DO", "DTO")
						+ StringPool.DOT_JAVA;
				return path;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null).setController(null).setService(null).setServiceImpl(null)
				.setMapper(null).setEntity(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		if (StringUtils.isNotBlank(superEntityClass)) {
			strategy.setSuperEntityClass(superEntityClass);
		}
		if (StringUtils.isNotBlank(superEntityColumns)) {
			strategy.setSuperEntityColumns(superEntityColumns);
		}
		if (StringUtils.isNotBlank(superControllerClass)) {
			strategy.setSuperControllerClass(superControllerClass);
		}
		strategy.setRestControllerStyle(restControllerStyle);
		strategy.setControllerMappingHyphenStyle(controllerMappingHyphenStyle);
		strategy.setTablePrefix(tablePrefix);
		if (StringUtils.isNotBlank(include)) {
			strategy.setInclude(include);
		} else {
			strategy.setExclude(exclude);
		}
		strategy.entityTableFieldAnnotationEnable(entityTableFieldAnnotationEnable);
		strategy.setEntityBooleanColumnRemoveIsPrefix(entityBooleanColumnRemoveIsPrefix);
		if (StringUtils.isNotBlank(logicDeleteFieldName)) {
			strategy.setLogicDeleteFieldName(logicDeleteFieldName);
		}
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
