package com.butone.model.interceptor;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.butone.model.Form;
import com.butone.utils.StringUtils;

/**
 * 窗体模型拦截器，对ModelStruct属性进行base64编码处理
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class FormModelStructInterceptor extends EmptyInterceptor {
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Form) {
			for (int i = 0; i < propertyNames.length; i++) {
				if ("modelStruct".equals(propertyNames[i])) {
					String modelStruct = (String) state[i];
					if (modelStruct != null && modelStruct.startsWith("{"))
						try {
							state[i] = StringUtils.base64Encode(modelStruct
									.getBytes("utf-8"));
						} catch (UnsupportedEncodingException e) {
						}
				}
			}

		}
		return super.onLoad(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof Form) {
			for (int i = 0; i < propertyNames.length; i++) {
				if ("modelStruct".equals(propertyNames[i])) {
					String modelStruct = (String) currentState[i];
					if (modelStruct != null && !modelStruct.startsWith("{")) {
						try {
							currentState[i] = new String(StringUtils
									.base64Decode(modelStruct), "utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}

}
