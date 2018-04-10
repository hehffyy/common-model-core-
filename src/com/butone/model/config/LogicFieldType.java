package com.butone.model.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 逻辑字段类型，用于描述逻辑字段的物理结构 。一个逻辑字段由一个或多个字段组成
 * 
 * 
 */
@XmlRootElement(name = "LogicField")
@XmlAccessorType(XmlAccessType.FIELD)
public class LogicFieldType {
	@XmlAttribute(required = true)
	private String name;
	@XmlAttribute
	private String dispName;
	@XmlAttribute
	private String dataType;
	@XmlAttribute
	private Integer length;
	@XmlAttribute
	private Integer scale;
	@XmlAttribute
	private String forignKeys;
	@XmlAttribute
	private String suffix;

	@XmlElement(name = "Configure")
	private String configure;

	@XmlElement(name = "Desc")
	private String desc;

	@XmlElement
	private String autoFill;

	@XmlElement
	private String orderBy;
	/**
	 * 编辑器
	 */
	@XmlElement(name = "Editor")
	private String editor;
	@XmlElementWrapper(name = "SubFields", nillable = true)
	@XmlElement(name = "Field")
	private List<LogicSubFieldDefine> subFields;

	@XmlElement(name = "SlaveTable")
	private List<LogicFieldType> slaveTables;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfigure() {
		return configure;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	/**
	 * 显示名称
	 * 
	 * @return
	 */
	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/**
	 * 数据类型
	 * 
	 * @return
	 */
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * 长度
	 * 
	 * @return
	 */
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * 精度
	 * 
	 * @return
	 */
	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getForignKeys() {
		return forignKeys;
	}

	public void setForignKeys(String forignKeys) {
		this.forignKeys = forignKeys;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getAutoFill() {
		return autoFill;
	}

	public void setAutoFill(String autoFill) {
		this.autoFill = autoFill;
	}

	/**
	 * 描述信息
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 编辑器
	 * 
	 * @return
	 */
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public List<LogicSubFieldDefine> getSubFields() {
		return subFields;
	}

	public void setSubFields(List<LogicSubFieldDefine> subFields) {
		this.subFields = subFields;
	}

	public List<LogicFieldType> getSlaveTables() {
		return slaveTables;
	}

	public void setSlaveTables(List<LogicFieldType> slaveTables) {
		this.slaveTables = slaveTables;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class LogicSubFieldDefine {
		@XmlAttribute
		private String label;
		@XmlAttribute
		private String suffix;
		@XmlAttribute
		private String dataType;
		@XmlAttribute
		private Integer length;
		@XmlAttribute
		private Integer scale;
		@XmlElement
		private String desc;
		@XmlElement
		private String autoFill;

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String dispName) {
			this.label = dispName;
		}

		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			this.dataType = dataType;
		}

		public Integer getLength() {
			return length;
		}

		public void setLength(Integer length) {
			this.length = length;
		}

		public Integer getScale() {
			return scale;
		}

		public void setScale(Integer scale) {
			this.scale = scale;
		}

		public String getAutoFill() {
			return autoFill;
		}

		public void setAutoFill(String autoFill) {
			this.autoFill = autoFill;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

}
