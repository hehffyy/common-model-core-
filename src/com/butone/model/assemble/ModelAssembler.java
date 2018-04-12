package com.butone.model.assemble;

import java.util.Collection;

import com.butone.model.annotation.SubModel;

public interface ModelAssembler<E> {
	/**
	 * 装配模型
	 * 
	 * @param entity
	 *            装配对象
	 */
	void assemble(E entity);

	/**
	 * 装配模型
	 * 
	 * @param instance
	 *            装配对象
	 */
	void assemble(Object instance, Collection<Class<?>> excepts);

	/**
	 * 销毁
	 * 
	 * @param entity
	 */
	void destroy(Object entity);

	/**
	 * 获得装配目标元素的Class
	 * 
	 * @return
	 */
	Class<?> getEntityClass();

	/**
	 * 获得子模型实例的集合
	 * 
	 * @param entity
	 * @param subCls
	 * @param sub
	 * @return
	 */
	Collection<?> fetchSubCollection(Object entity, Class<?> subCls,
			SubModel subModel);

	/**
	 * 获得子模型的装配器
	 * 
	 * @param subClass
	 * @return
	 */
	ModelAssembler<?> getSubModelAssembler(Class<?> subClass);

	/**
	 * 设置需要装配的子模型列表
	 * 
	 * @param subModelClasses
	 */
	void setAssembleClasses(Collection<Class<?>> subModelClasses);

	/**
	 * 添加需要装载的子模型
	 * 
	 * @param subModelClasses
	 */
	void addAssembleClasses(Class<?>... subModelClass);

	/**
	 * 加载默认的装配模型Class
	 */
	void loadDefaultAssembleClasses();

	Collection<Object> getSubModelCollection(Object entity, Class<?> subCls)
			throws Exception;

}
