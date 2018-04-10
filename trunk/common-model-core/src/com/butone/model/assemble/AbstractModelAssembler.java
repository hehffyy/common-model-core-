package com.butone.model.assemble;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;
import com.butone.model.annotation.SubModels;

public abstract class AbstractModelAssembler<E> implements ModelAssembler<E> {
	protected List<Class<?>> includesubModelClasses = new ArrayList<Class<?>>();
	protected Class<?> entityCls;

	protected AbstractModelAssembler() {
		Class<?> c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityCls = (Class<?>) p[0];
		}
	}

	@Override
	public void setAssembleClasses(Collection<Class<?>> subModelClasses) {
		this.includesubModelClasses.clear();
		this.includesubModelClasses.addAll(subModelClasses);
	};

	public Class<?> getAssembleClass(Class<?> entityClass) {
		for (Class<?> assembleClass : includesubModelClasses) {
			Type t = assembleClass.getGenericSuperclass();
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			if (p[0].equals(entityClass))
				return assembleClass;
		}
		return null;
	}

	@Override
	public void addAssembleClasses(Class<?>... subModelClass) {
		for (Class<?> cls : subModelClass) {
			if (includesubModelClasses.indexOf(cls) == -1)
				includesubModelClasses.add(cls);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void assemble(Object entity, Collection<Class<?>> excepts) {
		try {
			for (Class<?> subCls : includesubModelClasses) {
				if (excepts != null && excepts.contains(subCls))
					continue;
				// 获得子模型的注解
				SubModel sub = getSubModel(subCls);
				if (sub == null)
					throw new RuntimeException(subCls.getName() + "未注解SubModel");
				Method m = methodOfAssembleTarget(subCls);
				if (m == null)
					throw new RuntimeException(entityCls.getName()
							+ "未注解get or set" + subCls.getSimpleName() + "的方法");
				// 获得实体关联的子模型集合
				Collection<?> subColl = fetchSubCollection(entity, subCls, sub);
				if (!sub.inverse()) {
					// 如果子模型也存在装配器，那么先装配子模型
					for (Object instance : subColl) {
						ModelAssembler<?> subAssemble = subCls
								.equals(this.entityCls) ? this : this
								.getSubModelAssembler(subCls);
						if (subAssemble != null)
							subAssemble.assemble(instance, excepts);
					}
					// 标注在get方法上，添加子模型集合
					Collection<Object> coll = (Collection<Object>) m
							.invoke(entity);
					coll.clear();
					coll.addAll(subColl);
				} else {
					// 翻转及多对一的主对象 不进行装配
					if (!m.getReturnType().equals(Void.TYPE))
						throw new RuntimeException(
								"翻转的SubModel对应的AssembleTarget必须注解在set方法上");
					if (subColl.isEmpty())
						m.invoke(entity, new Object[] { null });
					else if (subColl.size() == 1)
						m.invoke(entity, subColl.iterator().next());
					else
						throw new RuntimeException(entity.toString()
								+ "存在多个主对象" + subCls.getName());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(entity.toString(), e);
		}
	}

	@Override
	public void assemble(E entity) {
		assemble(entity, null);
	}

	@Override
	public abstract Collection<?> fetchSubCollection(Object entity,
			Class<?> subCls, SubModel subModel);

	@Override
	public ModelAssembler<?> getSubModelAssembler(Class<?> subClass) {
		return ModelAssemblerFactory.getDefaultAssemble(subClass);
	}

	/**
	 * 反射装配元素的装配目标的方法
	 * 
	 * @param subCls
	 * @return
	 */
	private Method methodOfAssembleTarget(Class<?> subCls) {
		Method[] mods = entityCls.getMethods();
		for (Method m : mods) {
			AssembleTarget am = m.getAnnotation(AssembleTarget.class);
			// 注解非空、标注的Class相同、返回值是Coll
			if (am != null && am.value().equals(subCls)) {
				Class<?> rt = m.getReturnType();
				if (Void.TYPE.equals(rt)) {
					if (m.getParameterTypes().length != 1) {
						throw new RuntimeException("@AssembleTarget注解的方法"
								+ m.getName() + "必须且只应有一个参数");
					}
					if (subCls.isAssignableFrom(m.getParameterTypes()[0]))
						return m;

				} else if (Collection.class.isAssignableFrom(rt)) {
					return m;
				}
			}
		}
		return null;
	}

	@Override
	public Collection<Object> getSubModelCollection(Object entity, Class subCls)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method m = methodOfAssembleTarget(subCls);
		if (m == null)
			throw new RuntimeException(entityCls.getName() + "未注解get or set"
					+ subCls.getSimpleName() + "的方法");
		return (Collection<Object>) m.invoke(entity);
	}

	private SubModel getSubModel(Class<?> cls) {
		SubModel subModel = cls.getAnnotation(SubModel.class);
		if (subModel == null) {
			SubModels subModels = cls.getAnnotation(SubModels.class);
			if (subModels != null)
				for (SubModel sub : subModels.subModels()) {
					if (sub != null && sub.target().equals(this.entityCls)) {
						subModel = sub;
						break;
					}
				}
		}
		return subModel;

	}

	@Override
	public Class<?> getEntityClass() {
		return entityCls;
	}

	public void destroy(Object entity) {
		try {
			for (Class<?> subCls : includesubModelClasses) {
				// 获得子模型的注解
				SubModel sub = getSubModel(subCls);
				if (sub == null)
					throw new RuntimeException(subCls.getName() + "未注解SubModel");
				// 翻转的即 多对一 或者 一对一，多为parent 或者owner对象。
				if (sub.inverse())
					continue;

				// 获得实体关联的子模型集合
				Collection<?> subColl = fetchSubCollection(entity, subCls, sub);
				// 如果子模型也存在装配器，那么先装配子模型
				for (Object instance : subColl) {
					ModelAssembler<?> subAssemble = this
							.getSubModelAssembler(subCls);
					if (subAssemble != null)
						subAssemble.destroy(instance);
				}
				innerDestroy(subCls, subColl);
			}
			innerDestroy(this.getEntityClass(), entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract void innerDestroy(Class<?> subCls,
			Collection<?> subModels);

	protected abstract void innerDestroy(Class<?> entityCls, Object entity);

}
