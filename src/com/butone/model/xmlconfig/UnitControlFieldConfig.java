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

public class UnitControlFieldConfig implements XmlConfig {

	// private String data;
	// private String ref;
	// private String labelRef;
	private String unitType;
	private String dispUnit;
	private String controlField;

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("UnitControlField");
		d.addAttribute("majorVer", getMajorVersionNumber().toString());
		d.addAttribute("minorVer", getMinorVersionNumber().toString());

		Element node = d.addElement("unitType");
		node.addCDATA(unitType);

		node = d.addElement("dispUnit");
		node.addCDATA(dispUnit);

		node = d.addElement("controlField");
		node.addCDATA(controlField);
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

	@Override
	public void parseXml(String xml) throws Exception {

		this.unitType = null;
		this.controlField = null;

		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;
		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);

		// 版本检查
		Element e = (Element) doc.selectSingleNode("UnitControlField");
		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}

		e = (Element) doc.selectSingleNode("UnitControlField/unitType");
		if (e != null) {
			this.unitType = e.getText();
		}

		e = (Element) doc.selectSingleNode("UnitControlField/dispUnit");
		if (e != null) {
			this.dispUnit = e.getText();
		}

		e = (Element) doc.selectSingleNode("UnitControlField/controlField");
		if (e != null) {
			this.controlField = e.getText();
		}
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
	public boolean compatible(Integer marjorVersion, Integer minorVer) {
		return true;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getDispUnit() {
		return dispUnit;
	}

	public void setDispUnit(String dispUnit) {
		this.dispUnit = dispUnit;
	}

	public String getControlField() {
		return controlField;
	}

	public void setControlField(String controlField) {
		this.controlField = controlField;
	}

}
