package com.butone.model.annotation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

public class AssembleAnnotationProcessorFactory implements
		AnnotationProcessorFactory {
	private static final Collection<String> supportedAnnotations = Collections
			.unmodifiableCollection(Arrays.asList("com.butone.model.annotation.SubModel"));
	 // No supported options
    private static final Collection<String> supportedOptions = Collections.emptySet();

	@Override
	public AnnotationProcessor getProcessorFor(
			Set<AnnotationTypeDeclaration> arg0,
			AnnotationProcessorEnvironment arg1) {
		return new AssembleAnnotationProcessor(arg0,arg1);
	}

	@Override
	public Collection<String> supportedAnnotationTypes() {
		return supportedAnnotations;
	}

	@Override
	public Collection<String> supportedOptions() {
		return supportedOptions;
	}

}
