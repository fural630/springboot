package com.example;

import org.mybatis.generator.api.ShellRunner;

public class MybatisGeneratorApplication {
	
	public static void main(String[] args) {
		args = new String[] {"-configfile", "src\\main\\resources\\mybaitis\\generatorConfig.xml", "-overwrite"};
		ShellRunner.main(args);
	}
}
