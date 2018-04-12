package com.butone.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;
import com.butone.xml.Base64XmlAdapter;

/**
 * 流程对象
 * 
 * @hibernate.class table="M_Flow"
 * @author Administrator
 * 
 */
@XmlRootElement(name = "Flow")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "bizGuid" }, target = Biz.class, targetKeys = { "guid" }, orderProperty = "order")
public class Flow extends BaseResource implements Serializable, Sort {
	private static final long serialVersionUID = 3866189407016575652L;
	private String bizGuid;
	private String name;
	private String dispName;
	@XmlElement
	@XmlJavaTypeAdapter(Base64XmlAdapter.class)
	private String model;
	@XmlElement
	@XmlJavaTypeAdapter(Base64XmlAdapter.class)
	private String diagram;
	private BigDecimal limitDays;
	private String dayConutKind;
	/**
	 * 限办日期表达式
	 */
	private String limitDateExpr;
	/**
	 * 限办开始日期表达式(Java)
	 */
	private String limitStartDateExpr;

	/**
	 * 限办生效环节 (flowActKind=fakBusiness的FlowAct.getName()),如果为空启动时生效，否则制定环节批转后生效
	 */
	private String limitEffectActivity;

	/**
	 * 是否已废弃
	 */
	private Boolean disabled;
	/**
	 * 是否协同控制流程
	 */
	private Boolean coopControl;
	/**
	 * 机构级别(普通流程有效)，例如建设用地审批，可以分别建立 省级、地市、区县 3个级别的不同流程。
	 */
	private String orgLevel;
	/**
	 * 活动环节列表
	 */
	private List<FlowAct> activities = new ArrayList<FlowAct>();
	/**
	 * 环节分组
	 */
	private List<FlowActGroup> actGroups = new ArrayList<FlowActGroup>();

	/**
	 * 功能模板
	 */
	private String funcTemplet;

	/**
	 * 功能模板
	 */
	private String funcTemplet2;

	/**
	 * 静默办结，无需办结结果
	 */
	private Boolean silenceFinish;
	/**
	 * 显示顺序
	 */
	private Integer dispOrder;

	/**
	 * 案卷浏览环节
	 */
	private String flowViewActivity;

	/**
	 * 办结类型
	 */
	private String finishKind;

	/**
	 * 案卷标题表达式
	 */
	private String recTitleExpr;

	/**
	 * 是否旧版执行者选择框
	 */
	private Boolean oldExecutorDialog;

	/**
	 * 要件模板
	 */
	private String materialTemplate;

	/**
	 * 所属业务
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FbizGuid" length="32" not-null="true"
	 *                   unique-key="M_FLOW_NAME_UNQ"
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * 流程模型
	 * 
	 * @hibernate.property column="Fmodel"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 * @return
	 */
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * 流程视图
	 * 
	 * @hibernate.property column="Fdiagram"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 * @return
	 */
	public String getDiagram() {
		return diagram;
	}

	public void setDiagram(String diagram) {
		this.diagram = diagram;
	}

	/**
	 * 名称英文，用于创建process文件夹及process资源
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="100" not-null="true"
	 *                   unique-key="M_FLOW_NAME_UNQ"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 显示名称
	 * 
	 * @hibernate.property column="FDispName" length="60" not-null="true"
	 */
	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/**
	 * 模板文件名称
	 * 
	 * @hibernate.property column="FfuncTemplet" length="255" not-null="true"
	 */
	public String getFuncTemplet() {
		return funcTemplet;
	}

	public void setFuncTemplet(String funcTemplet) {
		this.funcTemplet = funcTemplet;
	}

	/**
	 * 模板文件名称
	 * 
	 * @hibernate.property column="FfuncTemplet2" length="255"
	 */
	public String getFuncTemplet2() {
		return funcTemplet2;
	}

	public void setFuncTemplet2(String funcTemplet2) {
		this.funcTemplet2 = funcTemplet2;
	}

	/**
	 * 限办日期
	 * 
	 * @hibernate.property column="FLimitDays"
	 */
	public BigDecimal getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(BigDecimal limitDays) {
		// if(limitDays != null && limitDays == 0)
		// return;
		this.limitDays = limitDays;
	}

	/**
	 * 限办日期类型：工作日、自然日
	 * 
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
	 * @hibernate.column name="FRecTitleExpr" length="1000" comment="案卷标题"
	 */
	public String getRecTitleExpr() {
		return recTitleExpr;
	}

	public void setRecTitleExpr(String recTitleExpr) {
		this.recTitleExpr = recTitleExpr;
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

	/**
	 * @hibernate.property
	 * @hibernate.column name="FLimitStartDateExpr" length="1000"
	 *                   comment="限办开始日期表达式"
	 */
	public String getLimitStartDateExpr() {
		return limitStartDateExpr;
	}

	public void setLimitStartDateExpr(String limitStartDateExpr) {
		this.limitStartDateExpr = limitStartDateExpr;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FlimitEffectActivity" length="100"
	 *                   comment="限办生效环节(如果为空启动后立即生效，否则制定环节批转后生效)"
	 */
	public String getLimitEffectActivity() {
		return limitEffectActivity;
	}

	public void setLimitEffectActivity(String limitEffectActivity) {
		this.limitEffectActivity = limitEffectActivity;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FflowViewActivity" length="20" comment="案卷浏览环节"
	 */
	public String getFlowViewActivity() {
		return flowViewActivity;
	}

	public void setFlowViewActivity(String flowViewActivity) {
		this.flowViewActivity = flowViewActivity;
	}

	/**
	 * 是否已废弃
	 * 
	 * @hibernate.property column="fdisabled"
	 */
	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @hibernate.property column="fCoopControl"
	 */
	public Boolean getCoopControl() {
		return coopControl;
	}

	public void setCoopControl(Boolean coopControl) {
		this.coopControl = coopControl;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FfinishKind" length="20" comment="默认办结类型"
	 */
	public String getFinishKind() {
		return finishKind;
	}

	public void setFinishKind(String finishKind) {
		this.finishKind = finishKind;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FmaterialTemplate" length="20" comment="要件模板"
	 */
	public String getMaterialTemplate() {
		return materialTemplate;
	}

	public void setMaterialTemplate(String materialTemplate) {
		this.materialTemplate = materialTemplate;
	}

	@AssembleTarget(value = FlowAct.class)
	public List<FlowAct> getActivities() {
		return activities;
	}

	/**
	 * 静默办结
	 * 
	 * @hibernate.property column="fsilenceFinish"
	 */
	public Boolean getSilenceFinish() {
		return silenceFinish;
	}

	public void setSilenceFinish(Boolean silenceFinish) {
		this.silenceFinish = silenceFinish;
	}

	/**
	 * 是否旧版执行者选择框
	 * 
	 * @hibernate.property column="fOldExecutorDialog"
	 */
	public Boolean getOldExecutorDialog() {
		return oldExecutorDialog;
	}

	public void setOldExecutorDialog(Boolean oldExecutorDialog) {
		this.oldExecutorDialog = oldExecutorDialog;
	}

	public void setActivities(List<FlowAct> activities) {
		this.activities = activities;
	}

	@AssembleTarget(value = FlowActGroup.class)
	public List<FlowActGroup> getActGroups() {
		return actGroups;
	}

	public void setActGroups(List<FlowActGroup> actGroups) {
		this.actGroups = actGroups;
	}

	public FlowActGroup findActGroupOfActivity(String actGuid) {
		for (FlowActGroup group : getActGroups()) {
			if (("," + group.getIncludeActGuids() + ",").contains(actGuid))
				return group;
		}
		return null;
	}

	public FlowAct findActivityByGuid(String actGuid) {
		for (FlowAct act : getActivities()) {
			if (act.getGuid().equals(actGuid))
				return act;
		}
		return null;
	}

	public FlowAct findActivityByName(String actName) {
		for (FlowAct act : getActivities()) {
			if (act.getName().equals(actName))
				return act;
		}
		return null;
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
