package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.butone.model.annotation.SubModel;

/**
 * 表单逻辑插件
 * 
 * @hibernate.class table="M_FormLogicPlugin"
 */
@XmlRootElement(name = "FormLogicPlugin")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "formGuid" }, target = Form.class, targetKeys = { "guid" })
public class FormLogicPlugin extends BaseLogicPlugin implements Serializable {
	private static final long serialVersionUID = 1771270738442826697L;
	/**
	 * 所属表单
	 */
	private String formGuid;
	/**
	 * 对应的表单元素ID，生成资源时会挂接此element的onclick事件
	 */
	private String elementId;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FformGuid" index="M_FormLogicPlugin_Form"
	 *                   length="32"
	 */
	public String getFormGuid() {
		return formGuid;
	}

	public void setFormGuid(String formGuid) {
		this.formGuid = formGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FelementId"
	 */
	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

}
