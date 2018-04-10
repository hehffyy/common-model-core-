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

public class AttachmentEditorFieldConfig implements XmlConfig {

	private String access; // 权限
	private String displayButtons; // 操作

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("AttachmentField");
		d.addAttribute("majorVer", getMajorVersionNumber().toString()); // 主版本
		d.addAttribute("minorVer", getMinorVersionNumber().toString()); // 小版本

		Element node = d.addElement("access");
		node.addCDATA(access);

		node = d.addElement("displayButtons");
		node.addCDATA(displayButtons);
		return document;
	}

	@Override
	public String asXml() {
		Document doc = getDocument();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gbk");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLWriter writer;
		try {
			writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
			return new String(out.toByteArray(), "gbk");
		} catch (Exception e) {
			return doc.asXML();
		}
	}

	@Override
	public void parseXml(String xml) throws Exception {

		this.access = null;
		this.displayButtons = null;
		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;
		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);

		// 版本检查
		Element e = (Element) doc.selectSingleNode("AttachmentField");
		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}

		e = (Element) doc
				.selectSingleNode("AttachmentField/access");
		if (e != null) {
			this.access = e.getText();
		}

		e = (Element) doc.selectSingleNode("AttachmentField/displayButtons");
		if (e != null) {
			this.displayButtons = e.getText();
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

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getDisplayButtons() {
		return displayButtons;
	}

	public void setDisplayButtons(String displayButtons) {
		this.displayButtons = displayButtons;
	}

 
}
