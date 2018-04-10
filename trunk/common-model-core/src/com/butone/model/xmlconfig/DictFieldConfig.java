package com.butone.model.xmlconfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.butone.model.utils.ValueHelper;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DictFieldConfig implements XmlConfig {
	private String itemName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("DictField");
		d.addAttribute("majorVer", getMajorVersionNumber().toString());
		d.addAttribute("minorVer", getMinorVersionNumber().toString());

		Element node = d.addElement("itemName");
		node.addCDATA(itemName);
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
		// TODO Auto-generated method stub
		return 0;
	}

	public void parseXml(String xml) throws Exception {
		this.itemName = null;
		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;
		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);

		// 版本检查
		Element e = (Element) doc.selectSingleNode("/DictField");
		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}

		e = (Element) doc.selectSingleNode("/DictField/itemName");
		if (e != null) {
			this.itemName = e.getText();
		}

	}

}
