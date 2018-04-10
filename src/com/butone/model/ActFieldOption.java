package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * 环节字段选项
 * 
 * @hibernate.class table="M_ActFieldOption" comment="环节字段选项"
 */
@XmlRootElement(name = "ActFieldOption")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "actGuid", "tableGuid" }, target = ActTablePermission.class, targetKeys = {
		"actGuid", "tableGuid" })
public class ActFieldOption extends Version implements Serializable {
	private static final long serialVersionUID = 3141336532187515328L;
	/**
	 * 所属环节
	 */
	private String actGuid;
	/**
	 * 所属工作表
	 */
	private String tableGuid;

	/**
	 * 对应字段
	 */
	private String fieldGuid;
	/**
	 * 只读属性，对应.w感知主键的readonly(如果有)
	 */
	private Boolean readonly;

	/**
	 * 计算表达式，js
	 */
	private String calcExpr;

	/**
	 * 只读表达式 jsExp，X5 bizData.rule
	 */
	private String readonlyExpr;
	/**
	 * 可见表达式 jsExp，X5 bizData.rule
	 */
	private String visibleExpr;
	/**
	 * 必须表达式 jsExp，X5 bizData.rule
	 */
	private String requiredExpr;
	/**
	 * 约束表达式 jsExp，X5 bizData.rule
	 */
	private String constraintExpr;
	/**
	 * 违反约束时的提示信息,常量
	 */
	private String tipInfo;

	/**
	 * 默认值
	 */
	private String defalutValue;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FactGuid" length="32" not-null="true"
	 *                   unique-key="M_M_ActFieldOption_UNQ"
	 */
	public String getActGuid() {
		return actGuid;
	}

	public void setActGuid(String actGuid) {
		this.actGuid = actGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FTableGuid" length="32" not-null="true"
	 *                   unique-key="M_M_ActFieldOption_UNQ"
	 */
	public String getTableGuid() {
		return tableGuid;
	}

	public void setTableGuid(String tableGuid) {
		this.tableGuid = tableGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FfieldGuid" length="32" not-null="true"
	 *                   unique-key="M_M_ActFieldOption_UNQ"
	 */
	public String getFieldGuid() {
		return fieldGuid;
	}

	public void setFieldGuid(String fieldGuid) {
		this.fieldGuid = fieldGuid;
	}

	/**
	 * @hibernate.property column="FreadonlyExpr" length="1000"
	 */
	public String getReadonlyExpr() {
		return readonlyExpr;
	}
	
	public void setReadonlyExpr(String readonlyExpr) {
		this.readonlyExpr = readonlyExpr;
	}
	
	/**
	 * @hibernate.property column="FcalcExpr" length="1000"
	 */
	public String getCalcExpr() {
		return calcExpr;
	}

	public void setCalcExpr(String calcExpr) {
		this.calcExpr = calcExpr;
	}

	/**
	 * @hibernate.property column="fvisibleExpr" length="1000"
	 */
	public String getVisibleExpr() {
		return visibleExpr;
	}

	public void setVisibleExpr(String visibleExpr) {
		this.visibleExpr = visibleExpr;
	}

	/**
	 * @hibernate.property column="frequiredExpr" length="1000"
	 */
	public String getRequiredExpr() {
		return requiredExpr;
	}

	public void setRequiredExpr(String requiredExpr) {
		this.requiredExpr = requiredExpr;
	}

	/**
	 * @hibernate.property column="fconstraintExpr" length="1000"
	 */
	public String getConstraintExpr() {
		return constraintExpr;
	}

	public void setConstraintExpr(String constraintExpr) {
		this.constraintExpr = constraintExpr;
	}

	/**
	 * @hibernate.property column="ftipInfo" length="100"
	 */
	public String getTipInfo() {
		return tipInfo;
	}

	public void setTipInfo(String tipInfo) {
		this.tipInfo = tipInfo;
	}

	/**
	 * @hibernate.property column="Freadonly"
	 */
	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}
	/**
	 * @hibernate.property column="FdefaultValue" length="1000"
	 */
	public String getDefalutValue() {
		return defalutValue;
	}

	public void setDefalutValue(String defalutValue) {
		this.defalutValue = defalutValue;
	}

}
