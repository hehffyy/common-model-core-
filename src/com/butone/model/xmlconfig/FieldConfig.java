package com.butone.model.xmlconfig;

import org.dom4j.Document;

/**
 * 字段配置,包含树形定义及互拟关系
 */
public class FieldConfig implements XmlConfig {
	/**
	 * 编码树定义
	 */
	private TreeDef treeDef;

	/**
	 * 主从翻转定义
	 */
	private String inverseOf;

	public String getInverseOf() {
		return inverseOf;
	}

	public void setInverseOf(String inverseOf) {
		this.inverseOf = inverseOf;
	}

	public TreeDef getTreeDef() {
		return treeDef;
	}

	public void setTreeDef(TreeDef treeDef) {
		this.treeDef = treeDef;
	}

	@Override
	public Document getDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	public String asXml() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean compatible(Integer marjorVersion, Integer minorVer) {
		// TODO Auto-generated method stub
		return false;
	}

	public Integer getMajorVersionNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getMinorVersionNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public void parseXml(String xml) throws Exception {
		// TODO Auto-generated method stub

	}

	public static class TreeDef {
		private String pathFieldName;
		private String pathSeperator;

		public String getPathFieldName() {
			return pathFieldName;
		}

		public void setPathFieldName(String pathFieldName) {
			this.pathFieldName = pathFieldName;
		}

		public String getPathSeperator() {
			return pathSeperator;
		}

		public void setPathSeperator(String pathSeperator) {
			this.pathSeperator = pathSeperator;
		}

	}
}
