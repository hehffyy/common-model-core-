package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;
import com.butone.model.utils.ValueHelper;

/**
 * 物理表定义，其中包含 “物理表逻辑插件”列表，作用于所有引用的概念的Action执行时机。
 * 
 * @hibernate.class table="M_TableDef"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TableDef")
@SubModel(foreignKeys = "bizGuid", target = Biz.class, targetKeys = "guid", orderProperty = "order")
public class TableDef extends BaseResource implements Serializable, Sort {
	private static final long serialVersionUID = -2489737591749074893L;
	/**
	 * 物理表明，当为引用表时，为bizData的id
	 */
	private String name;
	/**
	 * 显示名称
	 */
	private String dispName;
	/**
	 * 所属业务
	 */
	private String bizGuid;
	// /**
	// * 所属数据库
	// */
	// private String belongDB;
	/**
	 * 显示顺序
	 */
	private Integer dispOrder;
	/**
	 * 主表GUID
	 */
	private String masterTable;
	/**
	 * 本表的外键字段，对应主表的主键（注意X5只支持1个主键）
	 */
	private String foreignKeys;

	/**
	 * 级联定义
	 */
	private String cascade;

	// /**
	// * 案卷编号字段
	// */
	// private String bizRecIdField;

	/**
	 * 工作表模板
	 */
	private String template;

	private List<FieldDef> fields = new ArrayList<FieldDef>();
	/**
	 * 物理表属性，TableConfig的xml数据。
	 * 
	 * @see com.butone.model.xmlconfig.TableConfig
	 */
	private String properties;
	// @XmlTransient
	// private Set<TableLogicPlugin> logicPlugins;
	/**
	 * 索引定义
	 */
	private List<IndexDef> indexDefs = new ArrayList<IndexDef>();

	/**
	 * 工作表插件
	 */
	private List<TableLogicPlugin> logicPlugins = new ArrayList<TableLogicPlugin>();
	/**
	 * 可以引用其他业务的表，refTable为被引用表的GUID。
	 */
	private String refTable;
	/**
	 * 是否视图表，默认为否。当为视图时
	 */
	private Boolean isView;

	/**
	 * 结构已改变，需要重新生成物理表
	 */
	private Boolean structChanged;

	/**
	 * 排序字段
	 */
	private String orderBy;

	/**
	 * 默认过滤条件
	 */
	private String condition;

	/**
	 * 工作表类型
	 */
	private String kind;

	/**
	 * 标记此表已删除，后台控制字段，用于显示控制。销毁时，才会删除记录并drop物理表。
	 */
	@XmlTransient
	private Boolean deleted = false;

	// /**
	// * 所属数据库GUID
	// *
	// * @hibernate.property
	// * @hibernate.column name="FBelongDB" length="32" not-null="true"
	// * unique-key="M_TableDef_DBName"
	// */
	// public String getBelongDB() {
	// return belongDB;
	// }
	//
	// public void setBelongDB(String belongDB) {
	// this.belongDB = belongDB;
	// }

	/**
	 * 物理表名
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="30" not-null="true"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 如果是引用表，记录引用的工作表GUID
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FRefTable" comment="记录引用的工作表GUID" length="32"
	 * 
	 */
	public String getRefTable() {
		return refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
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
	 * 所属目录
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FbizGuid" length="32" not-null="true"
	 *                   index="M_TableDef_Biz"
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
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

	// * @hibernate.set inverse="true" cascade="delete" lazy="false"
	// * order-by="FDispOrder"
	// * @hibernate.collection-key column="FTableGuid"
	// * @hibernate.collection-one-to-many class="com.butone.model.FieldDef"
	/**
	 * 物理字段列表
	 * 
	 */
	@AssembleTarget(value = FieldDef.class)
	public List<FieldDef> getFields() {
		return fields;
	}

	public void setFields(List<FieldDef> fields) {
		this.fields = fields;
	}

	/**
	 * @hibernate.property column="FOrderBy" length="200"
	 */
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @hibernate.property column="Fcondition" length="1000" comment="默认过滤条件"
	 */
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FMasterTable" length="32" comment="主表Guid"
	 */
	public String getMasterTable() {
		return masterTable;
	}

	public void setMasterTable(String masterTable) {
		this.masterTable = masterTable;
	}

	/**
	 * 对应于主表主键的外键字段
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FForeignKeys" length="30" comment="对应于主表主键的外键字段"
	 * 
	 */
	public String getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(String foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	/**
	 * 明细表级联关系定义
	 * 
	 * @hibernate.property
	 * @hibernate.column name="Fcascade" length="30" comment="明细表级联定义"
	 * 
	 */
	public String getCascade() {
		return cascade;
	}

	public void setCascade(String cascade) {
		this.cascade = cascade;
	}

	// /**
	// * 流程工作表的案卷编号字段
	// *
	// * @hibernate.property
	// * @hibernate.column name="FbizRecIdField" length="30"
	// * comment="流程工作表的案卷编号字段"
	// *
	// */
	// public String getBizRecIdField() {
	// return bizRecIdField;
	// }
	//
	// public void setBizRecIdField(String bizRecIdField) {
	// this.bizRecIdField = bizRecIdField;
	// }

	/**
	 * 模板
	 * 
	 * @hibernate.property
	 * @hibernate.column name="Ftemplate" length="32" comment="模板"
	 * 
	 */
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * 索引
	 */
	@AssembleTarget(value = IndexDef.class)
	public List<IndexDef> getIndexDefs() {
		return indexDefs;
	}

	/**
	 * 模板
	 * 
	 * @hibernate.property
	 * @hibernate.column name="fKind" length="20" comment="表类型"
	 * @see com.butone.model.enums.TableKind
	 */
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setIndexDefs(List<IndexDef> indexDefs) {
		this.indexDefs = indexDefs;
	}

	/**
	 * 插件
	 */
	@AssembleTarget(value = TableLogicPlugin.class)
	public List<TableLogicPlugin> getLogicPlugins() {
		return logicPlugins;
	}

	public void setLogicPlugins(List<TableLogicPlugin> logicPlugins) {
		this.logicPlugins = logicPlugins;
	}

	/**
	 * @hibernate.property column="FIsview"
	 * @return
	 */
	public Boolean getIsView() {
		return isView;
	}

	public void setIsView(Boolean isView) {
		this.isView = isView;
	}

	/**
	 * 结构已改变，需要重新生成物理表
	 * 
	 * @hibernate.property column="FstructChanged"
	 * @return
	 */
	public Boolean getStructChanged() {
		return structChanged;
	}

	public void setStructChanged(Boolean structChanged) {
		this.structChanged = structChanged;
	}

	/**
	 * 物理表属性，包括索引级、唯一约束等
	 * 
	 * @see com.butone.model.xmlconfig.TableConfig
	 * 
	 * @hibernate.property column="Fproperties"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 * @return
	 */
	public String getProperties() {
		return properties;
	}

	public void setProperties(String attributes) {
		this.properties = attributes;
	}

	/**
	 * 删除标记
	 * 
	 * @hibernate.property column="Fdeleted"
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * 通过字段名获得字段定义
	 * 
	 * @param fieldName
	 * @return
	 */
	public FieldDef getField(String fieldName) {
		if (fields == null || fields.size() == 0) {
			return null;
		}
		Iterator<FieldDef> itorFld = fields.iterator();
		while (itorFld.hasNext()) {
			FieldDef field = itorFld.next();
			if (field.getName().equalsIgnoreCase(fieldName)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 通过字段GUID获得字段定义
	 * 
	 * @param fieldName
	 * @return
	 */
	public FieldDef getFieldByGuid(String guid) {
		if (fields == null || fields.size() == 0) {
			return null;
		}
		Iterator<FieldDef> itorFld = fields.iterator();
		while (itorFld.hasNext()) {
			FieldDef field = itorFld.next();
			if (field.getGuid().equalsIgnoreCase(guid)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 获取主键字段
	 * 
	 * @return
	 */
	public String getPrimaryField() {
		for (FieldDef fld : this.fields) {
			if (fld.getPrimary() != null && fld.getPrimary()) {
				return fld.getName();
			}
		}
		return null;
	}

	/**
	 * 获得主键字符串，多个主键用split分割
	 * 
	 * @param split
	 * @return
	 */
	public String unionKeyNames(String split) {
		StringBuffer buf = new StringBuffer();
		for (FieldDef field : this.fields) {
			if (ValueHelper.isTrue(field.getPrimary())) {
				if (buf.length() > 0) {
					buf.append(split);
				}
				buf.append(field.getName());
			}
		}
		if (buf.length() == 0) {
			return null;
		}
		return buf.toString();
	}

	/**
	 * 获得主键字符串，多个主键用split分割
	 * 
	 * @param split
	 * @return
	 */
	public String unionFieldNames(String split) {
		StringBuffer buf = new StringBuffer();
		for (FieldDef field : this.fields) {
			if (buf.length() > 0) {
				buf.append(split);
			}
			buf.append(field.getName());
		}
		return buf.toString();
	}

	/**
	 * 判断是否存在索引，包含且只有一个指定的字段
	 * 
	 * @param fieldName
	 * @return
	 */
	public boolean containsSingleFieldUniqueIndex(String fieldName) {
		for (IndexDef i : this.getIndexDefs()) {
			String[] fields = i.getFieldNames().split(",");
			if (fields.length == 1
					&& fields[0].toLowerCase().equals(fieldName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
