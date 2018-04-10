package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * @hibernate.class table="M_ActLogicPluginOption" comment="环节逻辑插件选项"
 */
@XmlRootElement(name = "ActLogicPluginOption")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "actGuid" }, target = FlowAct.class, targetKeys = { "guid" }, orderProperty = "groupName,order")
public class ActLogicPluginOption extends Version implements Serializable, Sort {
	private static final long serialVersionUID = -2290600661602053164L;

	private String actGuid;
	private String pluginGuid;
	private String groupName;
	private Integer order;
	private String visibleExpr;
	private String visibleUIExpr;
	private Boolean outerService;
	private String autoServiceCronExpr;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FactGuid" length="32" not-null="true"
	 *                   unique-key="M_ActLogicPluginOption_UNQ"
	 */
	public String getActGuid() {
		return actGuid;
	}

	public void setActGuid(String actGuid) {
		this.actGuid = actGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FPluginGuid" length="32" not-null="true"
	 *                   unique-key="M_ActLogicPluginOption_UNQ"
	 */
	public String getPluginGuid() {
		return pluginGuid;
	}

	public void setPluginGuid(String pluginGuid) {
		this.pluginGuid = pluginGuid;
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

	/**
	 * @hibernate.property column="FGroupName" length="20"
	 */
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Java可见表达式
	 * 
	 * @hibernate.property column="FvisibleExpr" length="1000"
	 */
	public String getVisibleExpr() {
		return visibleExpr;
	}

	public void setVisibleExpr(String visibleExpr) {
		this.visibleExpr = visibleExpr;
	}

	/**
	 * UI可见表达式
	 * 
	 * @hibernate.property column="FVisibleUIExpr" length="1000"
	 */
	public String getVisibleUIExpr() {
		return visibleUIExpr;
	}

	public void setVisibleUIExpr(String visibleUIExpr) {
		this.visibleUIExpr = visibleUIExpr;
	}

	/**
	 * 自动服务触发表达式
	 * 
	 * @hibernate.property column="FautoServiceCronExpr" length="50"
	 */
	public String getAutoServiceCronExpr() {
		return autoServiceCronExpr;
	}

	public void setAutoServiceCronExpr(String autoServiceCronExpr) {
		this.autoServiceCronExpr = autoServiceCronExpr;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FOuterService" comment="是否发布外部服务"
	 */
	public Boolean getOuterService() {
		return outerService;
	}

	public void setOuterService(Boolean outerService) {
		this.outerService = outerService;
	}

}
