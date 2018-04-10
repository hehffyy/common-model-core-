package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * 环节表单组件选项，即本环节表单的插件是否有效。如果环节未选择某个插件，生成的html应该disable掉，最好能特殊显示。
 * 
 * @author Administrator
 * @hibernate.class table="M_ActFormPluginOption" comment="环节表单选项"
 */
@XmlRootElement(name = "ActFormPluginOption")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "actGuid", "formGuid" }, target = ActFormOption.class, targetKeys = {
		"actGuid", "formGuid" })
public class ActFormPluginOption extends Version implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1921637402632940620L;
	/**
	 * 插件Guid
	 */
	private String pluginGuid;
	/**
	 * 所属环节
	 */
	private String actGuid;
	/**
	 * 所属表单
	 */
	private String formGuid;
	/**
	 * 生效表达式
	 */
	private String enabledExpr;
	/**
	 * 提示信息，
	 */
	private String tipInfo;
	

	/**
	 * @hibernate.property
	 * @hibernate.column name="fpluginGuid" length="32" not-null="true"
	 *                   unique-key="M_ActFormPluginOption_UNQ"
	 */
	public String getPluginGuid() {
		return pluginGuid;
	}

	public void setPluginGuid(String pluginGuid) {
		this.pluginGuid = pluginGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FactGuid" length="32" not-null="true"
	 *                   unique-key="M_ActFormPluginOption_UNQ"
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
	 *                   unique-key="M_ActFormPluginOption_UNQ"
	 */
	public String getFormGuid() {
		return formGuid;
	}

	public void setFormGuid(String formGuid) {
		this.formGuid = formGuid;
	}

	/**
	 * @hibernate.property column="FenabledExpr" length="1000"
	 */
	public String getEnabledExpr() {
		return enabledExpr;
	}

	public void setEnabledExpr(String enabledExpr) {
		this.enabledExpr = enabledExpr;
	}

	/**
	 * @hibernate.property column="FtipInfo" length="200"
	 */
	public String getTipInfo() {
		return tipInfo;
	}

	public void setTipInfo(String tipInfo) {
		this.tipInfo = tipInfo;
	}
	

}
