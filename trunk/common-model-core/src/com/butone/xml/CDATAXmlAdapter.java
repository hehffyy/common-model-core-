package com.butone.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CDATAXmlAdapter extends XmlAdapter<String, String> {

	@Override
	public String unmarshal(String v) throws Exception {
		if (v == null)
			return null;
		v = v.trim();
		if (v.startsWith("<![CDATA[")){
			String ret = v.substring(9, v.length() - 3);
			return ret;
		}
		else
			return v;
	}

	@Override
	public String marshal(String v) throws Exception {
		if (v == null)
			return null;
		return "<![CDATA[" + v + "]]>";
	}

}
