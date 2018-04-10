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

/**
 * 业务定义
 * 
 * @hibernate.class table="M_Biz" comment="业务表"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BIZ")
@SubModel(foreignKeys = { "catalogGuid" }, target = Catalog.class, targetKeys = { "guid" }, orderProperty = "order")
public class Biz extends Version implements Serializable, Sort {
	//
	private static final long serialVersionUID = -912227341874813866L;
	private String catalogGuid;
	private String bizKind;
	private String name;
	private String dispName;
	private String databaseName;
	private Integer dispOrder;
	/**
	 * 业务类型，比如 办公、审批
	 */
	private String bizType;
	/**
	 * 关联业务
	 */
	private List<BizRelation> relBIZs = new ArrayList<BizRelation>();
	/**
	 * 工作表
	 */
	private List<TableDef> tables = new ArrayList<TableDef>();
	/**
	 * 业务逻辑插件
	 */
	private List<BizLogicPlugin> logicPlugins = new ArrayList<BizLogicPlugin>();
	/**
	 * 业务规则
	 */
	private List<BizRule> rules = new ArrayList<BizRule>();

	/**
	 * 文件导入插件
	 */
	private List<FileImportPlugin> fileImportPlugins = new ArrayList<FileImportPlugin>();
	/**
	 * 表单导航
	 */
	private List<BizNav> navs = new ArrayList<BizNav>();
	/**
	 * 材料导航
	 */
	private List<MaterialNav> materialnavs = new ArrayList<MaterialNav>();
	/**
	 * 业务审批事项
	 */
	private List<BizMaterialGroup> bizMaterialGroups = new ArrayList<BizMaterialGroup>();
	/**
	 * 业务流程
	 */
	private List<Flow> flows = new ArrayList<Flow>();
	/**
	 * 业务表单
	 */
	private List<Form> forms = new ArrayList<Form>();

	/**
	 * 业务资源文件
	 */
	private List<BizResourceFile> resourceFile = new ArrayList<BizResourceFile>();

	/**
	 * 对象关系映射 是否已创建
	 */
	private Boolean ORMCreated;

	/**
	 * 锁定业务，不允许生成任何资源，不允许修改
	 */
	private Boolean locked;

	@XmlTransient
	private Boolean deleted;

	@XmlTransient
	private Catalog catalog;

	/**
	 * 所属目录
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FCatalogGuid" length="32"
	 *                   unique-key="M_BIZ_NAME_UNQ"
	 */
	public String getCatalogGuid() {
		return catalogGuid;
	}

	public void setCatalogGuid(String catalogGuid) {
		this.catalogGuid = catalogGuid;
	}

	/**
	 * 目录名称英文用于创建Model文件夹
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="100" not-null="true"
	 *                   unique-key="M_BIZ_NAME_UNQ"
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
	 * 业务类型：流程、非流程、查询
	 * 
	 * @see com.butone.model.enums.BizKind
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FBizKind" length="20" comment="业务类型"
	 */
	public String getBizKind() {
		return bizKind;
	}

	public void setBizKind(String bizKind) {
		this.bizKind = bizKind;
	}

	/**
	 * 业务分类：办公、审批
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FBizType" length="20" comment="业务类型"
	 */
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	/**
	 * 删除标记
	 * 
	 * @see com.butone.model.enums.BizKind
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FDeleted" comment="删除标记"
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * 数据库，X5特性
	 * 
	 * @hibernate.property column="FDatabaseName" length="20"
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * 对象关系映射 是否已创建
	 * 
	 * @hibernate.property column="FORMCreated"
	 */
	public Boolean getORMCreated() {
		return ORMCreated;
	}

	public void setORMCreated(Boolean oRMCreated) {
		ORMCreated = oRMCreated;
	}

	/**
	 * 对象关系映射 是否已创建
	 * 
	 * @hibernate.property column="FLocked"
	 */
	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@AssembleTarget(TableDef.class)
	public List<TableDef> getTables() {
		return tables;
	}

	public void setTables(List<TableDef> tables) {
		this.tables = tables;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	@AssembleTarget(Catalog.class)
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@AssembleTarget(BizLogicPlugin.class)
	public List<BizLogicPlugin> getLogicPlugins() {
		return logicPlugins;
	}

	public void setLogicPlugins(List<BizLogicPlugin> logicPlugins) {
		this.logicPlugins = logicPlugins;
	}

	@AssembleTarget(BizRule.class)
	public List<BizRule> getRules() {
		return rules;
	}

	public void setRules(List<BizRule> rules) {
		this.rules = rules;
	}

	@AssembleTarget(FileImportPlugin.class)
	public List<FileImportPlugin> getFileImportPlugins() {
		return fileImportPlugins;
	}

	public void setFileImportPlugin(List<FileImportPlugin> fileImportPlugins) {
		this.fileImportPlugins = fileImportPlugins;
	}

	@AssembleTarget(BizResourceFile.class)
	public List<BizResourceFile> getResourceFile() {
		return resourceFile;
	}

	public void setResourceFile(List<BizResourceFile> resourceFile) {
		this.resourceFile = resourceFile;
	}

	@AssembleTarget(BizNav.class)
	public List<BizNav> getNavs() {
		return navs;
	}

	public void setNavs(List<BizNav> navs) {
		this.navs = navs;
	}

	@AssembleTarget(BizRelation.class)
	public List<BizRelation> getRelBIZs() {
		return relBIZs;
	}

	public void setRelBIZs(List<BizRelation> relBIZs) {
		this.relBIZs = relBIZs;
	}

	@AssembleTarget(MaterialNav.class)
	public List<MaterialNav> getMaterialNavs() {
		return materialnavs;
	}

	public void setMaterialNavs(List<MaterialNav> materialnavs) {
		this.materialnavs = materialnavs;
	}

	@AssembleTarget(Flow.class)
	public List<Flow> getFlows() {
		return flows;
	}

	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	@AssembleTarget(BizMaterialGroup.class)
	public List<BizMaterialGroup> getBizMaterialGroups() {
		return bizMaterialGroups;
	}

	public void setBizMaterialGroups(List<BizMaterialGroup> bizMaterialGroups) {
		this.bizMaterialGroups = bizMaterialGroups;
	}

	@AssembleTarget(Form.class)
	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public TableDef findTableDef(String tableGuid) {
		for (TableDef table : this.getTables())
			if (table.getGuid().equals(tableGuid))
				return table;
		return null;
	}

	public TableDef findTableDefByName(String tableName) {
		for (TableDef table : this.getTables())
			if (table.getName().equals(tableName))
				return table;
		return null;
	}

	public BizNav findNav(String navGuid) {
		for (BizNav nav : this.getNavs())
			if (nav.getGuid().equals(navGuid))
				return nav;
		return null;
	}

	public BizResourceFile findBizResourceFile(String fileGuid) {
		for (BizResourceFile file : this.getResourceFile())
			if (file.getGuid().equals(fileGuid))
				return file;
		return null;
	}

}
