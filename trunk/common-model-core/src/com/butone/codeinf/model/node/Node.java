package com.butone.codeinf.model.node;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.butone.model.Version;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Node extends Version implements Serializable {

	private static final long serialVersionUID = 7429613441429108011L;
	//节点显示名称
	@XmlAttribute
	private String displayName;
	//是否编号相关
	@XmlAttribute
	private boolean relateSequenceValue;

	public boolean isRelateSequenceValue() {
		return relateSequenceValue;
	}

	public void setRelateSequenceValue(boolean relateSequenceValue) {
		this.relateSequenceValue = relateSequenceValue;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
