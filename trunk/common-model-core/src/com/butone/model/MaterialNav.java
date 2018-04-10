package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.butone.model.annotation.SubModel;

/**
 * 业务表单导航
 * 
 * @author Administrator
 * @hibernate.class table="M_MaterialNav" comment="业务表"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = "bizGuid", target = Biz.class, targetKeys = "guid", orderProperty = "order")
public class MaterialNav extends Version implements Serializable, Sort {
	private static final long serialVersionUID = 561237424609523238L;
	/**
	 * 所属业务
	 */
	private String bizGuid;
	/**
	 * 导航栏标题
	 */
	private String label;
	/**
	 * 显示顺序
	 */
	private Integer dispOrder;

	/**
	 * 包含材料
	 */
	private String materials;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FbizGuid" length="32" not-null="true"
	 *                   index="M_MaterialNav_Biz"
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * @hibernate.property column="Flabel" length="50" not-null="true"
	 */
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @hibernate.property column="FdispOrder" type="java.lang.Integer"
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

	/**
	 * @hibernate.property column="Fmaterials" length=1000
	 */
	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

}
