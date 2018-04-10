package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;

/**
 * 环节表单选项，即流程环节可见表单
 * 
 * @author Administrator
 * @hibernate.class table="M_ActFormOption" comment="环节表单选项"
 */
@XmlRootElement(name = "ActFormOption")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "actGuid" }, target = FlowAct.class, targetKeys = { "guid" }, orderProperty = "order")
public class ActFormOption extends Version implements Serializable, Sort {
	private static final long serialVersionUID = 8866069440215164134L;
	/**
	 * 所属环节
	 */
	private String actGuid;
	/**
	 * 对应表单
	 */
	private String formGuid;
	/**
	 * 可见表达式
	 */
	private String visibleExpr;

	/**
	 * 可见表达式
	 */
	private String visibleUIExpr;

	/**
	 * 默认表单,即进入功能后第一个加载的.w
	 */
	private Boolean defaultForm;

	/**
	 * 环节表单插件选项
	 */
	private List<ActFormPluginOption> formPluginOptions = new ArrayList<ActFormPluginOption>();

	/**
	 * 非流程环节时的主表单，关联到保存按钮
	 */
	private String mainTable;

	private Integer order;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FactGuid" length="32" not-null="true"
	 *                   unique-key="M_ActFormOption_UNQ"
	 */
	public String getActGuid() {
		return actGuid;
	}

	public void setActGuid(String actGuid) {
		this.actGuid = actGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FformGuid" length="32" not-null="true"
	 *                   unique-key="M_ActFormOption_UNQ"
	 */
	public String getFormGuid() {
		return formGuid;
	}

	public void setFormGuid(String formGuid) {
		this.formGuid = formGuid;
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
	 * 可见表达式
	 * 
	 * @hibernate.property column="fdefaultForm"
	 */
	public Boolean getDefaultForm() {
		return defaultForm;
	}

	public void setDefaultForm(Boolean defaultForm) {
		this.defaultForm = defaultForm;
	}

	@AssembleTarget(value = ActFormPluginOption.class)
	public List<ActFormPluginOption> getFormPluginOptions() {
		return formPluginOptions;
	}

	public void setFormPluginOptions(List<ActFormPluginOption> formPluginOptions) {
		this.formPluginOptions = formPluginOptions;
	}

	/**
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FMainTable" length="32"
	 */
	public String getMainTable() {
		return mainTable;
	}

	public void setMainTable(String mainTable) {
		this.mainTable = mainTable;
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

}
