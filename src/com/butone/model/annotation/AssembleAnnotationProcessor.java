package com.butone.model.annotation;

import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

@SuppressWarnings("deprecation")
public class AssembleAnnotationProcessor implements AnnotationProcessor {

	// Annotation处理器环境，是该处理器与APT交互的重要途径
	@SuppressWarnings("unused")
	private AnnotationProcessorEnvironment env;
	@SuppressWarnings("unused")
	private Set<AnnotationTypeDeclaration> types;

	public AssembleAnnotationProcessor(Set<AnnotationTypeDeclaration> types,
			AnnotationProcessorEnvironment env) {
		this.env = env;
		this.types = types;
	}

	@Override
	public void process() {

	}
}
