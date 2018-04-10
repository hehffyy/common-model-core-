package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;
import com.butone.model.annotation.SubModels;

/**
 * 审批事项目录 。 gkMaterial允许的子节点包括gkApprove、gkConditionGroup，例如：
 * 采矿权新立、变更、注销登记(gkMaterial) -- 采矿权新立 (gkApprove) -- 采矿权变更 (gkApprove) --
 * 权利人变更(gkConditionGroup) -- 矿区范围 (gkConditionGroup) -- 采矿权注销(gkApprove)
 * 
 * 国家秘密测绘利用审批(gkApprove) -- 基础材料 (gkConditionGroup) -- 本省申请本省(gkMaterial) --
 * 首次申请(gkConditionGroup) -- 外省申请本省(gkMaterial) -- 首次申请(gkConditionGroup)
 * 
 * @hibernate.class table="M_MaterialGroup" comment="材料分组表"
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SubModels(subModels = {
		@SubModel(inverse = true, target = BizMaterialGroup.class, foreignKeys = { "groupGuid" }, targetKeys = { "guid" }),
		@SubModel(foreignKeys = { "parentGuid" }, target = MaterialGroup.class, targetKeys = { "guid" }) })
public class MaterialGroup extends Version implements Serializable, Sort {

	private static final long serialVersionUID = -219776720054892557L;
	private String parentGuid;
	/**
	 * 目录名称
	 */
	private String groupName;
	/**
	 * 目录类型，gkMaterial:事项目录
	 * ,gkApprove:审批事项,gkSubApprove:审批子项,gkConditionGroup:条件分组
	 */
	private String groupKind;
	/**
	 * 目录编码，应对广东省网上服务事项编码
	 */
	private String groupCode;
	/**
	 * 事项编码，应对广东省电子监察审批事项
	 */
	private String approveCode;
	/**
	 * 事项子项编码，应对广东省电子监察审批事项
	 */
	private String subApproveCode;
	/**
	 * 限办天数，应对广东省网上办事大厅办事须知
	 */
	private Integer limitDays;
	/**
	 * 归属部门ID
	 */
	private String belongDeptId;
	/**
	 * 归属部门名称
	 */
	private String belongDeptName;
	/**
	 * 服务事项类型,0：其他 1：行政许可事项 2：非行政许可审批事项 3：社会事务服务事项
	 */
	private String approveKind;
	/**
	 * 事项级别，1：国家级 2：省级 3：地市级 4：区县级 5：乡镇级
	 */
	private String approveLevel;
	/**
	 * 是否投资事项 1：是 0：否
	 */
	private String isInvest;
	/**
	 * 树形全路径
	 */
	private String path;

	/**
	 * 实现顺序
	 */
	private Integer dispOrder;

	private List<MaterialGroup> childs = new ArrayList<MaterialGroup>();

	private List<Material> materials = new ArrayList<Material>();

	/**
	 * 父分组
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FParentGuid" length="32"
	 *                   index="M_MaterialGroup_PID"
	 */
	public String getParentGuid() {
		return parentGuid;
	}

	public void setParentGuid(String parentGuid) {
		this.parentGuid = parentGuid;
	}

	/**
	 * shen名称
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FgroupName" length="200"
	 */
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FGroupKind" length="20" not-null="true"
	 */
	public String getGroupKind() {
		return groupKind;
	}

	public void setGroupKind(String groupKind) {
		this.groupKind = groupKind;
	}

	/**
	 * 分组编码，应对广东省网上服务事项编码
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FgroupCode" length="50"
	 */
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * 审批事项编码，应对广东省电子监察审批事项
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FApproveCode" length="50"
	 */
	public String getApproveCode() {
		return approveCode;
	}

	public void setApproveCode(String approveCode) {
		this.approveCode = approveCode;
	}

	/**
	 * 审批事项子项编码，应对广东省电子监察审批事项子项
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FSubApproveCode" length="50"
	 */
	public String getSubApproveCode() {
		return subApproveCode;
	}

	public void setSubApproveCode(String subApproveCode) {
		this.subApproveCode = subApproveCode;
	}

	/**
	 * 限办天数，应对广东省电子监察审批事项子项及服务事项
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FLimitDays"
	 */
	public Integer getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(Integer limitDays) {
		this.limitDays = limitDays;
	}

	/**
	 * 归属部门，应对广东省服务事项，即主办部门
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FbelongDeptId" length="64"
	 */
	public String getBelongDeptId() {
		return belongDeptId;
	}

	public void setBelongDeptId(String belongDeptId) {
		this.belongDeptId = belongDeptId;
	}

	/**
	 * 归属部门名称，应对广东省服务事项，即主办部门
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FbelongDeptName" length="200"
	 */
	public String getBelongDeptName() {
		return belongDeptName;
	}

	public void setBelongDeptName(String belongDeptName) {
		this.belongDeptName = belongDeptName;
	}

	/**
	 * 服务事项类型，应对广东省服务事项类型
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FApproveKind" length="2"
	 */
	public String getApproveKind() {
		return approveKind;
	}

	public void setApproveKind(String approveKind) {
		this.approveKind = approveKind;
	}

	/**
	 * 服务事项类型，应对广东省服务事项类型
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FapproveLevel" length="2"
	 */
	public String getApproveLevel() {
		return approveLevel;
	}

	public void setApproveLevel(String approveLevel) {
		this.approveLevel = approveLevel;
	}

	/**
	 * 是否投资事项，应对广东省服务事项类型
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FInvest" length="2"
	 */
	public String getIsInvest() {
		return isInvest;
	}

	public void setIsInvest(String isInvest) {
		this.isInvest = isInvest;
	}

	/**
	 * 显示顺序
	 * 
	 * @hibernate.property column="FDispOrder" type="java.lang.Integer"
	 * @return
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
	 * 树形全路径
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FPath" length="2000"
	 */
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	// /**
	// * @hibernate.set inverse="true" cascade="delete" order-by="FDispOrder"
	// * lazy="false"
	// * @hibernate.collection-key column="FParentGuid"
	// * @hibernate.collection-one-to-many
	// class="com.butone.model.MaterialGroup"
	// * @return
	// */
	@AssembleTarget(value = MaterialGroup.class)
	public List<MaterialGroup> getChilds() {
		return childs;
	}

	public void setChilds(List<MaterialGroup> childs) {
		this.childs = childs;
	}

	@AssembleTarget(value = Material.class)
	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

}
