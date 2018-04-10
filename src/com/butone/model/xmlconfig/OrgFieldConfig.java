package com.butone.model.xmlconfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.butone.model.utils.ValueHelper;

/**
 * 业务字段[OrgSelect]配置模型
 * 
 * @author Administrator
 */
public class OrgFieldConfig implements XmlConfig {
	public static class OrgRange {
		private String condition; // java 生效条件

		private String expression;// java 机构函数表达式

		public String getCondition() {
			return condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

		public String getExpression() {
			return expression;
		}

		public void setExpression(String expression) {
			this.expression = expression;
		}
	}

	private String multiSelect; // 是否多选 true|false
	private String orgKinds; // 可选组织类型 ogn|dpt|pos|psm
	private String showType; // 显示类型[列表/树] list|tree
	private String onBeforeCalcOrgExpr;
	private List<OrgRange> ranges = new ArrayList<OrgRange>(); // 机构范围

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("OrgSelectField");
		d.addAttribute("majorVer", getMajorVersionNumber().toString()); // 主版本
		d.addAttribute("minorVer", getMinorVersionNumber().toString()); // 小版本

		Element node = d.addElement("multiSelect");
		node.addCDATA(multiSelect);

		node = d.addElement("showType");
		node.addCDATA(showType);

		node = d.addElement("orgKinds");
		node.addCDATA(orgKinds);

		node = d.addElement("onBeforeCalcOrgExpr");
		node.addCDATA(onBeforeCalcOrgExpr);

		node = d.addElement("ranges");
		for (OrgRange range : ranges) {
			Element sub = node.addElement("range");
			sub.addElement("condition").addCDATA(range.condition);
			sub.addElement("expression").addCDATA(range.expression);
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

	@SuppressWarnings("unchecked")
	@Override
	public void parseXml(String xml) throws Exception {

		this.multiSelect = null;
		this.orgKinds = null;
		this.showType = null;
		this.onBeforeCalcOrgExpr = null;
		this.ranges.clear();
		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;
		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);

		// 版本检查
		Element e = (Element) doc.selectSingleNode("OrgSelectField");
		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}

		e = (Element) doc.selectSingleNode("OrgSelectField/multiSelect");
		if (e != null) {
			this.multiSelect = e.getText();
		}

		e = (Element) doc.selectSingleNode("OrgSelectField/orgKinds");
		if (e != null) {
			this.orgKinds = e.getText();
		}

		e = (Element) doc.selectSingleNode("OrgSelectField/showType");
		if (e != null) {
			this.showType = e.getText();
		}

		e = (Element) doc
				.selectSingleNode("OrgSelectField/onBeforeCalcOrgExpr");
		if (e != null) {
			this.onBeforeCalcOrgExpr = e.getText();
		}

		List<Node> nodes = doc.selectNodes("//range");
		for (Node n : nodes) {
			OrgRange range = new OrgRange();
			range.condition = n.selectSingleNode("condition").getText();
			range.expression = n.selectSingleNode("expression").getText();
			this.ranges.add(range);
		}

	}

	public boolean compatible(Integer marjorVersion, Integer minorVer) {
		return true;
	};

	@Override
	public Integer getMajorVersionNumber() {
		return 1;
	}

	@Override
	public Integer getMinorVersionNumber() {
		return 0;
	}

	public String getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}

	public String getOrgKinds() {
		return orgKinds;
	}

	public void setOrgKinds(String orgKinds) {
		this.orgKinds = orgKinds;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public List<OrgRange> getRanges() {
		return ranges;
	}

	public void setRanges(List<OrgRange> ranges) {
		this.ranges = ranges;
	}

	public String getOnBeforeCalcOrgExpr() {
		return onBeforeCalcOrgExpr;
	}

	public void setOnBeforeCalcOrgExpr(String onBeforeCalcOrgExpr) {
		this.onBeforeCalcOrgExpr = onBeforeCalcOrgExpr;
	}

}
