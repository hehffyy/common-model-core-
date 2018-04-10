package com.butone.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 装配目标，one-to-many的子模型必须注解在get方法上，如果是many-to-one或者one-to-one,必须注解在set方法上。
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface AssembleTarget {
	Class<?> value();
}
