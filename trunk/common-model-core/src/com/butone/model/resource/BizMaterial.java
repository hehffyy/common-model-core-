package com.butone.model.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "material", propOrder = { "id", "name", "required","medium", "originalRequired","dispOrder","isDefSelect" })
public class BizMaterial {

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String name;

	/**
	 * 是否必要材料
	 */
	@XmlAttribute
	private Boolean required;
	
	/**
	 * 介质材料：光盘、文本
	 */
	@XmlAttribute
	private String medium;

	/**
	 * 是否原件
	 */
	@XmlAttribute
	private Boolean originalRequired;
	
	/**
	 * 材料分数
	 */
	@XmlAttribute
	private Integer mtNums;
	
	/**
	 * 排序
	 */
	@XmlAttribute
	private Integer dispOrder;
	

	/**
	 * 是否默认：在系统中未扫描要件，需要给客户打印回执单，打印是否默认为是的材料名称
	 */
	@XmlAttribute
	private Boolean isDefSelect;
	
	
	/**
	 * 材料分组名称：允许为业务的材料定义对应的分组
	 */
	@XmlAttribute
	private String matNav;
	
	 
	
	
	
	
	
	public String getMatNav() {
		return matNav;
	}

	public void setMatNav(String matNav) {
		this.matNav = matNav;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getOriginalRequired() {
		return originalRequired;
	}

	public void setOriginalRequired(Boolean originalRequired) {
		this.originalRequired = originalRequired;
	}
	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public Boolean getIsDefSelect() {
		return isDefSelect;
	}

	public void setIsDefSelect(Boolean isDefSelect) {
		this.isDefSelect = isDefSelect;
	}

	public Integer getMtNums() {
		return mtNums;
	}

	public void setMtNums(Integer mtNums) {
		this.mtNums = mtNums;
	}

	public Integer getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Integer dispOrder) {
		this.dispOrder = dispOrder;
	}
 

}
