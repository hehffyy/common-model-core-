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

public class LookupConfig implements XmlConfig {
	/**
	 * 枚举类型
	 */
	public static String LookupKind_ENUM = "enum";
	/**
	 * 字典类型
	 */
	public static String LookupKind_DICT = "dict";

	/**
	 * 自定义查询
	 */
	public static String LookupKind_QUERY = "query";

	/**
	 * 枚举类型
	 */
	public static String LookupShowKind_GRID = "grid";
	/**
	 * 字典类型
	 */
	public static String LookupShowKind_TREE = "tree";

	/**
	 * 查找类型
	 */
	private String lookupKind;

	/**
	 * 简单枚举值
	 */
	private List<String> enumValues;

	/**
	 * 字典设置
	 */
	private LookupDict dict;

	/**
	 * 查询设置
	 */
	private LookupQuery query;

	private String showKind;
	/**
	 * 网格设置
	 */
	private GridSetting gridSetting;

	@Override
	public Document getDocument() {
		Document document = DocumentHelper.createDocument();
		Element d = document.addElement("LookupDef");
		d.addAttribute("majorVer", getMajorVersionNumber().toString());
		d.addAttribute("minorVer", getMinorVersionNumber().toString());
		d.addAttribute("kind", lookupKind);
		d.addAttribute("showKind", showKind);
		if (LookupKind_ENUM.equals(lookupKind)) {
			Element node = d.addElement("EnumValues");
			for (String value : enumValues) {
				if (!ValueHelper.isEmpty(value)) {
					Element subNode = node.addElement("Value");
					subNode.addCDATA(value);
				}
			}
		} else if (LookupKind_DICT.equals(lookupKind)) {
			Element node = d.addElement("Dict");
			node.addAttribute("type", dict.getType());
			node.addAttribute("name", dict.getName());
			node.addAttribute("isShow", String.valueOf(dict.getIsShow()));
			node.addAttribute("manualInput",
					String.valueOf(dict.isManualInput()));
			node.addAttribute("dictNameField",
					String.valueOf(dict.getDictNameField()));

		} else if (LookupKind_QUERY.equals(lookupKind)) {
			Element node = d.addElement("Query");
			node.addAttribute("guid", query.getTableGuid());
			node.addAttribute("fieldAlias", query.getFieldAlias());
			node.addAttribute("fieldNames", query.getFieldNames());
			node.addAttribute("distinct", Boolean.valueOf(query.isDistinct())
					.toString());

			Element subNode = node.addElement("select");
			subNode.addCDATA(query.getSelect());

			subNode = node.addElement("from");
			subNode.addCDATA(query.getFrom());

			subNode = node.addElement("condition");
			subNode.addCDATA(query.getCondition());

			subNode = node.addElement("orderBy");
			subNode.addCDATA(query.getOrderBy());

			Element gridNode = d.addElement("GRID");
			GridSetting gridSetting = getGridSetting();

			gridNode.addAttribute("onlySelect",
					String.valueOf(gridSetting.onlySelect));
			gridNode.addAttribute("multiSelect",
					String.valueOf(gridSetting.multiSelect));
			gridNode.addAttribute("delayCreateGrid",
					String.valueOf(gridSetting.delayCreateGrid));
			gridNode.addAttribute("separator", gridSetting.separator);
			gridNode.addAttribute("dropDownHeight",
					String.valueOf(gridSetting.dropDownHeight));
			gridNode.addAttribute("dropDownWidht",
					String.valueOf(gridSetting.dropDownWidht));
			gridNode.addAttribute("headerCaptions", gridSetting.headerCaptions);
			gridNode.addAttribute("lookupField", gridSetting.lookupField);
			gridNode.addAttribute("lookupField1", gridSetting.lookupField1);
			gridNode.addAttribute("lookupField2", gridSetting.lookupField2);
			gridNode.addAttribute("dataField1", gridSetting.dataField1);
			gridNode.addAttribute("dataField2", gridSetting.dataField2);
			gridNode.addAttribute("showFields", gridSetting.showFields);

			if (LookupConfig.LookupShowKind_TREE.equals(showKind)) {
				Element treeNode = d.addElement("TREE");

				treeNode.addAttribute("rootFilter", gridSetting.rootFilter);
				treeNode.addAttribute("virtualRoot", gridSetting.virtualRoot);
				treeNode.addAttribute("onlyLeaf",
						String.valueOf(gridSetting.onlyLeaf));
				treeNode.addAttribute("cascade",
						String.valueOf(gridSetting.cascade));
				treeNode.addAttribute("nodeLevelField",
						gridSetting.nodeLevelField);
				treeNode.addAttribute("nodeKindField",
						gridSetting.nodeKindField);
				treeNode.addAttribute("parentField", gridSetting.parentField);
			}

		}
		return document;
	}

	@Override
	public String asXml() {
		if (LookupKind_ENUM.equals(lookupKind)) {
			if (enumValues == null) {
				return "";
			}
		} else if (LookupKind_DICT.equals(lookupKind)) {
			if (dict.getType().isEmpty() && dict.getName().isEmpty()) {
				return "";
			}
		} else if (LookupKind_QUERY.equals(lookupKind)) {
			if (query.getTableGuid() == null || query.getTableGuid().isEmpty()) {
				return "";
			}
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

	public boolean compatible(Integer marjorVersion, Integer minorVer) {
		return marjorVersion.equals(1) && minorVer.equals(0);
	}

	public Integer getMajorVersionNumber() {
		return 1;
	}

	public Integer getMinorVersionNumber() {
		return 0;
	}

	public String getLookupKind() {
		return lookupKind;
	}

	public void setLookupKind(String lookupKind) {
		this.lookupKind = lookupKind;
	}

	public List<String> getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(List<String> enumValues) {
		this.enumValues = enumValues;
	}

	public LookupDict getDict() {
		return dict;
	}

	public void setDict(LookupDict dict) {
		this.dict = dict;
	}

	public LookupQuery getQuery() {
		return query;
	}

	public void setQuery(LookupQuery query) {
		this.query = query;
	}

	public String getShowKind() {
		return showKind;
	}

	public void setShowKind(String showKind) {
		this.showKind = showKind;
	}

	public GridSetting getGridSetting() {
		return gridSetting;
	}

	public void setGridSetting(GridSetting gridSetting) {
		this.gridSetting = gridSetting;
	}

	@SuppressWarnings("unchecked")
	public void parseXml(String xml) throws Exception {

		this.lookupKind = null;
		if (ValueHelper.isEmpty(xml)) {
			return;
		}
		SAXReader reader = new SAXReader();
		reader.setEncoding("gbk");
		ByteArrayInputStream in;

		in = new ByteArrayInputStream(xml.getBytes("gbk"));
		Document doc = reader.read(in);
		Element e = (Element) doc.selectSingleNode("/LookupDef");

		if (!compatible(Integer.parseInt(e.attribute("majorVer").getValue()),
				Integer.parseInt(e.attribute("minorVer").getValue()))) {
			throw new Exception("XML版本不兼容");
		}

		this.lookupKind = e.attributeValue("kind");
		if (LookupKind_ENUM.equals(lookupKind)) {
			List<Element> subNodes = doc
					.selectNodes("/LookupDef/EnumValues/Value");
			this.enumValues = new ArrayList<String>();
			for (Element node : subNodes) {
				this.enumValues.add(node.getText());
			}
		} else if (LookupKind_DICT.equals(lookupKind)) {
			Element node = (Element) doc.selectSingleNode("/LookupDef/Dict");
			this.dict = new LookupDict();
			this.dict.type = node.attributeValue("type");
			this.dict.name = node.attributeValue("name");
			this.dict.dictNameField = node.attributeValue("dictNameField");
			this.dict.isShow = Boolean.valueOf(node.attributeValue("isShow"));
			this.dict.manualInput = Boolean.valueOf(node
					.attributeValue("manualInput"));
		} else if (LookupKind_QUERY.equals(lookupKind)) {
			Element node = (Element) doc.selectSingleNode("/LookupDef/Query");
			this.query = new LookupQuery();
			this.query.tableGuid = node.attributeValue("guid");
			this.query.fieldAlias = node.attributeValue("fieldAlias");
			this.query.fieldNames = node.attributeValue("fieldNames");
			this.query.distinct = ValueHelper.isTrue(node
					.attributeValue("distinct"));

			Element subNode = (Element) node.selectSingleNode("select");
			this.query.select = subNode.getText();
			subNode = (Element) node.selectSingleNode("from");
			this.query.from = subNode.getText();
			subNode = (Element) node.selectSingleNode("condition");
			this.query.condition = subNode.getText();
			subNode = (Element) node.selectSingleNode("orderBy");
			this.query.orderBy = subNode.getText();

			this.showKind = e.attributeValue("showKind");

			Element gridnode = (Element) doc
					.selectSingleNode("/LookupDef/GRID");
			this.gridSetting = new GridSetting();
			this.gridSetting.onlySelect = Boolean.valueOf(gridnode
					.attributeValue("onlySelect"));
			this.gridSetting.multiSelect = Boolean.valueOf(gridnode
					.attributeValue("multiSelect"));
			this.gridSetting.delayCreateGrid = Boolean.valueOf(gridnode
					.attributeValue("delayCreateGrid"));
			this.gridSetting.separator = gridnode.attributeValue("separator");
			this.gridSetting.dropDownHeight = Integer.valueOf(gridnode
					.attributeValue("dropDownHeight"));
			this.gridSetting.dropDownWidht = Integer.valueOf(gridnode
					.attributeValue("dropDownWidht"));
			this.gridSetting.headerCaptions = gridnode
					.attributeValue("headerCaptions");
			this.gridSetting.lookupField = gridnode
					.attributeValue("lookupField");
			this.gridSetting.lookupField1 = gridnode
					.attributeValue("lookupField1");
			this.gridSetting.lookupField2 = gridnode
					.attributeValue("lookupField2");
			this.gridSetting.dataField1 = gridnode.attributeValue("dataField1");
			this.gridSetting.dataField2 = gridnode.attributeValue("dataField2");
			this.gridSetting.showFields = gridnode.attributeValue("showFields");

			if (LookupConfig.LookupShowKind_TREE.equals(showKind)) {
				Element treenode = (Element) doc
						.selectSingleNode("/LookupDef/TREE");
				this.gridSetting.rootFilter = treenode
						.attributeValue("rootFilter");
				this.gridSetting.virtualRoot = treenode
						.attributeValue("virtualRoot");
				this.gridSetting.onlyLeaf = Boolean.valueOf(treenode
						.attributeValue("onlyLeaf"));
				this.gridSetting.cascade = Boolean.valueOf(treenode
						.attributeValue("cascade"));
				this.gridSetting.nodeLevelField = treenode
						.attributeValue("nodeLevelField");
				this.gridSetting.nodeKindField = treenode
						.attributeValue("nodeKindField");
				this.gridSetting.parentField = treenode
						.attributeValue("parentField");

			}

		}
	}

	public static class LookupDict {
		private String type;
		private String name;
		// false：只显示字典名称 , true ： 显示 编号 + 名称
		private Boolean isShow;
		private boolean manualInput = false;
		// 字典名称字段
		private String dictNameField;

		public String getDictNameField() {
			if (dictNameField == null)
				return "";
			return dictNameField;
		}

		public void setDictNameField(String dictNameField) {
			this.dictNameField = dictNameField;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Boolean getIsShow() {
			if (isShow == null)
				return false;
			return isShow;
		}

		public void setIsShow(Boolean isShow) {
			this.isShow = isShow;
		}

		public boolean isManualInput() {
			return manualInput;
		}

		public void setManualInput(boolean manualInput) {
			this.manualInput = manualInput;
		}

	}

	public static class LookupQuery {
		private String tableGuid;
		// 生成Query Action
		private String select;
		private String from;
		private String condition;
		private String orderBy;

		private String fieldAlias;
		private String fieldNames;

		private boolean distinct;

		public String getTableGuid() {
			return tableGuid;
		}

		public void setTableGuid(String tableGuid) {
			this.tableGuid = tableGuid;
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

		public boolean isDistinct() {
			return distinct;
		}

		public void setDistinct(boolean distinct) {
			this.distinct = distinct;
		}

	}

	public static class GridSetting {
		/**
		 * 只允许下拉
		 */
		public boolean onlySelect = false;
		/**
		 * 允许多选
		 */
		public boolean multiSelect = false;
		/**
		 * 多选分割符号
		 */
		public String separator;
		/**
		 * 延时创建下拉框
		 */
		public boolean delayCreateGrid = true;
		/**
		 * 下拉高度
		 */
		public Integer dropDownHeight = 0;
		/**
		 * 下拉宽度
		 */
		public Integer dropDownWidht = 0;
		/**
		 * 标头名称，如果为#text_filter，则显示此列过滤框
		 */
		public String headerCaptions;

		/**
		 * 查找字段(对应colunm-ref.value)
		 */
		public String lookupField;
		/**
		 * 查找字段1(对应colunm-ref.label，非空默认等于lookupField)
		 */
		public String lookupField1;
		/**
		 * 查找字段2(对应colunm-ref.ext)
		 */
		public String lookupField2;
		/**
		 * 数据字段1(对应label-ref)
		 */
		public String dataField1;
		/**
		 * 数据字段2(对应ext-ref)
		 */
		public String dataField2;

		/**
		 * 显示字段内容，字段之前以逗号隔开，例如： no,name,city
		 */
		public String showFields = "";

		// 一下是树形设置

		/**
		 * 父字段
		 */
		public String parentField;
		/**
		 * 节点类型字段
		 */
		public String nodeKindField;
		/**
		 * 节点级别字段
		 */
		public String nodeLevelField;
		/**
		 * 虚拟根名称
		 */
		public String virtualRoot;
		/**
		 * 根过滤条件
		 */
		public String rootFilter;
		/**
		 * 只能选择叶子
		 */
		public boolean onlyLeaf = false;
		/**
		 * 级联勾选
		 */
		public boolean cascade = true;

	}

}
