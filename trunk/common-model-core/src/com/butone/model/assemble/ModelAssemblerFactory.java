package com.butone.model.assemble;

import java.util.ServiceLoader;

@SuppressWarnings("unchecked")
public class ModelAssemblerFactory {
	private static ServiceLoader<ModelAssembler> serviceLoader = ServiceLoader
			.load(ModelAssembler.class);

	public static <A> ModelAssembler<A> getBlankAssemble(Class<A> cls) {
		for (ModelAssembler assemble : serviceLoader) {
			if (assemble.getEntityClass().equals(cls)) {
				return assemble;
			}
		}
		return null;
	}

	public static <A> ModelAssembler<A> getDefaultAssemble(Class<A> cls) {
		for (ModelAssembler assemble : serviceLoader) {
			if (assemble.getEntityClass().equals(cls)) {
				assemble.loadDefaultAssembleClasses();
				return assemble;
			}
		}
		return null;
	}
}
