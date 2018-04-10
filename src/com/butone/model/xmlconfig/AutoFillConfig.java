package com.butone.model.xmlconfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.butone.model.utils.ValueHelper;

/**
 * 自动填充配置
 * 
 * @author Administrator
 * 
 */
public class AutoFillConfig implements XmlConfig {
	/**
	 * 默认值(常量)
	 */
	private String defaultValue;
	/**
	 * 创建表达式(java)
	 */
	private String createExpr;
	/**
	 * 计算表达式(js)
	 */
	private String calcExpr;

	private String calcExpr2;

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getCreateExpr() {
		return createExpr;
	}

	public void setCreateExpr(String createExpr) {
		this.createExpr = createExpr;
	}

	public String getCalcExpr() {
		return calcExpr;
	}

	public void setCalcExpr(String calcExpr) {
		this.calcExpr = calcExpr;
	}

	public String getCalcExpr2() {
		return calcExpr2;
	}

	public void setCalcExpr2(String calcExpr2) {
		this.calcExpr2 = calcExpr2;
	}

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"gbk\"?>"
				+ "<autoFillDef majorVer=\"1\" minorVer=\"0\">"
				+ "<defaultValue><![CDATA[]]></defaultValue>"
				+ "<createExpr><![CDATA[guid()]]></createExpr>"
				+ "<calcExpr><![CDATA[]]></calcExpr><calcExpr2><![CDATA[]]></calcExpr2>"
				+ "</autoFillDef>";
		try {
			new AutoFillConfig().parseXml(xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("autoFillDef");
		d.addAttribute("majorVer", getMajorVersionNumber().toString());
		d.addAttribute("minorVer", getMinorVersionNumber().toString());

		Element node = d.addElement("defaultValue");
		node.addCDATA(defaultValue);

		node = d.addElement("createExpr");
		node.addCDATA(createExpr);

		node = d.addElement("calcExpr");
		node.addCDATA(calcExpr);

		node = d.addElement("calcExpr2");
		node.addCDATA(calcExpr2);
		return document;
	}

	public String asXml() {
		// 这里处理默认空值处理
		if (ValueHelper.isEmpty(defaultValue)
				&& ValueHelper.isEmpty(createExpr)
				&& ValueHelper.isEmpty(calcExpr)
				&& ValueHelper.isEmpty(calcExpr2)) {
			return null;
		}
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

	public Integer getMajorVersionNumber() {
		return 1;
	}

	public Integer getMinorVersionNumber() {
		return 0;
	}

	public void parseXml(String xml) throws Exception {
		this.calcExpr = null;
		this.calcExpr2 = null;
		this.createExpr = null;
		this.defaultValue = null;
		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);
		Element e = (Element) doc.selectSingleNode("/autoFillDef");

		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}
		e = (Element) doc.selectSingleNode("/autoFillDef/defaultValue");
		if (e != null) {
			this.defaultValue = e.getText();
		}
		e = (Element) doc.selectSingleNode("/autoFillDef/createExpr");
		if (e != null) {
			this.createExpr = e.getText();
		}
		e = (Element) doc.selectSingleNode("/autoFillDef/calcExpr");
		if (e != null) {
			this.calcExpr = e.getText();
		}
		e = (Element) doc.selectSingleNode("/autoFillDef/calcExpr2");
		if (e != null) {
			this.calcExpr2 = e.getText();
		}
	}

	public boolean compatible(Integer marjorVersion, Integer minorVer) {
		return marjorVersion.equals(1) && minorVer.equals(0);
	}
}
