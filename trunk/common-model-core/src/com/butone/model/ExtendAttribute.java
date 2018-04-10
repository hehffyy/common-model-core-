package com.butone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 业务规则，用于业务动作执行前的业务规范检查，及业务动作执行后的业务逻辑。业务规则支持的业务动作必须为2段动作即Query及Advance。
 * 
 * @hibernate.class table="M_ExtendAttribute"
 */
@XmlRootElement(name = "ExtendAttribute")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtendAttribute extends Version {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String model;

	@XmlAttribute
	private String key;

	@XmlAttribute
	private String value;

	/**
	 * 所属模型
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FModel" length="50" not-null="true"
	 *                   unique-key="IDX_EXTATTR_MODELKEY"
	 */
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * 扩展属性Key
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FKey" length="50" not-null="true"
	 *                   unique-key="IDX_EXTATTR_MODELKEY"
	 */
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 扩展属性Value
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FValue" length="1000"
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
