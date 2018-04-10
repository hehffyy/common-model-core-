package com.butone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * 物理表逻辑插件，用于实现类似触发器功能。作用于所有概念Action时机。
 * 
 * @see com.butone.model.Concept
 * @hibernate.class table="M_TableLogicPlugin"
 */
@XmlRootElement(name = "TableLogicPlugin")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "tableGuid" }, target = TableDef.class, targetKeys = { "guid" })
public class TableLogicPlugin extends BaseLogicPlugin {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2002262140579071494L;
	public static String EVENT_BEFORESAVE = "beforeSave";
	public static String EVENT_AFTERSAVE = "afterSave";
	private String name;
	private String event;
	private String tableGuid;

	/**
	 * 触发时机
	 * 
	 * @hibernate.property column="FEvent" length="20" not-null="true"
	 */
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * 工作表
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FTableGuid" length="32" not-null="true"
	 *                   unique-key="M_TableLogicPlugin_Name_Table"
	 */
	public String getTableGuid() {
		return tableGuid;
	}

	public void setTableGuid(String tableGuid) {
		this.tableGuid = tableGuid;
	}

	/**
	 * 工作表
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="100" not-null="true"
	 *                   unique-key="M_TableLogicPlugin_Name_Table"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
