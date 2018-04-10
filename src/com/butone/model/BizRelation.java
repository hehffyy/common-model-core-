package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * @hibernate.class table="M_BizRelation" comment="业务关系表"
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BizRelation")
@SubModel(foreignKeys = { "bizGuid" }, target = Biz.class, targetKeys = { "guid" }, orderProperty = "order")
public class BizRelation extends Version implements Serializable, Sort {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850491307054098493L;
	/**
	 * 业务GUID
	 */
	String bizGuid;
	/**
	 * 关联业务类型
	 */
	String relBizGuid;
	/**
	 * 关联类型 include，use
	 */
	String relType;
	/**
	 * 显示顺序
	 */
	private Integer dispOrder;

	/**
	 * 显示顺序
	 * 
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

	/**
	 * @hibernate.property
	 * @hibernate.column name="FBizGuid" length="32" comment="业务GUID"
	 *                   not-null="true" unique-key="M_BIZREL_BIZREL_UNQ"
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FRelGuid" length="32" comment="引用业务GUID"
	 *                   not-null="true" unique-key="M_BIZREL_BIZREL_UNQ"
	 */
	public String getRelBizGuid() {
		return relBizGuid;
	}

	public void setRelBizGuid(String relBizGuid) {
		this.relBizGuid = relBizGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FRelType" length="32" comment="引用类型"
	 *                   not-null="true"
	 */
	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType;
	}

}
