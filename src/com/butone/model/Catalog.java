package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;
import com.butone.model.annotation.SubModels;

/**
 * 目录，通用树形结构。每个节点都可以认为是一个X5的业务功能
 * 
 * @hibernate.class table="M_Catalog"
 * 
 */
@XmlRootElement(name = "Catalog")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModels(subModels = { @SubModel(foreignKeys = { "parentGuid" }, target = Catalog.class, targetKeys = { "guid" }, orderProperty = "order"),
		@SubModel(inverse = true, target = Biz.class, foreignKeys = { "catalogGuid" }, targetKeys = { "guid" }) })
public class Catalog extends BaseResource implements Serializable, Sort {
	public static boolean isNullGuid(String guid) {
		return guid == null || "-1".equals(guid) || "".equals(guid);
	}

	private static final long serialVersionUID = -8328171958272475305L;
	private String parentGuid = "-1";
	private String name;
	private String dispName;
	private String kind;
	private Integer dispOrder;
	private String path;
	private String pathName;
	private List<Biz> bizList = new ArrayList<Biz>();
	@XmlTransient
	private Catalog parent;

	private List<Catalog> childs = new ArrayList<Catalog>();

	public Catalog getParent() {
		return parent;
	}

	public void setParent(Catalog parent) {
		this.parent = parent;
	}

	/**
	 * 上级目录
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FParentGuid" length="32"
	 *                   unique-key="M_Catalog_NAME_UNQ"
	 */
	public String getParentGuid() {
		return parentGuid;
	}

	public void setParentGuid(String parentGuid) {
		this.parentGuid = parentGuid;
	}

	/**
	 * 目录名称英文用于创建Model文件夹
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="100" not-null="true"
	 *                   unique-key="M_Catalog_NAME_UNQ"
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
	 * @hibernate.property column="FDispName" length="100" not-null="true"
	 */
	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/**
	 * 显示顺序
	 * 
	 * @hibernate.property column="FDispOrder" type="java.lang.Integer"
	 */
	@Override
	public Object getOrder() {
		return dispOrder;
	}

	@Override
	public void setOrder(Object dispOrder) {
		if (dispOrder != null)
			this.dispOrder = new Integer(dispOrder.toString());
		else
			this.dispOrder = null;
	}

	/**
	 * 目录类型
	 * 
	 * @hibernate.property column="FKind"
	 */
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * 树形全路径
	 * 
	 * @hibernate.property column="FPath" length="1000"
	 */
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 树形全路径
	 * 
	 * @hibernate.property column="FPathName" length="2000"
	 */
	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	// /**
	// * 父目录
	// *
	// * @hibernate.many-to-one column="FParentGuid" cascade="none"
	// * class="com.butone.model.Catalog" insert="false"
	// * update="false" not-found="ignore" lazy="false"
	// */
	// public Catalog getParent() {
	// return parent;
	// }
	//
	// public void setParent(Catalog parent) {
	// this.parent = parent;
	// }

	// /**
	// * 子目录
	// *
	// * @hibernate.set inverse="true" cascade="none" order-by="FDispOrder"
	// * lazy="false"
	// * @hibernate.collection-key column="FParentGuid"
	// * @hibernate.collection-one-to-many class="com.butone.model.Catalog"
	// * @return
	// */
	@AssembleTarget(Catalog.class)
	public List<Catalog> getChilds() {
		return childs;
	}

	public void setChilds(List<Catalog> childs) {
		this.childs = childs;
	}

	// /**
	// * 数据库，X5特性
	// *
	// * @hibernate.property column="FDatabaseName" length="20"
	// */
	//	
	// public String getDatabaseName() {
	// return databaseName;
	// }
	//
	//	
	// public void setDatabaseName(String databaseName) {
	// this.databaseName = databaseName;
	// }

	// * @hibernate.set inverse="true" cascade="none" order-by="FDispOrder"
	// * @hibernate.collection-key column="FCatalogGuid"
	// * @hibernate.collection-one-to-many class="com.butone.model.Biz"

	/**
	 * 业务列表
	 * 
	 * @return
	 */
	@AssembleTarget(Biz.class)
	public List<Biz> getBizList() {
		return bizList;
	}

	public void setBizList(List<Biz> bizList) {
		this.bizList = bizList;
	}

}
