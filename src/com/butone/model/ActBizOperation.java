package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * @hibernate.class table="M_ActBizOperation" comment="环节业务操作"
 */
@XmlRootElement(name = "ActBizOperation")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "actGuid" }, target = FlowAct.class, targetKeys = { "guid" })
public class ActBizOperation extends Version implements Serializable {

	private static final long serialVersionUID = -7303835963761464408L;

	/**
	 * 所属环节
	 */
	private String actGuid;
	/**
	 * 业务操作
	 */
	private String operation;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FactGuid" length="32" not-null="true"
	 *                   unique-key="M_ActBizOperation_UNOPT"
	 */
	public String getActGuid() {
		return actGuid;
	}

	public void setActGuid(String actGuid) {
		this.actGuid = actGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FOperation" length="50" not-null="true"
	 *                   unique-key="M_ActBizOperation_UNOPT"
	 */
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
