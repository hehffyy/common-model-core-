package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.butone.model.annotation.AssembleTarget;

/**
 * 系统字典
 * 
 * @author Administrator
 * @hibernate.class table="M_SysDict" comment="系统字典表"
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SysDict extends Version implements Serializable {
	private static final long serialVersionUID = 5061442788939752049L;

	/**
	 * 字典名称
	 */
	private String name;

	private List<SysDictItem> items = new ArrayList<SysDictItem>();

	/**
	 * @hibernate.property column="FName" length="100" not-null="true"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// /**
	// * @hibernate.list inverse="true" cascade="delete" lazy="false"
	// * @hibernate.collection-key column="FType" property-ref="name"
	// * @hibernate.collection-index column="FDispOrder"
	// * @hibernate.collection-one-to-many class="com.butone.model.SysDictItem"
	// * @return
	// */
	@AssembleTarget(value = SysDictItem.class)
	public List<SysDictItem> getItems() {
		return items;
	}

	public void setItems(List<SysDictItem> items) {
		this.items = items;

	}

}
