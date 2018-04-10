package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;
import com.butone.xml.Base64XmlAdapter;
import com.butone.xml.CDATAXmlAdapter;

/**
 * 业务规则，用于业务动作执行前的业务规范检查，及业务动作执行后的业务逻辑。业务规则支持的业务动作必须为2段动作即Query及Advance。
 * 
 * @hibernate.class table="M_BizRule"
 * @author Administrator
 */
@XmlRootElement(name = "BizRule")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "bizGuid" }, target = Biz.class, targetKeys = { "guid" }, orderProperty = "order")
public class BizRule extends Version implements Serializable, Sort {
	private static final long serialVersionUID = 2082089332561499943L;
	@XmlAttribute
	private String bizGuid;
	@XmlAttribute
	private String name;
	/**
	 * 规则类型：禁止规则、提示规则
	 */
	@XmlAttribute
	private String kind;
	/**
	 * 触发事件：流转、办结、终止、回退、移交。支持多选，用逗号分隔。触发时机，afterQuery触发事件
	 */
	@XmlAttribute
	private String triggerEvents;
	/**
	 * 判定业务逻辑，在判定表达式前执行，产生dataModels的实例，从而支持更加复杂判定表达式。
	 */
	@XmlElement
	@XmlJavaTypeAdapter(Base64XmlAdapter.class)
	private String verifyLogic;
	/**
	 * 数据模型：参与验证逻辑的临时数据对象。xml描述包含多个“表”
	 */
	@XmlElement
	@XmlJavaTypeAdapter(CDATAXmlAdapter.class)
	private String dataModels;

	/**
	 * 判定逻辑,java表达式，包含业务工作表及dataModels实例。
	 */
	@XmlElement
	@XmlJavaTypeAdapter(CDATAXmlAdapter.class)
	private String verifyExpr;
	/**
	 * 提示信息
	 */
	@XmlElement
	@XmlJavaTypeAdapter(CDATAXmlAdapter.class)
	private String tipExpr;

	/**
	 * 业务规则动作
	 */
	@XmlElement(name = "action")
	private List<BizRuleAction> actions = new ArrayList<BizRuleAction>();

	/**
	 * 执行顺序
	 */
	@XmlAttribute
	private Integer order;
	@XmlElement
	private String relBizDatas;

	/**
	 * 是否监管规则
	 */
	@XmlAttribute
	private Boolean supervise;

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
	 * 所属业务
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FBizGuid" length="32" not-null="true"
	 *                   unique-key="M_BIZRULE_NAME_BIZ"
	 * @return
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * 规则名称
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="200" not-null="true"
	 *                   unique-key="M_BIZRULE_NAME_BIZ"
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="FKind" length="20"
	 * @return
	 */
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * @hibernate.property column="FTriggerEvents" length="100"
	 * @return
	 */
	public String getTriggerEvents() {
		return triggerEvents;
	}

	public void setTriggerEvents(String triggerEvents) {
		this.triggerEvents = triggerEvents;
	}

	/**
	 * 数据模型：参与验证逻辑的临时数据对象。
	 * 
	 * @hibernate.property column="FDataModels"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 * @return
	 */
	public String getDataModels() {
		return dataModels;
	}

	public void setDataModels(String dataModels) {
		this.dataModels = dataModels;
	}

	/**
	 * 判定业务逻辑
	 * 
	 * @hibernate.property column="FVerifyLogic"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getVerifyLogic() {
		return verifyLogic;
	}

	public void setVerifyLogic(String verifyLogic) {
		this.verifyLogic = verifyLogic;
	}

	/**
	 * 判定表达式
	 * 
	 * @hibernate.property column="FverifyExpr"
	 *                     type="org.springframework.orm.hibernate3.support.ClobStringType"
	 */
	public String getVerifyExpr() {
		return verifyExpr;
	}

	public void setVerifyExpr(String verifyExpr) {
		this.verifyExpr = verifyExpr;
	}

	/**
	 * 判定提示表达式
	 * 
	 * @hibernate.property column="FtipExpr"
	 *                     type="org.springframework.orm.hibernate3.support.ClobStringType"
	 */
	public String getTipExpr() {
		return tipExpr;
	}

	public void setTipExpr(String tipExpr) {
		this.tipExpr = tipExpr;
	}

	// /**
	// * @hibernate.set inverse="true" cascade="delete" lazy="false"
	// * @hibernate.collection-key column="FRuleGuid"
	// * @hibernate.collection-one-to-many
	// class="com.butone.model.BizRuleAction"
	// * @return
	// */
	@AssembleTarget(BizRuleAction.class)
	public List<BizRuleAction> getActions() {
		return actions;
	}

	public void setActions(List<BizRuleAction> actions) {
		this.actions = actions;
	}

	/**
	 * 判定提示表达式
	 * 
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
	 * 判定提示表达式
	 * 
	 * @hibernate.property column="FSupervise"
	 */
	public Boolean getSupervise() {
		return supervise;
	}

	public void setSupervise(Boolean supervise) {
		this.supervise = supervise;
	}
}
