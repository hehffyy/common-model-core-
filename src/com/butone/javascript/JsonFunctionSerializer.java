package com.butone.javascript;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

public class JsonFunctionSerializer implements ObjectSerializer {

	@Override
	public void write(JSONSerializer serializer, Object object,
			Object fieldName, Type fieldType) throws IOException {
		if (object == null) {
			serializer.getWriter().writeNull();
			return;
		}
		JsonFunction function = (JsonFunction) object;
		StringBuffer sb = new StringBuffer();
		Iterator<String> i = function.getParameters().iterator();
		sb.append("function (");
		while (i.hasNext()) {
			sb.append(i.next());
			if (i.hasNext())
				sb.append(",");
		}
		sb.append("){\n");
		i = function.getSegments().iterator();
		while (i.hasNext()) {
			sb.append(i.next());
		}
		sb.append("}\n");
		SerializeWriter out = serializer.getWriter();
		String text = sb.toString();
		out.write(text, 0, text.length());
	}

}
