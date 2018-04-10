package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.butone.model.annotation.SubModel;

/**
 * 系统字典项目
 * 
 * @author Administrator
 * @hibernate.class table="M_SysDictItem" comment="系统字典表"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "type" }, target = SysDict.class, targetKeys = { "type" }, orderProperty = "order")
public class SysDictItem extends Version implements Serializable, Sort {
	private static final long serialVersionUID = 1927202016813351981L;
	/**
	 * 类型，对应SysDict的name属性
	 */
	private String type;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;

	private Integer dispOrder;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FType" length="50" not-null="true"
	 *                   unique-key="M_SysDictItem_UNQ"
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="Fcode" length="20" not-null="true"
	 *                   unique-key="M_SysDictItem_UNQ"
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @hibernate.property column="Fname" length="100"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="FDispOrder" type="java.lang.Integer"
	 */
	public Object getOrder() {
		return dispOrder;
	}

	public void setOrder(Object dispOrder) {
		if (dispOrder != null)
			this.dispOrder = new Integer(dispOrder.toString());
		else
			this.dispOrder = null;
	}
}
