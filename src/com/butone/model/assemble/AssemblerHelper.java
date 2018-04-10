package com.butone.model.assemble;

import com.butone.model.assemble.ModelAssembler;

/**
 * 装配器检查，BaseAccemble中的回调接口，用于级联专配时获得子模型的装配器。
 * @author Administrator
 *
 */
public interface AssemblerHelper {
	ModelAssembler<?> getSubAssembler(Class<?> cls);
}
