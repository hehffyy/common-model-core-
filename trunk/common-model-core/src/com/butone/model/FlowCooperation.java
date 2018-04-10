package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 机构内流程协同，即数据交换控制模型
 * 
 * @hibernate.class table="M_FlowCooperation"
 * @author Administrator
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FlowCooperation extends Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6950113315197315491L;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 显示名称
	 */
	private String dispName;
	/**
	 * 模型
	 */
	private String model;
	/**
	 * 图例
	 */
	private String diagram;

	/**
	 * 名称英文
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="100" not-null="true"
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
	 * 协同模型
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
	
	



}
