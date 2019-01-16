package com.xhz.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
/**
 * 领域模型转换工具类
 * @author zhangzm
 *
 */
public class CopyUtil {
	
	/**
	 * 复制单个对象属性<p>将source 复制成 clzz 并返回</p>
	 * @param source 复制源
	 * @param clzz 复制目标类型
	 * @return 复制目标对象
	 * @throws BeansException
	 * 
	 */
	public static <T> T copyProperties(Object source, Class<T> clzz) throws BeansException {
		T object = null;
		try {
			object = clzz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BeanUtils.copyProperties(source, object);
		return object;
	}
	
	/**
	 * 复制List类型的属性 <p>将 sourceList 复制成 clzz 类型的List 并返回</p>
	 * @param sourceList 复制源
	 * @param clzz 复制目标类型
	 * @return 复制目标对象
	 * @throws BeansException
	 */
	public static <T, E> List<E> copyListProperties(List<T> sourceList, Class<E> clzz) throws BeansException {
		List<E> targetList = new ArrayList<E>();
		for (T source : sourceList) {
			E object = null;
			try {
				object = clzz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			BeanUtils.copyProperties(source, object);
			targetList.add(object);
		}
		return targetList;
	}
}
