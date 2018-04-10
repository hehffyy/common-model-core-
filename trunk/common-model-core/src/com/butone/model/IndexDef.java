package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.butone.model.annotation.SubModel;

/**
 * 业务定义
 * 
 * @hibernate.class table="M_IndexDef" comment="索引定义"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = "tableGuid", target = TableDef.class, targetKeys = "guid")
public class IndexDef extends Version implements Serializable {
	private static final long serialVersionUID = 7603719964314424401L;
	private String name;
	private String tableGuid;
	private String fieldNames;
	private Integer isUnique;

	/**
	 * 索引名称
	 * 
	 * @hibernate.property column="Fname" length="30" not-null="true"
	 *                     unique="true"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 所属表
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FtableGuid" length="32" not-null="true"
	 *                   index="M_IndexDef_Table"
	 */
	public String getTableGuid() {
		return tableGuid;
	}

	public void setTableGuid(String tableGuid) {
		this.tableGuid = tableGuid;
	}

	/**
	 * 字段列表，多字段逗号分隔
	 * 
	 * @hibernate.property column="FfieldNames" length="200" not-null="true"
	 */
	public String getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String fieldNames) {
		this.fieldNames = fieldNames;
	}

	/**
	 * 字段列表，多字段逗号分隔
	 * 
	 * @hibernate.property column="FisUnique"
	 */
	public Integer getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(Integer isUnique) {
		this.isUnique = isUnique;
	}

	public boolean isUnique() {
		return 1 == this.getIsUnique();
	}

}
