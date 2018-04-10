package com.butone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.model.annotation.AssembleTarget;
import com.butone.model.annotation.SubModel;
import com.butone.xml.Base64XmlAdapter;

/**
 * 业务表单
 * 
 * @hibernate.class table="M_Form"
 */
@XmlRootElement(name = "Form")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = { "bizGuid" }, target = Biz.class, targetKeys = { "guid" }, orderProperty = "order")
public class Form extends BaseResource implements Serializable, Sort {
	private static final long serialVersionUID = -3963017701463278901L;
	/**
	 * 所属业务
	 */
	private String bizGuid;
	/**
	 * 所属导航栏
	 */
	private String navGuid;
	private String name;
	private String displayName;
	/**
	 * 文档类型：cell、form
	 */
	private String kind;
	/**
	 * html
	 */
	private String content;
	/**
	 * model结构
	 */
	@XmlJavaTypeAdapter(Base64XmlAdapter.class)
	private String modelStruct;
	/**
	 * 是否代码片段，它只是其他文档的组成部分。代码片段不允许添加到导航栏、不允许配置到环节。
	 */
	private Boolean fragment;

	/**
	 * url 用于自定义.w文件
	 */
	private String url;

	private List<FormLogicPlugin> plugins = new ArrayList<FormLogicPlugin>();

	/**
	 * 表单资源文件
	 */
	private List<BizResourceFile> resourceFiles = new ArrayList<BizResourceFile>();
	/**
	 * 显示顺序
	 */
	private Integer dispOrder;

	/**
	 * @hibernate.property
	 * @hibernate.column name="FBizGuid" length="32" not-null="true"
	 *                   unique-key="M_Form_BizName"
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column name="FnavGuid" length="32" index="M_Form_NavGuid"
	 */
	public String getNavGuid() {
		return navGuid;
	}

	public void setNavGuid(String navGuid) {
		this.navGuid = navGuid;
	}

	/**
	 * 文档名称，符合文件命名规范，同一业务下文档名称唯一
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="32" not-null="true"
	 *                   unique-key="M_Form_BizName"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 显示名称
	 * 
	 * @hibernate.property column="FDispName" length="50"
	 */
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 文档类型：cell、form
	 * 
	 * @hibernate.property column="FKind" length="10"
	 * @return
	 */
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * 文档设计内容
	 * 
	 * @hibernate.property column="FContent"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 文档设计模型
	 * 
	 * @hibernate.property column="FModelStruct"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getModelStruct() {
		return modelStruct;
	}

	public void setModelStruct(String modelStruct) {
		this.modelStruct = modelStruct;
	}

	/**
	 * 自定义.w
	 * 
	 * @hibernate.property column="FUrl" length="500"
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @hibernate.property column="FisFragment"
	 */
	public Boolean getFragment() {
		return fragment;
	}

	public void setFragment(Boolean fragment) {
		this.fragment = fragment;
	}

	@AssembleTarget(FormLogicPlugin.class)
	public List<FormLogicPlugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<FormLogicPlugin> plugins) {
		this.plugins = plugins;
	}

	@AssembleTarget(BizResourceFile.class)
	public List<BizResourceFile> getResourceFiles() {
		return resourceFiles;
	}

	public void setResourceFiles(List<BizResourceFile> resourceFiles) {
		this.resourceFiles = resourceFiles;
	}

	/**
	 * 显示顺序
	 * 
	 * @hibernate.property column="FDispOrder" type="java.lang.Integer"
	 */
	public Object getOrder() {
		return dispOrder;
	}

	public void setOrder(Object dispOrder) {
		if (dispOrder != null)
			this.dispOrder = new Integer(dispOrder.toString());
		else
			this.dispOrder = null;
	}

}
