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
@XmlType(name = "businessActionConfig", propOrder = { "subBizs" })
public class BusinessActionConfig {

	/**
	 * 
	 */

	@XmlElementWrapper(name = "subBizs")
	@XmlElement(name = "biz")
	private List<BizAction> subBizs;

	public List<BizAction> getSubBizs() {
		return subBizs;
	}

	public void setSubBizs(List<BizAction> subBizs) {
		this.subBizs = subBizs;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement
	@XmlType(name = "biz", propOrder = { "id", "name", "condtions" })
	public static class BizAction {

		@XmlAttribute
		private String id;

		@XmlAttribute
		private String name;

		@XmlElementWrapper(name = "condtions")
		@XmlElement(name = "condtion")
		private List<Condition> condtions;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Condition> getCondtions() {
			return condtions;
		}

		public void setCondtions(List<Condition> condtions) {
			this.condtions = condtions;
		}

	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement
	@XmlType(name = "condition", propOrder = { "expr", "operates" })
	public static class Condition {

		@XmlElement
		@XmlJavaTypeAdapter(CDATAXmlAdapter.class)
		private String expr;

		public String getExpr() {
			return expr;
		}

		public void setExpr(String expr) {
			this.expr = expr;
		}

		@XmlElementWrapper(name = "operates")
		@XmlElement(name = "operate")
		private List<String> operates;

		public List<String> getOperates() {
			if (operates == null)
				operates = new ArrayList<String>();
			return operates;
		}

		public void setOperates(List<String> operates) {
			this.operates = operates;
		}

	}

}
