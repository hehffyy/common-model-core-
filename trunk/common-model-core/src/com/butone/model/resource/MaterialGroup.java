package com.butone.model.resource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.xml.CDATAXmlAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "materialGroup", propOrder = { "id", "name", "condition",
		"subGroups", "materials" })
public class MaterialGroup {

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String name;
	@XmlAttribute
	private Integer order;
	// @XmlAttribute
	// private String kind;

	@XmlElement
	@XmlJavaTypeAdapter(CDATAXmlAdapter.class)
	private String condition;

	@XmlElementWrapper(name = "subGroups")
	@XmlElement(name = "materialGroup")
	private ArrayList<MaterialGroup> subGroups;

	@XmlElementWrapper(name = "materials")
	@XmlElement(name = "material")
	private ArrayList<BizMaterial> materials;

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public String getKind() {
	// return kind;
	// }
	//
	// public void setKind(String kind) {
	// this.kind = kind;
	// }

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<MaterialGroup> getSubGroups() {
		return subGroups;
	}

	public void setSubGroups(ArrayList<MaterialGroup> subGroups) {
		this.subGroups = subGroups;
	}

	public List<BizMaterial> getMaterials() {
		if (materials == null)
			materials = new ArrayList<BizMaterial>();
		return materials;
	}

	public void setMaterials(ArrayList<BizMaterial> materials) {
		this.materials = materials;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
