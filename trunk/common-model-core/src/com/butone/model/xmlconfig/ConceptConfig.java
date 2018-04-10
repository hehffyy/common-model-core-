package com.butone.model.xmlconfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.butone.model.utils.ValueHelper;

public class ConceptConfig implements XmlConfig {
	private List<LeftJoin> leftJoins;

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("ConceptConfig");
		d.addAttribute("majorVer", getMajorVersionNumber().toString());
		d.addAttribute("minorVer", getMinorVersionNumber().toString());

		Element leftJoins = d.addElement("LeftJoins");
		for (LeftJoin i : this.getLeftJoins()) {
			Element leftjoin = leftJoins.addElement("LeftJoin");
			leftjoin.addAttribute("guid", i.getTableGuid());
			leftjoin.addAttribute("table", i.getTableName());
			if (i.isOptional()) {
				leftjoin.addAttribute("optional", "true");
			}
			leftjoin.addAttribute("on", i.getOnRelation());
			leftjoin.addAttribute("return", i.getReturnFields());

		}
		return document;
	}

	@Override
	public String asXml() {
		Document document = getDocument();

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gbk");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLWriter writer;
		try {
			writer = new XMLWriter(out, format);
			writer.write(document);
			writer.close();
			return new String(out.toByteArray(), "gbk");
		} catch (Exception e) {
			return document.asXML();
		}
	}

	public boolean compatible(Integer marjorVersion, Integer minorVer) {
		return marjorVersion.equals(1) && minorVer.equals(0);
	}

	public Integer getMajorVersionNumber() {
		return 1;
	}

	public Integer getMinorVersionNumber() {
		return 0;
	}

	public List<LeftJoin> getLeftJoins() {
		return leftJoins != null ? leftJoins
				: (leftJoins = new ArrayList<LeftJoin>());
	}

	public void setLeftJoins(List<LeftJoin> leftJoins) {
		this.leftJoins = leftJoins;
	}

	@SuppressWarnings("unchecked")
	public void parseXml(String xml) throws Exception {
		this.leftJoins = new ArrayList<LeftJoin>();
		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;

		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);
		Element e = (Element) doc.selectSingleNode("/ConceptConfig");

		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}
		List<Element> nodes = doc
				.selectNodes("/ConceptConfig/LeftJoins/LeftJoin");
		for (Element node : nodes) {
			LeftJoin i = new LeftJoin();
			i.setOnRelation(node.attributeValue("on"));
			i.setOptional(ValueHelper.isTrue(node.attributeValue("optional")));
			i.setReturnFields(node.attributeValue("return"));
			i.setTableGuid(node.attributeValue("guid"));
			i.setTableName(node.attributeValue("table"));
			this.leftJoins.add(i);
		}
	}

	public static class LeftJoin {
		/**
		 * 关联物理表
		 */
		private String tableGuid;
		/**
		 * 物理表名
		 */
		private String tableName;
		/**
		 * 返回字段,逗号分割
		 */
		private String returnFields;
		/**
		 * on关系，必须是 表名1.字段=表名2.字段
		 */
		private String onRelation;
		/**
		 * 是否可选，如果为true，join表必须存在数据，否则返回空
		 */
		private boolean optional;

		public String getTableGuid() {
			return tableGuid;
		}

		public void setTableGuid(String tableGuid) {
			this.tableGuid = tableGuid;
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getReturnFields() {
			return returnFields;
		}

		public void setReturnFields(String returnFields) {
			this.returnFields = returnFields;
		}

		public String getOnRelation() {
			return onRelation;
		}

		public void setOnRelation(String onRelation) {
			this.onRelation = onRelation;
		}

		public boolean isOptional() {
			return optional;
		}

		public void setOptional(boolean optional) {
			this.optional = optional;
		}

	}

}
