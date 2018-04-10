package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.model.annotation.SubModel;
import com.butone.xml.Base64XmlAdapter;

/**
 * 业务规则动作，仅在触发警告规则后执行。
 * 
 * @hibernate.class table="M_BizRuleAction"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "ruleGuid" }, target = BizRule.class, targetKeys = { "guid" }, orderProperty = "order")
public class BizRuleAction extends Version implements Serializable, Sort {
	private static final long serialVersionUID = -451665982454454395L;
	private String ruleGuid;
	private String condition;
	/**
	 * 代码插件，默认为数据计算逻辑。
	 */
	private String logicPlugin;
	/**
	 * CalcLogicConfig的xml
	 */
	@XmlJavaTypeAdapter(Base64XmlAdapter.class)
	private String pluginConfigure;

	private String name;

	private Integer order;

	private String relBizDatas;

	/**
	 * 关联的业务表，插件执行时只加载这些表
	 * 
	 * @hibernate.property column="FRelBizDatas" length="1000"
	 */
	public String getRelBizDatas() {
		return relBizDatas;
	}

	public void setRelBizDatas(String relBizDatas) {
		this.relBizDatas = relBizDatas;
	}

	/**
	 * 所属规则
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FRuleGuid" length="32"
	 *                   index="M_BizRuleAction_Rule"
	 * @return
	 */
	public String getRuleGuid() {
		return ruleGuid;
	}

	public void setRuleGuid(String ruleGuid) {
		this.ruleGuid = ruleGuid;
	}

	/**
	 * 动作名称
	 * 
	 * @hibernate.property column="FName" length="200"
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 生效条件
	 * 
	 * @hibernate.property column="FCondition" length="1000"
	 * @return
	 */
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * 动作业务逻辑插件类型
	 * 
	 * @hibernate.property column="FLogicPlugin" length="1000"
	 * @return
	 */
	public String getLogicPlugin() {
		return logicPlugin;
	}

	public void setLogicPlugin(String logicPlugin) {
		this.logicPlugin = logicPlugin;
	}

	/**
	 * 动作业务逻辑插件配置
	 * 
	 * @hibernate.property column="FPluginConfigure"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 * @return
	 */
	public String getPluginConfigure() {
		return pluginConfigure;
	}

	public void setPluginConfigure(String pluginConfigure) {
		this.pluginConfigure = pluginConfigure;
	}

	/**
	 * 执行顺序
	 * 
	 * @hibernate.property column="FOrder" type="java.lang.Integer"
	 * @return
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
