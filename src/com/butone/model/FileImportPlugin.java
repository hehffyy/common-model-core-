package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.butone.model.annotation.SubModel;
import com.butone.xml.Base64XmlAdapter;
import com.butone.xml.CDATAXmlAdapter;

/**
 * 文件导入插件
 * 
 * @hibernate.class table="M_FileImportPlugin"
 */
@XmlRootElement(name = "FileImportPlugin")
@XmlAccessorType(XmlAccessType.FIELD)
@SubModel(foreignKeys = "bizGuid", target = Biz.class, targetKeys = "guid")
public class FileImportPlugin extends Version implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6505858121863517803L;
	// 所属业务
	@XmlAttribute
	private String bizGuid;
	// 插件名称
	@XmlAttribute
	private String name;
	// 文件类型
	@XmlAttribute
	private String fileType;
	// 导入或导出类型
	@XmlAttribute
	private String importType;
	// 文件的数据模型
	@XmlElement
	@XmlJavaTypeAdapter(CDATAXmlAdapter.class)
	private String dataModels;

	// 映射逻辑
	@XmlElement
	@XmlJavaTypeAdapter(Base64XmlAdapter.class)
	private String mappingLogic;

	// 异步的
	@XmlAttribute
	private Boolean async;

	private String relBizDatas;

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
	 * 所属业务
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FBizGuid" length="32" not-null="true"
	 *                   unique-key="M_FileImpPlugin_NAME_BIZ"
	 * @return
	 */
	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * 所属业务
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="200" not-null="true"
	 *                   unique-key="M_FileImpPlugin_NAME_BIZ"
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 文件类型
	 * 
	 * @hibernate.property column="FFileType" length="50" not-null="true"
	 * @return
	 */
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 导入导出类型
	 * 
	 * @hibernate.property column="FImportType" length="50" not-null="true"
	 * @return
	 */
	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	/**
	 * 数据模型
	 * 
	 * @hibernate.property column="FDataModels"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 * @return
	 */
	public String getDataModels() {
		return dataModels;
	}

	public void setDataModels(String dataModels) {
		this.dataModels = dataModels;
	}

	/**
	 * 映射逻辑
	 * 
	 * @hibernate.property column="FMappingLogic"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 * @return
	 */
	public String getMappingLogic() {
		return mappingLogic;
	}

	public void setMappingLogic(String mappingLogic) {
		this.mappingLogic = mappingLogic;
	}

	/**
	 * 异步
	 * 
	 * @hibernate.property column="FAsync"
	 * @return
	 */
	public Boolean getAsync() {
		return async;
	}

	public void setAsync(Boolean async) {
		this.async = async;
	}

}
