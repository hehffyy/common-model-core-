package com.butone.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface SubModel {
	/**
	 * 主表Class
	 * 
	 * @return
	 */
	Class<?> target();
	/**
	 * 主表主键(Class属性名)，对应本表的外键。翻转时为本表的外键。
	 * 
	 * @return
	 */
	String[] targetKeys();

	/**
	 * 本表外键(Class属性名)。翻转时为主表的主键。
	 * 
	 * @return
	 */
	String[] foreignKeys();
	
	/**
	 * 关系翻转，当前模型实际是主模型。
	 * @return
	 */
	boolean inverse() default false; 

	/**
	 * 排序属性
	 * @return
	 */
	String orderProperty() default "";

	boolean asc() default true;

}
