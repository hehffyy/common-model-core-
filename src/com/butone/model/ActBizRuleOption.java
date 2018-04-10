package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * @hibernate.class table="M_ActBizRuleOption" comment="环节业务规则选项"
 */
@XmlRootElement(name = "ActBizRuleOption")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "actGuid" }, target = FlowAct.class, targetKeys = { "guid" }, orderProperty = "order")
public class ActBizRuleOption extends Version implements Serializable, Sort {
	private static final long serialVersionUID = 5578507147888982555L;
	/**
	 * 所属环节
	 */
	private String actGuid;
	/**
	 * 对应业务规则
	 */
	private String ruleGuid;

	private Integer order;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FactGuid" length="32" not-null="true"
	 *                   unique-key="M_ActBizRuleOption_UNQ"
	 */
	public String getActGuid() {
		return actGuid;
	}

	public void setActGuid(String actGuid) {
		this.actGuid = actGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FRuleGuid" length="32" not-null="true"
	 *                   unique-key="M_ActBizRuleOption_UNQ"
	 */
	public String getRuleGuid() {
		return ruleGuid;
	}

	public void setRuleGuid(String ruleGuid) {
		this.ruleGuid = ruleGuid;
	}

	/**
	 * @hibernate.property column="FOrder" type="java.lang.Integer"
	 */
	public Object getOrder() {
		return order;
	}

	public void setOrder(Object order) {
		if (order != null)
			this.order = new Integer(order.toString());
		else
			this.order = null;
	}

}
