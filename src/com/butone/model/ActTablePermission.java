package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;

/**
 * 环节物理表权限。
 * 
 * @author Administrator
 * @hibernate.class table="M_ActTablePermission" comment="环节表单选项"
 */
@XmlRootElement(name = "ActTablePermission")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "actGuid" }, target = FlowAct.class, targetKeys = { "guid" })
public class ActTablePermission extends Version implements Serializable, Sort {
	private static final long serialVersionUID = 1877921322221632520L;

	/**
	 * 所属环节
	 */
	private String actGuid;
	/**
	 * 对应的工作表
	 */
	private String tableGuid;

	/**
	 * 权限IDU。对于明细表用于控制是否创建新增、删除、保存按钮。对于主表，则是concept的rule属性。
	 */
	private String permission;

	/**
	 * 新增按钮的 enabledExpr,javaScript脚本
	 */
	private String insertCondition;
	/**
	 * 删除按钮的 enabledExpr,javaScript脚本
	 */
	private String deleteCondition;

	private String readonlyExpr;

	private Boolean readonly;

	/**
	 * 过滤条件，可以使用Java函数。
	 */
	private String filter;

	/**
	 * 属性废弃
	 */
	@Deprecated
	private Boolean refreshAfterNew;

	/**
	 * 启用的工作表插件列表
	 */
	private String tablePlugins;

	/**
	 * 环节字段选项
	 */
	private List<ActFieldOption> fieldOptions = new ArrayList<ActFieldOption>();

	private Integer order;

	@AssembleTarget(value = ActFieldOption.class)
	public List<ActFieldOption> getFieldOptions() {
		return fieldOptions;
	}

	public void setFieldOptions(List<ActFieldOption> fieldOptions) {
		this.fieldOptions = fieldOptions;
	}

	public ActFieldOption findActFieldOption(FieldDef fld) {
		for (ActFieldOption opt : getFieldOptions())
			if (opt.getFieldGuid().equals(fld.getGuid())
					|| opt.getFieldGuid().equals(fld.getRefField()))
				return opt;
		return null;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FactGuid" length="32" not-null="true"
	 *                   unique-key="M_ActTablePermission_UNQ"
	 */
	public String getActGuid() {
		return actGuid;
	}

	public void setActGuid(String actGuid) {
		this.actGuid = actGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FtableGuid" length="32" not-null="true"
	 *                   unique-key="M_ActTablePermission_UNQ"
	 */
	public String getTableGuid() {
		return tableGuid;
	}

	public void setTableGuid(String tableGuid) {
		this.tableGuid = tableGuid;
	}

	/**
	 * @hibernate.property column="Fpermission" length="30"
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @hibernate.property column="FReadOnly"
	 */
	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	/**
	 * @hibernate.property column="FReadonlyExpr" length="1000"
	 */
	public String getReadonlyExpr() {
		return readonlyExpr;
	}

	public void setReadonlyExpr(String readonlyExpr) {
		this.readonlyExpr = readonlyExpr;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @hibernate.property column="FinsertCondition" length="1000"
	 */
	public String getInsertCondition() {
		return insertCondition;
	}

	public void setInsertCondition(String insertCondition) {
		this.insertCondition = insertCondition;
	}

	/**
	 * @hibernate.property column="FdeleteCondition" length="1000"
	 */
	public String getDeleteCondition() {
		return deleteCondition;
	}

	public void setDeleteCondition(String deleteCondition) {
		this.deleteCondition = deleteCondition;
	}

	/**
	 * @hibernate.property column="FFilter" length="1000"
	 */
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * @hibernate.property column="FRefreshAfterNew"
	 */
	@Deprecated
	public Boolean getRefreshAfterNew() {
		return refreshAfterNew;
	}

	@Deprecated
	public void setRefreshAfterNew(Boolean refreshAfterNew) {
		this.refreshAfterNew = refreshAfterNew;
	}

	/**
	 * @hibernate.property column="FtablePlugins" length="1000"
	 */
	public String getTablePlugins() {
		return tablePlugins;
	}

	public void setTablePlugins(String tablePlugins) {
		this.tablePlugins = tablePlugins;
	}

	/**
	 * @hibernate.property column="FOrder" type="java.lang.Integer"
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
