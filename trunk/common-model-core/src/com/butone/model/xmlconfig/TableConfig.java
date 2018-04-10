package com.butone.model.xmlconfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.butone.model.utils.ValueHelper;

/**
 * 物理表属性
 * 
 * @author Administrator
 */
public class TableConfig implements XmlConfig {

	public static void main(String[] args) {
		try {
			TableConfig config = new TableConfig();
			System.out.println(config.asXml());
			config.parseXml(config.asXml());
			config.setSelect("select * from slb where fbizrecid=:p1");
			config.setQuery(true);
			Parameter p = new Parameter();
			p.setName("p1");
			p.setExpr("'acb'");
			config.getParameters().add(p);
			System.out.println(config.asXml());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class Parameter {
		String name;
		String expr;// java 表达式

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getExpr() {
			return expr;
		}

		public void setExpr(String expr) {
			this.expr = expr;
		}
	}

	/**
	 * 是否查询，如果是查询X5将使用SQL来构造Table，不支持DDL。sql语句存储到select属性中
	 */
	private boolean isQuery;
	private String dataModel; // tableType
	private String select;// fields,split by ","
	private String from; // tableName
	private String condition; // whereCondition。当用于描述查询业务表时，如果是Query，那么这里解析:param的名称，","分隔
	private String orderBy; //
	private String fieldAlias;//
	private String fieldNames;// table.field 逗号分隔，与fieldAlias顺序一致
	private Integer limit;
	private boolean distinct;
	private XmlConfig advance;
	private List<Parameter> parameters = new ArrayList<Parameter>();

	public List<Parameter> getParameters() {
		return parameters;
	}

	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("tableConfig");
		root.addAttribute("majorVer", getMajorVersionNumber().toString());
		root.addAttribute("minorVer", getMinorVersionNumber().toString());

		root.addAttribute("isQuery", new Boolean(isQuery).toString());
		if (this.limit != null)
			root.addAttribute("limit", this.limit.toString());

		root.addAttribute("distinct", Boolean.valueOf(this.distinct).toString());

		root.addElement("dataModel").addCDATA(dataModel);
		root.addElement("select").addCDATA(select);
		root.addElement("from").addCDATA(from);
		root.addElement("condition").addCDATA(condition);
		root.addElement("orderBy").addCDATA(orderBy);
		root.addElement("fieldAlias").addCDATA(fieldAlias);
		root.addElement("fieldNames").addCDATA(fieldNames);

		Element paramsNode = root.addElement("parameters");
		for (Parameter p : parameters) {
			Element pEle = paramsNode.addElement("parameter");
			pEle.addAttribute("name", p.getName());
			pEle.addElement("expr").addCDATA(p.getExpr());
		}

		if (advance != null) {
			Document advanceDoc = advance.getDocument();
			root.addElement("advance").add(advanceDoc.getRootElement());
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
	
	public String asXml(String charset) {
		Document document = getDocument();

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charset);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLWriter writer;
		try {
			writer = new XMLWriter(out, format);
			writer.write(document);
			writer.close();
			return new String(out.toByteArray(), charset);
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

	public void parseXml(String xml, Class<?> advanceClass) throws Exception {
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);
		Element root = doc.getRootElement();

		if (!compatible(
				Integer.parseInt(root.attribute("majorVer").getValue()),
				Integer.parseInt(root.attribute("minorVer").getValue()))) {
			throw new Exception("XML数据版本不兼容");
		}

		this.condition = root.selectSingleNode("condition").getText();
		this.dataModel = root.selectSingleNode("dataModel").getText();
		this.select = root.selectSingleNode("select").getText();
		this.from = root.selectSingleNode("from").getText();
		this.orderBy = root.selectSingleNode("orderBy").getText();
		Node subNode = root.selectSingleNode("fieldAlias");
		if (subNode != null)
			this.fieldAlias = subNode.getText();
		subNode = root.selectSingleNode("fieldNames");
		if (subNode != null)
			this.fieldNames = subNode.getText();
		this.isQuery = Boolean.parseBoolean(root.attributeValue("isQuery"));
		Attribute attr = root.attribute("limit");
		if (attr != null && !ValueHelper.isEmpty(attr.getText()))
			this.limit = Integer.parseInt(attr.getText());
		attr = root.attribute("distinct");
		if (attr != null && !ValueHelper.isEmpty(attr.getText()))
			this.distinct = Boolean.valueOf(attr.getText());

		this.parameters.clear();

		@SuppressWarnings("unchecked")
		List<Node> nodes = root.selectNodes("//parameter");
		for (Node node : nodes) {
			Element pEle = (Element) node;
			Parameter p = new Parameter();
			p.setName(pEle.attributeValue("name"));
			p.setExpr(pEle.selectSingleNode("expr").getText());
			this.parameters.add(p);
		}
		if (advanceClass != null) {
			this.advance = (XmlConfig) advanceClass.newInstance();
			Element advanceEle = (Element) root.selectSingleNode("advance");
			if (advanceEle != null) {
				if (advanceEle.elements().size() > 0) {
					this.advance.parseXml(((Element) advanceEle.elements().get(
							0)).asXML());
				}
			}
		}

	}

	@Override
	public void parseXml(String xml) throws Exception {
		this.parseXml(xml, null);
	}

	public boolean isQuery() {
		return isQuery;
	}

	public void setQuery(boolean isQuery) {
		this.isQuery = isQuery;
	}

	public String getDataModel() {
		return dataModel;
	}

	public void setDataModel(String dataModel) {
		this.dataModel = dataModel;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getFieldAlias() {
		return fieldAlias;
	}

	public void setFieldAlias(String fieldAlias) {
		this.fieldAlias = fieldAlias;
	}

	public String getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String fieldNames) {
		this.fieldNames = fieldNames;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public XmlConfig getAdvance() {
		return advance;
	}

	public void setAdvance(XmlConfig advance) {
		this.advance = advance;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

}
