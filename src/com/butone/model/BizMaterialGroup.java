package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;

/**
 * as table="业务与审批事项的对照关系，many-to-many。
 * 
 * @hibernate.class table="M_BizMaterialGroup" comment="业务与审批事项的对照关系"
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@SubModel(foreignKeys = "bizGuid", target = Biz.class, targetKeys = "guid", orderProperty = "order")
public class BizMaterialGroup extends Version implements Serializable, Sort {

	private static final long serialVersionUID = 1151024018959269295L;
	/**
	 * 审批事项目录
	 */
	private String groupGuid;
	/**
	 * 业务编号
	 */
	private String bizGuid;
	/**
	 * 生效条件Exp
	 */
	private String condition;
	/**
	 * 分组名称
	 */
	private String name;
	/**
	 * 是否系统组
	 */
	private Boolean isSystem;

	/**
	 * 审批材料
	 */
	private List<BizSelectMaterial> bizSelectMaterials = new ArrayList<BizSelectMaterial>();
	/**
	 * 排序
	 */
	private Integer order;

	/**
	 * 材料分组
	 * 
	 * @hibernate.property column="FGroupGuid" length="32"
	 */
	public String getGroupGuid() {
		return groupGuid;
	}

	public void setGroupGuid(String groupGuid) {
		this.groupGuid = groupGuid;
	}

	/**
	 * 所属业务
	 * 
	 * @hibernate.property column="FBizGuid" length="32"
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * 生效条件
	 * 
	 * @hibernate.property column="FCondition" length="2000"
	 */
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * 分组名称
	 * 
	 * @hibernate.property column="FName" length="200"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 是否系统组
	 * 
	 * @hibernate.property column="FisSys"
	 */
	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	@AssembleTarget(BizSelectMaterial.class)
	public List<BizSelectMaterial> getBizSelectMaterials() {
		return bizSelectMaterials;
	}

	public void setBizSelectMaterials(List<BizSelectMaterial> bizSelectMaterials) {
		this.bizSelectMaterials = bizSelectMaterials;
	}

	/**
	 * 分组次序
	 * 
	 * @hibernate.property column="FDispOrder" type="java.lang.Integer"
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
