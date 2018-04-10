package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.butone.model.annotation.SubModel;

/**
 * 物理表字段定义
 * 
 * @hibernate.class table="M_FieldDef"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = "tableGuid", target = TableDef.class, targetKeys = "guid", orderProperty = "order")
public class FieldDef extends Version implements Serializable, Sort {

	private static final long serialVersionUID = 1L;
	/**
	 * 所属物理表GUID
	 */
	private String tableGuid;
	/**
	 * 物理字段名
	 */
	private String name;
	/**
	 * 显示名称
	 */
	private String dispName;
	/**
	 * 数据类型
	 * 
	 * @See com.butone.Model.DataType
	 */
	private String dataType;
	/**
	 * 长度/精度
	 */
	private Integer length;
	/**
	 * 比例尺(小数位)
	 */
	private Integer scale;
	/**
	 * 是否主键
	 */
	private Boolean primary;
	/**
	 * 非空
	 */
	private Boolean notNull;

	/**
	 * 唯一
	 */
	private Boolean unique;
	/**
	 * 字段类型，fkNormal普通字段、fkLogic逻辑字段(如机构字段、签名字段等)、fkRelated关联字段(逻辑字段的附属字段，
	 * 如签名字段的加密字段)
	 * 
	 * @see com.butone.model.enum.FieldKind
	 */
	private String fieldKind;

	/**
	 * 自动填充属性，包含默认值default、新建表达式(java expr)、js自动计算表达式(bizData.rule.calculate)
	 * 
	 * @see com.butone.model.xmlconfig.AutoFillConfig
	 */
	private String autoFillDef;
	/**
	 * 字段属性，保留字段
	 */
	private String properties;
	/**
	 * 显示顺寻
	 */
	private Integer dispOrder;
	/**
	 * 逻辑字段类型
	 */
	private String logicFieldKind;
	/**
	 * 逻辑字段配置
	 */
	private String logicFieldConfigure;
	/**
	 * X5关系别名，一个业务下的关系名不能重复，通过整数后缀区分
	 */
	private Integer x5RelationAlias;

	/**
	 * 编辑样式,当控件为input或者gridColumn时，决定了的编辑类型。
	 */
	private String editStyle;

	/**
	 * 查找属性，X5实现机制 1.字典表构造虚拟本体(参考SA_TASK)
	 * 
	 */
	private String lookupDef;

	/**
	 * 只读表达式，bizData.rule.readonly
	 */
	private String readonlyExpr;

	/**
	 * 可见表达式，bizData.rule.relevant
	 */
	private String visibleExpr;

	/**
	 * 非空表达式，bizData.rule.required
	 */
	private String requiredExpr;

	/**
	 * 约束表达式，bizData.rule.constraint
	 */
	private String constraintExpr;

	/**
	 * 违反约束的提示信息,常量。bizData.rule.alert
	 */
	private String constraintTip;
	/**
	 * 任务中心字段
	 */
	private Boolean taskField;
	/**
	 * 案卷查询字段
	 */
	private Boolean queryField;

	/**
	 * 引用字段，即引用表的字段GUID
	 */
	private String refField;

	private Boolean sysField;

	/**
	 * 数据格式校验
	 */
	private String dataFormatRegex;

	public FieldDef() {

	}

	public FieldDef(String name, String dispName, String dataType) {
		this.name = name;
		this.dataType = dataType;
		this.dispName = dispName;
	}

	public FieldDef(String name, String dispName, String dataType, int length,
			int scale) {
		this.name = name;
		this.dataType = dataType;
		this.dispName = dispName;
		this.length = length;
		this.scale = scale;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FTableGuid" comment="所属表" length="32"
	 *                   not-null="true" unique-key="M_FieldDef_NameInTable"
	 */
	public String getTableGuid() {
		return tableGuid;
	}

	public void setTableGuid(String tableGuid) {
		this.tableGuid = tableGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FName" comment="字段名" length="30" not-null="true"
	 *                   unique-key="M_FieldDef_NameInTable"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="FdispName" length="60" not-null="true"
	 */
	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/**
	 * @hibernate.property column="FDataType" length="10" not-null="true"
	 */
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @hibernate.property column="FLength"
	 */
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @hibernate.property column="FScale"
	 */
	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	/**
	 * @hibernate.property column="FAutoFillDef"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getAutoFillDef() {
		return autoFillDef;
	}

	public void setAutoFillDef(String autoFillDef) {
		this.autoFillDef = autoFillDef;
	}

	/**
	 * @hibernate.property column="FIsPrimary"
	 */
	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	/**
	 * @hibernate.property column="FUnique"
	 */
	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	/**
	 * @hibernate.property column="FIsSysField"
	 */
	public Boolean getSysField() {
		return sysField;
	}

	public void setSysField(Boolean sysField) {
		this.sysField = sysField;
	}

	/**
	 * @hibernate.property column="FX5RelationAlias"
	 */
	public Integer getX5RelationAlias() {
		return x5RelationAlias;
	}

	public void setX5RelationAlias(Integer x5RelationAlias) {
		this.x5RelationAlias = x5RelationAlias;
	}

	/**
	 * @hibernate.property column="FNotNull"
	 */
	public Boolean getNotNull() {
		return notNull;
	}

	public void setNotNull(Boolean notNull) {
		this.notNull = notNull;
	}

	/**
	 * @hibernate.property column="FFieldKind" length="20" not-null="true"
	 */
	public String getFieldKind() {
		return fieldKind;
	}

	public void setFieldKind(String fieldKind) {
		this.fieldKind = fieldKind;
	}

	/**
	 * 字段属性,Xml结构
	 * 
	 * @see com.butone.model.xmlconfig.FieldConfig
	 * @hibernate.property column="FProperties"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

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
	 * @hibernate.property column="FrefField" length="32"
	 */
	public String getRefField() {
		return refField;
	}

	public void setRefField(String refField) {
		this.refField = refField;
	}

	/**
	 * 逻辑字段类型，例如lgkOrg、lgkDict等
	 * 
	 * @see com.butone.model.enum.LogicField
	 * 
	 * @hibernate.property column="FLogicFieldKind" length="20"
	 */
	public String getLogicFieldKind() {
		return logicFieldKind;
	}

	public void setLogicFieldKind(String logicFieldKind) {
		this.logicFieldKind = logicFieldKind;
	}

	/**
	 * 逻辑字段参数
	 * 
	 * @hibernate.property column="FLogicFieldConfigure"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getLogicFieldConfigure() {
		return logicFieldConfigure;
	}

	public void setLogicFieldConfigure(String logicFieldConfigure) {
		this.logicFieldConfigure = logicFieldConfigure;
	}

	/**
	 * @hibernate.property column="FEditStyle" length="20"
	 */
	public String getEditStyle() {
		return editStyle;
	}

	public void setEditStyle(String editStyle) {
		this.editStyle = editStyle;
	}

	/**
	 * @hibernate.property column="FLookupDef"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getLookupDef() {
		return lookupDef;
	}

	public void setLookupDef(String lookupDef) {
		this.lookupDef = lookupDef;
	}

	/**
	 * @hibernate.property column="FReadonlyExpr" length="1000"
	 */
	public String getReadonlyExpr() {
		return readonlyExpr;
	}

	public void setReadonlyExpr(String readonlyExpr) {
		this.readonlyExpr = readonlyExpr;
	}

	/**
	 * @hibernate.property column="FVisibleExpr" length="1000"
	 */
	public String getVisibleExpr() {
		return visibleExpr;
	}

	public void setVisibleExpr(String visibleExpr) {
		this.visibleExpr = visibleExpr;
	}

	/**
	 * @hibernate.property column="FRequiredExpr" length="1000"
	 */
	public String getRequiredExpr() {
		return requiredExpr;
	}

	public void setRequiredExpr(String requiredExpr) {
		this.requiredExpr = requiredExpr;
	}

	/**
	 * @hibernate.property column="FConstraintExpr" length="1000"
	 */
	public String getConstraintExpr() {
		return constraintExpr;
	}

	public void setConstraintExpr(String constraintExpr) {
		this.constraintExpr = constraintExpr;
	}

	/**
	 * @hibernate.property column="FConstraintTip" length="100"
	 */
	public String getConstraintTip() {
		return constraintTip;
	}

	public void setConstraintTip(String constraintTip) {
		this.constraintTip = constraintTip;
	}

	/**
	 * @hibernate.property column="FtaskField"
	 */
	public Boolean getTaskField() {
		return taskField;
	}

	public void setTaskField(Boolean taskField) {
		this.taskField = taskField;
	}

	/**
	 * @hibernate.property column="FqueryField"
	 */
	public Boolean getQueryField() {
		return queryField;
	}

	public void setQueryField(Boolean queryField) {
		this.queryField = queryField;
	}

	/**
	 * @hibernate.property column="FDataFormatRegex" length="20"
	 */
	public String getDataFormatRegex() {
		return dataFormatRegex;
	}

	public void setDataFormatRegex(String dataFormatRegex) {
		this.dataFormatRegex = dataFormatRegex;
	}

}
