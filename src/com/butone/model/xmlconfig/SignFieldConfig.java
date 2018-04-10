package com.butone.model.xmlconfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.butone.model.utils.ValueHelper;

public class SignFieldConfig implements XmlConfig {

	public enum HandWriteDevice {
		HW_ESP1030;
		public static String[] getAllDevice() {
			String[] ret = new String[HandWriteDevice.values().length];
			for (int i = 0; i < HandWriteDevice.values().length; i++) {
				ret[i] = HandWriteDevice.values()[i].name();
			}
			return ret;
		}

		public String getDeviceCLSID() {
			switch (this) {
			case HW_ESP1030:
				return "E8F5278C-0C72-4561-8F7E-CCBC3E48C2E3";
			default:
				return null;
			}
		}
	}

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"gbk\"?>"
				+ "<SignField majorVer=\"1\" minorVer=\"0\">"
				+ "<protectFields><![CDATA[cpml.FGUID,cpml.version,ddxt.sbmc,ddxt.sbbh]]></protectFields>"
				+ "<ref><![CDATA[zd4918]]></ref></SignField>";
		try {
			SignFieldConfig config = new SignFieldConfig();
			config.parseXml(xml);
			System.out.println(config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String protectFields;
	private String ref;
	private Boolean useSignImage;
	private Boolean verifyPassword;
	private String signDateField;
	private String handWriteDevice;
	private String processOperation;

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("SignField");
		d.addAttribute("majorVer", getMajorVersionNumber().toString());
		d.addAttribute("minorVer", getMinorVersionNumber().toString());

		if (useSignImage != null)
			d.addAttribute("useSignImage", useSignImage.toString());

		if (handWriteDevice != null)
			d.addAttribute("handWriteDevice", handWriteDevice);

		if (verifyPassword != null)
			d.addAttribute("verifyPassword", verifyPassword.toString());

		if (signDateField != null)
			d.addAttribute("signDateField", signDateField);
		if (processOperation != null)
			d.addAttribute("processOperation", processOperation);

		Element node = d.addElement("protectFields");
		node.addCDATA(protectFields);

		node = d.addElement("ref");
		node.addCDATA(ref);
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
	public boolean compatible(Integer marjorVersion, Integer minorVer) {
		return true;
	}

	@Override
	public Integer getMajorVersionNumber() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Integer getMinorVersionNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void parseXml(String xml) throws Exception {
		this.protectFields = null;
		this.ref = null;
		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;
		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);

		// 版本检查
		Element e = (Element) doc.selectSingleNode("SignField");
		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}
		Attribute attr = e.attribute("useCA");

		attr = e.attribute("useSignImage");
		if (attr != null)
			this.useSignImage = Boolean.valueOf(attr.getText());

		attr = e.attribute("handWriteDevice");
		if (attr != null)
			this.handWriteDevice = attr.getText();

		attr = e.attribute("verifyPassword");
		if (attr != null)
			this.verifyPassword = Boolean.valueOf(attr.getText());

		attr = e.attribute("processOperation");
		if (attr != null)
			this.processOperation = attr.getText();

		if (e.attribute("signDateField") != null)
			this.signDateField = e.attribute("signDateField").getText();

		e = (Element) doc.selectSingleNode("SignField/protectFields");
		if (e != null) {
			this.protectFields = e.getText();
		}

		e = (Element) doc.selectSingleNode("SignField/ref");
		if (e != null) {
			this.ref = e.getText();
		}
	}

	public String getProtectFields() {
		return protectFields;
	}

	public void setProtectFields(String protectFields) {
		this.protectFields = protectFields;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setVerifyPassword(Boolean verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	public Boolean getVerifyPassword() {
		return verifyPassword;
	}

	public String getSignDateField() {
		return signDateField;
	}

	public void setSignDateField(String signDateField) {
		this.signDateField = signDateField;
	}

	public Boolean getUseSignImage() {
		return useSignImage;
	}

	public void setUseSignImage(Boolean useSignImage) {
		this.useSignImage = useSignImage;
	}

	public String getHandWriteDevice() {
		return handWriteDevice;
	}

	public void setHandWriteDevice(String handWriteDevice) {
		this.handWriteDevice = handWriteDevice;
	}

	public String getProcessOperation() {
		return processOperation;
	}

	public void setProcessOperation(String processOperation) {
		this.processOperation = processOperation;
	}

}
