package com.butone.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * 流程环节分组
 * 
 * @hibernate.class table="M_FlowActGroup"
 * @author Administrator
 * 
 */
@XmlRootElement(name = "FlowActGroup")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "flowGuid" }, target = Flow.class, targetKeys = { "guid" })
public class FlowActGroup extends Version implements Serializable {
	private static final long serialVersionUID = -4701987045338863706L;
	private String name;
	private String flowGuid;
	private String includeActGuids;

	/**
	 * 限制办理天数
	 */
	private BigDecimal limitDays;
	/**
	 * 限办日期类型：工作日、自然日
	 */
	private String dayConutKind;
	/**
	 * 限制办理日期表达式
	 */
	private String limitDateExpr;

	/**
	 * 分组名称
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="100" not-null="true"
	 *                   unique-key="M_FLOWACTGROUP_NAME_UNQ"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 所属流程
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FflowGuid" length="32" not-null="true"
	 *                   unique-key="M_FLOWACTGROUP_NAME_UNQ"
	 */
	public String getFlowGuid() {
		return flowGuid;
	}

	public void setFlowGuid(String flowGuid) {
		this.flowGuid = flowGuid;
	}

	/**
	 * 包含的环节名称
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FincludeActGuids" length="1000"
	 */
	public String getIncludeActGuids() {
		return includeActGuids;
	}

	public void setIncludeActGuids(String includeActGuids) {
		this.includeActGuids = includeActGuids;
	}

	/**
	 * 限办日期
	 * 
	 * @hibernate.property 
	 * @hibernate.column name="FLimitDays" precision="10" scale="1"
	 */
	public BigDecimal getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(BigDecimal limitDays) {
		this.limitDays = limitDays;
	}

	/**
	 * @hibernate.property column="FdayConutKind" length="10"
	 */
	public String getDayConutKind() {
		return dayConutKind;
	}

	public void setDayConutKind(String dayConutKind) {
		this.dayConutKind = dayConutKind;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FLimitDateExpr" length="1000"
	 */
	public String getLimitDateExpr() {
		return limitDateExpr;
	}

	public void setLimitDateExpr(String limitDateExpr) {
		this.limitDateExpr = limitDateExpr;
	}

}
