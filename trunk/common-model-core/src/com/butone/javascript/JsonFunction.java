package com.butone.javascript;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.xml.CDATAXmlAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class JsonFunction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8708220798956094660L;
	@XmlAttribute
	String functionName;
	@XmlElementWrapper(name = "parameters")
	@XmlElement(name = "parameter")
	List<String> parameters;
	@XmlElementWrapper(name = "segments")
	@XmlElement(name = "segment")
	@XmlJavaTypeAdapter(CDATAXmlAdapter.class)
	List<String> segments;
	@XmlAttribute
	boolean globel;

	public String getFunctionName() {
		return functionName;
	}

	public List<String> getParameters() {
		if (parameters == null)
			parameters = new ArrayList<String>();
		return parameters;
	}

	public List<String> getSegments() {
		if (segments == null)
			segments = new ArrayList<String>();
		return segments;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public void setSegments(List<String> segments) {
		this.segments = segments;
	}

	public boolean isGlobel() {
		return globel;
	}

	public void setGlobel(boolean globel) {
		this.globel = globel;
	}

}
