package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import com.butone.model.annotation.SubModel;

/**
 * 审批材料，即原X3业务要件。
 * 
 * @hibernate.class table="M_Material" comment="审批材料"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(target = MaterialGroup.class, foreignKeys = { "groupGuid" }, targetKeys = { "guid" }, orderProperty = "order")
public class Material extends Version implements Serializable, Sort {
	private static final long serialVersionUID = -5210224515636399559L;
	@XmlAttribute
	private String groupGuid;
	/**
	 * 材料名称
	 */
	@XmlAttribute(name = "name")
	private String materialName;
	/**
	 * 是否必要材料
	 */
	@XmlAttribute
	private Boolean required;
	/**
	 * 是否需要原件
	 */
	@XmlAttribute
	private Boolean originalRequired;

	@XmlAttribute
	private Integer dispOrder;

	/**
	 * 所属分组
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FgroupGuid" length="32"
	 *                   index="M_Material_Group"
	 */
	public String getGroupGuid() {
		return groupGuid;
	}

	public void setGroupGuid(String groupGuid) {
		this.groupGuid = groupGuid;
	}

	/**
	 * 材料名称
	 * 
	 * @hibernate.property column="FmaterialName" length="200"
	 */
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	/**
	 * 是否必要材料
	 * 
	 * @hibernate.property column="Frequired"
	 */
	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	/**
	 * 是否需要原件
	 * 
	 * @hibernate.property column="ForiginalRequired"
	 */
	public Boolean getOriginalRequired() {
		return originalRequired;
	}

	public void setOriginalRequired(Boolean originalRequired) {
		this.originalRequired = originalRequired;
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
