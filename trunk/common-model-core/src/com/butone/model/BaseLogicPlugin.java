package com.butone.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.xml.Base64XmlAdapter;

/**
 * 逻辑插件基类，描述所有插件类对象具有的基础属性。<br>
 * 逻辑插件核心由2部分组成：逻辑实现类和逻辑执行参数。<br>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BaseLogicPlugin extends Version {
	private static final long serialVersionUID = -1546287608186054781L;
	@XmlAttribute
	private String className;
	@XmlElement
	@XmlJavaTypeAdapter(Base64XmlAdapter.class)
	private String parameter;
	@XmlAttribute
	private String dispName;
	@XmlElement
	private String desc;
	@XmlElement
	private String relBizDatas;

	/**
	 * 逻辑对应的实现类，X5系统中
	 * 
	 * @hibernate.property column="FLogicClass" length="100"
	 */
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 逻辑运行时配置参数
	 * 
	 * @hibernate.property column="FParameter"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * 逻辑显示名称
	 * 
	 * @hibernate.property column="FDispName" length="100" not-null="true"
	 */
	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/**
	 * 关联的业务表，插件执行时只加载这些表
	 * 
	 * @hibernate.property column="FRelBizDatas" length="1000"
	 */
	public String getRelBizDatas() {
		return relBizDatas;
	}

	public void setRelBizDatas(String relBizDatas) {
		this.relBizDatas = relBizDatas;
	}

	/**
	 * 描述
	 * 
	 * @hibernate.property column="FDesc" length="200"
	 */
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
