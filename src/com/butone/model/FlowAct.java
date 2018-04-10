package com.butone.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;

/**
 * 流程环节
 * 
 * @hibernate.class table="M_FlowAct"
 * @author Administrator
 * 
 */
@XmlRootElement(name = "FlowAct")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "flowGuid" }, target = Flow.class, targetKeys = { "guid" }, orderProperty = "name")
public class FlowAct extends Version implements Serializable {
	private static final long serialVersionUID = 407656042422675321L;
	private String flowGuid;
	private String name;
	private String dispName;
	/**
	 * 限制办理天数,用于流转时计算限制办理日期。如果存在于环节分组，此属性无效。
	 */
	private BigDecimal limitDays;
	private String dayConutKind;
	/**
	 * 限制办理日期表达式,用于流转时计算限制办理日期。如果存在于环节分组，此属性无效。
	 */
	private String limitDateExpr;

	/**
	 * 环节属性,大字段对象，备用
	 */
	private String properties;

	/**
	 * 流程协同Guid,当流程图为协同控制时，每一个环节为流程协同实例，描述了多个流程间的协作过程
	 */
	private String flowCoopGuid;

	/**
	 * 是否流程协作等待接收环节
	 */
	private Boolean isCoopReceiver;

	/**
	 * 禁止流转后取回
	 */
	private Boolean forbidWithdraw;

	/**
	 * 环节表单选项
	 */
	private List<ActFormOption> formOptions = new ArrayList<ActFormOption>();

	/**
	 * 环节工作表权限
	 */
	private List<ActTablePermission> tablePermissions = new ArrayList<ActTablePermission>();
	/**
	 * 环节业务规则选项
	 */
	private List<ActBizRuleOption> bizRuleOptions = new ArrayList<ActBizRuleOption>();

	/**
	 * 环节业务规则选项
	 */
	private List<ActLogicPluginOption> logicPluginOptions = new ArrayList<ActLogicPluginOption>();

	/**
	 * 环节业务操作
	 */
	private List<ActBizOperation> bizOperations = new ArrayList<ActBizOperation>();

	/**
	 * 环节类型
	 */
	private String flowActKind;

	/**
	 * 流程入口，用于收件功能
	 */
	private Boolean flowEntry;

	/**
	 * 本环节需要编码的字段（表.字段,表.字段,）
	 */
	private String codeFields;

	/**
	 * 材料权限
	 */
	private String materialAuth;

	/**
	 * 环节上传的附件的关联字段(表.字段)
	 */
	private String uploadField;

	/**
	 * 功能模板1
	 */
	private String funcTemplet;

	/**
	 * 功能模板2
	 */
	private String funcTemplet2;

	/**
	 * 环节根表
	 */
	private String mainTable;

	/**
	 * 环节是否可见
	 */
	private Boolean visible;
	
	/**
	 * 移动端是否启用
	 */
	private Boolean mobileEnable;

	/**
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FuploadField" length="50"
	 */
	public String getUploadField() {
		return uploadField;
	}

	public void setUploadField(String uploadField) {
		this.uploadField = uploadField;
	}

	/**
	 * 名称英文，用于对应process中Activity.Name
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="100" not-null="true"
	 *                   unique-key="M_FLOWACT_NAME_UNQ"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 所属流程
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FflowGuid" length="32" not-null="true"
	 *                   unique-key="M_FLOWACT_NAME_UNQ"
	 */
	public String getFlowGuid() {

		return flowGuid;
	}

	public void setFlowGuid(String flowGuid) {
		this.flowGuid = flowGuid;
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
	 * 限办日期
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FLimitDays" precision="10" scale="1"
	 */
	public BigDecimal getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(BigDecimal limitDays) {
		// if(limitDays != null && limitDays == 0)
		// return;
		this.limitDays = limitDays;
	}

	/**
	 * 限办日期类型：工作日、自然日
	 * 
	 * @hibernate.property column="FdayConutKind" length="10"
	 */
	public String getDayConutKind() {
		return dayConutKind;
	}

	public void setDayConutKind(String dayConutKind) {
		this.dayConutKind = dayConutKind;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FLimitDateExpr" length="1000"
	 */
	public String getLimitDateExpr() {
		return limitDateExpr;
	}

	public void setLimitDateExpr(String limitDateExpr) {
		this.limitDateExpr = limitDateExpr;
	}

	/**
	 * 环节属性，备用
	 * 
	 * @hibernate.property column="Fproperties"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 * @return
	 */
	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	/**
	 * 静态功能
	 * 
	 * @hibernate.property column="FflowActKind" length="20"
	 * @return
	 */
	public String getFlowActKind() {
		return flowActKind;
	}

	public void setFlowActKind(String flowActKind) {
		this.flowActKind = flowActKind;
	}

	/**
	 * 入口环节
	 * 
	 * @hibernate.property column="FflowEntry"
	 * @return
	 */
	public Boolean getFlowEntry() {
		return flowEntry;
	}

	public void setFlowEntry(Boolean flowEntry) {
		this.flowEntry = flowEntry;
	}

	/**
	 * 模板文件1名称
	 * 
	 * @hibernate.property column="FfuncTemplet" length="255"
	 */
	public String getFuncTemplet() {
		return funcTemplet;
	}

	public void setFuncTemplet(String funcTemplet) {
		this.funcTemplet = funcTemplet;
	}

	/**
	 * 模板文件2名称
	 * 
	 * @hibernate.property column="FfuncTemplet2" length="255"
	 */
	public String getFuncTemplet2() {
		return funcTemplet2;
	}

	public void setFuncTemplet2(String funcTemplet2) {
		this.funcTemplet2 = funcTemplet2;
	}

	/**
	 * 环节根表
	 * 
	 * @hibernate.property column="FMainTable" length="32"
	 */
	public String getMainTable() {
		return mainTable;
	}

	public void setMainTable(String mainTable) {
		this.mainTable = mainTable;
	}

	/**
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FcodeFields" length="200"
	 */
	public String getCodeFields() {
		return codeFields;
	}

	public void setCodeFields(String codeFields) {
		this.codeFields = codeFields;
	}

	/**
	 * 流程协同Guid,当流程为协同控制时，每一个环节为流程协同实例，描述了多个流程间的协作过程
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FflowCoopGuid" length="32"
	 */
	public String getFlowCoopGuid() {
		return flowCoopGuid;
	}

	/**
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FVisible"
	 */
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setFlowCoopGuid(String flowCoopGuid) {
		this.flowCoopGuid = flowCoopGuid;
	}

	/**
	 * 流程协同Guid,当流程为协同控制时，每一个环节为流程协同实例，描述了多个流程间的协作过程
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FisCoopReceiver"
	 */
	public Boolean getIsCoopReceiver() {
		return isCoopReceiver;
	}

	public void setIsCoopReceiver(Boolean isCoopReceiver) {
		this.isCoopReceiver = isCoopReceiver;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FforbidWithdraw"
	 */
	public Boolean getForbidWithdraw() {
		return forbidWithdraw;
	}

	public void setForbidWithdraw(Boolean forbidWithdraw) {
		this.forbidWithdraw = forbidWithdraw;
	}

	@AssembleTarget(value = ActFormOption.class)
	public List<ActFormOption> getFormOptions() {
		return formOptions;
	}

	public void setFormOptions(List<ActFormOption> formOptions) {
		this.formOptions = formOptions;
	}

	@AssembleTarget(value = ActTablePermission.class)
	public List<ActTablePermission> getTablePermissions() {
		return tablePermissions;
	}

	public ActTablePermission findActTablePermission(String tableGuid) {
		for (ActTablePermission opt : getTablePermissions()) {
			if (opt.getTableGuid().equals(tableGuid))
				return opt;
		}
		return null;
	}

	public ActFormOption findActFormOption(String formGuid) {
		for (ActFormOption opt : getFormOptions()) {
			if (opt.getFormGuid().equals(formGuid))
				return opt;
		}
		return null;
	}

	public void setTablePermissions(List<ActTablePermission> tablePermissions) {
		this.tablePermissions = tablePermissions;
	}

	@AssembleTarget(value = ActBizRuleOption.class)
	public List<ActBizRuleOption> getBizRuleOptions() {
		return bizRuleOptions;
	}

	public void setBizRuleOptions(List<ActBizRuleOption> bizRuleOptions) {
		this.bizRuleOptions = bizRuleOptions;
	}

	@AssembleTarget(value = ActLogicPluginOption.class)
	public List<ActLogicPluginOption> getLogicPluginOptions() {
		return logicPluginOptions;
	}

	public void setLogicPluginOptions(
			List<ActLogicPluginOption> logicPluginOptions) {
		this.logicPluginOptions = logicPluginOptions;
	}

	@AssembleTarget(value = ActBizOperation.class)
	public List<ActBizOperation> getBizOperations() {
		return bizOperations;
	}

	public void setBizOperations(List<ActBizOperation> bizOperations) {
		this.bizOperations = bizOperations;
	}

	/**
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FmaterialAuth" length="30"
	 */
	public String getMaterialAuth() {
		return materialAuth;
	}

	public void setMaterialAuth(String materialAuth) {
		this.materialAuth = materialAuth;
	}

	/**
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FmobileEnable"
	 */
	public Boolean getMobileEnable() {
		return mobileEnable;
	}

	public void setMobileEnable(Boolean mobileEnable) {
		this.mobileEnable = mobileEnable;
	}
	
	

}
