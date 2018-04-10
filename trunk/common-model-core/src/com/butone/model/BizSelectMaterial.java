package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.butone.model.annotation.SubModel;

/**
 * as table="业务与审批事项的对照关系，many-to-many。
 * 
 * @hibernate.class table="M_BizSelectMaterial" comment="事项与材料对照关系"
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@SubModel(foreignKeys = "groupGuid", target = BizMaterialGroup.class, targetKeys = "guid", orderProperty = "order")
public class BizSelectMaterial extends Version implements Serializable, Sort {

	private static final long serialVersionUID = 421323110135080119L;
	/**
	 * 事项目录
	 */
	private String groupGuid;
	/**
	 * 材料Guid
	 */
	private String materialGuid;
	/**
	 * 材料分数
	 */
	private Integer mtNums;
	/**
	 * 排序
	 */
	private Integer order;
	/**
	 * 是否默认勾选
	 */
	private Boolean isDefSelect;
	/**
	 * 介质材料：光盘、文本
	 */
	private String medium;

	/**
	 * 是否必要材料
	 */
	private Boolean required;

	/**
	 * 是否原件
	 */
	private Boolean originalRequired;

	/**
	 * 
	 * @hibernate.property column="FRequired"
	 */
	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	/**
	 * 
	 * @hibernate.property column="FOriginalRequired"
	 */
	public Boolean getOriginalRequired() {
		return originalRequired;
	}

	public void setOriginalRequired(Boolean originalRequired) {
		this.originalRequired = originalRequired;
	}

	/**
	 * 
	 * @hibernate.property column="FMedium" length="32"
	 */
	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	/**
	 * 事项目录
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FGroupGuid" length="32"
	 *                   index="M_BizSelectMaterial_GroupGuid"
	 */
	public String getGroupGuid() {
		return groupGuid;
	}

	public void setGroupGuid(String groupGuid) {
		this.groupGuid = groupGuid;
	}

	/**
	 * 材料Guid
	 * 
	 * @hibernate.property column="FMaterialGuid" length="32"
	 */
	public String getMaterialGuid() {
		return materialGuid;
	}

	public void setMaterialGuid(String materialGuid) {
		this.materialGuid = materialGuid;
	}

	/**
	 * 是否默认选中
	 * 
	 * @hibernate.property column="FIsDefSelect"
	 */
	public Boolean getIsDefSelect() {
		return isDefSelect;
	}

	public void setIsDefSelect(Boolean isDefSelect) {
		this.isDefSelect = isDefSelect;
	}

	/**
	 * 材料份数
	 * 
	 * @hibernate.property column="FMtNums"
	 */
	public Integer getMtNums() {
		return mtNums;
	}

	public void setMtNums(Integer mtNums) {
		this.mtNums = mtNums;
	}

	/**
	 * 材料次序
	 * 
	 * @hibernate.property column="FMtOrder" type="java.lang.Integer"
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
