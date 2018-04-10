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

public class CodeFieldConfig implements XmlConfig {
	// 编码guid
	private String codeguid;
	// 编码名称
	private String codename;
	// 参数节点设置
	private List<Parameter> parameterNodeValues = new ArrayList<Parameter>();
	// 手动编码，默认false即自动编码
	private boolean manualCoding;
	// 显示按钮
	private boolean showButton = true;
	// 参数节点关联的 表.字段, 表.字段
	private String relations;

	public String getCodeguid() {
		return codeguid;
	}

	public void setCodeguid(String codeguid) {
		this.codeguid = codeguid;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public List<Parameter> getParameterNodes() {
		return parameterNodeValues;
	}

	public void setParameterNodes(List<Parameter> parameterNodeValues) {
		this.parameterNodeValues = parameterNodeValues;
	}

	public boolean isManualCoding() {
		return manualCoding;
	}

	public void setManualCoding(boolean manualCoding) {
		this.manualCoding = manualCoding;
	}

	public boolean isShowButton() {
		return showButton;
	}

	public void setShowButton(boolean showButton) {
		this.showButton = showButton;
	}

	public String getRelations() {
		return relations;
	}

	public void setRelations(String relations) {
		this.relations = relations;
	}

	@Override
	public boolean compatible(Integer marjorVersion, Integer minorVer) {
		return marjorVersion.equals(1) && minorVer.equals(0);
	}

	@Override
	public Integer getMajorVersionNumber() {
		return 1;
	}

	@Override
	public Integer getMinorVersionNumber() {
		return 0;
	}

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("CodeFieldConfig");
		d.addAttribute("majorVer", getMajorVersionNumber().toString());
		d.addAttribute("minorVer", getMinorVersionNumber().toString());
		d.addAttribute("codeguid", codeguid);
		d.addAttribute("codename", codename);
		d.addAttribute("relations", relations);
		d.addAttribute("manualCoding", String.valueOf(manualCoding));
		d.addAttribute("showButton", String.valueOf(showButton));
		Element node = d.addElement("ParameterNodeValues");
		for (Parameter parameterNode : parameterNodeValues) {
			Element parameternode = node.addElement("ParameterNode");
			parameternode.addAttribute("guid", parameterNode.guid);
			parameternode
					.addAttribute("displayname", parameterNode.displayname);
			parameternode
					.addAttribute("parameValue", parameterNode.parameValue);
			parameternode
			.addAttribute("ui2Expr", parameterNode.ui2Expr);
		}
		return document;
	}

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

	@SuppressWarnings("unchecked")
	public void parseXml(String xml) throws Exception {

		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;

		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);
		Element e = (Element) doc.selectSingleNode("/CodeFieldConfig");

		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}
		this.codeguid = e.attributeValue("codeguid");
		this.codename = e.attributeValue("codename");
		this.relations = e.attributeValue("relations");
		this.manualCoding = Boolean.valueOf(e.attributeValue("manualCoding"));
		this.showButton = Boolean.valueOf(e.attributeValue("showButton"));
		List<Element> parameternodexml = doc
				.selectNodes("/CodeFieldConfig/ParameterNodeValues/ParameterNode");
		for (Element element : parameternodexml) {
			Parameter parameternode = new Parameter();
			parameternode.guid = element.attributeValue("guid");
			parameternode.displayname = element.attributeValue("displayname");
			parameternode.parameValue = element.attributeValue("parameValue");
			parameternode.ui2Expr = element.attributeValue("ui2Expr");
			parameterNodeValues.add(parameternode);
		}

	}

	public static class Parameter {

		/**
		 * 父字段
		 */
		public String guid;
		/**
		 * 节点类型字段
		 */
		public String displayname;
		/**
		 * 节点类型字段
		 */
		public String parameValue;

		/**
		 * ui2的表达式
		 */
		public String ui2Expr;

	}

}
