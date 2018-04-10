package com.butone.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.butone.utils.StringUtils;

public class Base64XmlAdapter extends XmlAdapter<String, String> {

	@Override
	public String unmarshal(String v) throws Exception {
		if (v == null)
			return null;
		return new String(StringUtils.base64Decode(v.getBytes("utf-8")),
				"utf-8");
	}

	@Override
	public String marshal(String v) throws Exception {
		if (v == null)
			return null;
		return StringUtils.base64Encode(v.getBytes("utf-8"));
	}

}
