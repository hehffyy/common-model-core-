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

public class UnitFieldConfig implements XmlConfig {

	private String unitType;
	private String dispUnit;
	private String storeUnit;
	private String useDialog;

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("UnitField");
		root.addAttribute("majorVer", getMajorVersionNumber().toString());
		root.addAttribute("minorVer", getMinorVersionNumber().toString());

		Element node = root.addElement("unitType");
		node.addCDATA(unitType);

		node = root.addElement("dispUnit");
		node.addCDATA(dispUnit);

		node = root.addElement("storeUnit");
		node.addCDATA(storeUnit);

		if (useDialog != null) {
			root.addAttribute("useDialog", useDialog);
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

	@Override
	public void parseXml(String xml) throws Exception {

		this.unitType = null;
		this.dispUnit = null;
		this.storeUnit = null;
		this.useDialog = null;

		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;
		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);

		// 版本检查
		Element e = (Element) doc.selectSingleNode("UnitField");
		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}

		this.useDialog = e.attributeValue("useDialog");

		e = (Element) doc.selectSingleNode("UnitField/unitType");
		if (e != null) {
			this.unitType = e.getText();
		}

		e = (Element) doc.selectSingleNode("UnitField/dispUnit");
		if (e != null) {
			this.dispUnit = e.getText();
		}

		e = (Element) doc.selectSingleNode("UnitField/storeUnit");
		if (e != null) {
			this.storeUnit = e.getText();
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

	public String getStoreUnit() {
		return storeUnit;
	}

	public void setStoreUnit(String storeUnit) {
		this.storeUnit = storeUnit;
	}

	public String getUseDialog() {
		return useDialog;
	}

	public void setUseDialog(String useDialog) {
		this.useDialog = useDialog;
	}

}
