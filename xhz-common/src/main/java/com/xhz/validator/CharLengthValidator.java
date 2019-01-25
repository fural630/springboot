package com.xhz.validator;

import java.io.UnsupportedEncodingException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 验证中文长度是否满足数据库字段长度
 * @author zhangzm
 *
 */
public class CharLengthValidator implements ConstraintValidator<CharLength, String> {

	private long min;
	private long max;
	private String charset;

	@Override
	public void initialize(CharLength parameters) {
		this.min = parameters.min();
		this.max = parameters.max();
		this.charset = parameters.charset();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (null == value) {
			value = "";
		}
		long length = 0;
		try {
			length = ((String) value).getBytes(charset).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return (length >= min) && (length <= max);
	}

}
