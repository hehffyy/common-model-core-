package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.model.annotation.SubModel;
import com.butone.xml.CDATAXmlAdapter;

/**
 * 业务逻辑代码插件，支持业务动作触发执行、也支持以Toolbar等形式交换执行。
 * 
 * @hibernate.class table="M_BizLogicPlugin"
 */
@XmlRootElement(name = "BizLogicPlugin")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = "bizGuid", target = Biz.class, targetKeys = "guid", orderProperty = "order")
public class BizLogicPlugin extends BaseLogicPlugin implements Serializable,
		Sort {
	public static final String TriggerKind_UITrigger = "UITrigger";
	public static final String TriggerKind_EventTrigger = "EventTrigger";

	private static final long serialVersionUID = 1L;
	/**
	 * 触发类型：流程事件、UI组件
	 */
	@XmlAttribute
	private String triggerKind;
	/**
	 * 如果触发类型为流程事件，描述触发时机包括格式为 [before|after]/[流转|回退|办结|作废|挂起]。允许多个时机，逗号分隔. eg:
	 * before/流转,before/办结
	 */
	@XmlAttribute
	private String triggerEvents;
	/**
	 * 所属业务
	 */
	@XmlAttribute
	private String bizGuid;
	/**
	 * 显示顺序
	 */
	private Integer dispOrder;

	@XmlAttribute
	private String name;

	@XmlJavaTypeAdapter(CDATAXmlAdapter.class)
	private String uiScript;

	/**
	 * 支持的类型[ui],[ui2]
	 */
	@XmlAttribute
	private String supports;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FName" length="200" not-null="true"
	 *                   unique-key="M_BizLogicPlugin_Name_Biz"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FBizGuid" unique-key="M_BizLogicPlugin_Name_Biz"
	 *                   not-null="true" length="32"
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * @hibernate.property column="FTriggerKind" length="30"
	 */
	public String getTriggerKind() {
		return triggerKind;
	}

	public void setTriggerKind(String triggerKind) {
		this.triggerKind = triggerKind;
	}

	/**
	 * @hibernate.property column="FTriggerEvents" length="200"
	 */
	public String getTriggerEvents() {
		return triggerEvents;
	}

	public void setTriggerEvents(String triggerEvents) {
		this.triggerEvents = triggerEvents;
	}

	/**
	 * UI脚本
	 * 
	 * @hibernate.property column="FUIScript"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getUiScript() {
		return uiScript;
	}

	public void setUiScript(String uiScript) {
		this.uiScript = uiScript;
	}

	/**
	 * 支持的类型
	 * 
	 * @hibernate.property column="FSupports" length="20"
	 */
	public String getSupports() {
		return supports;
	}

	public void setSupports(String supports) {
		this.supports = supports;
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

}
