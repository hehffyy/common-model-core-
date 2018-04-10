package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import com.butone.model.annotation.SubModel;
import com.butone.model.annotation.SubModels;

/**
 * 业务表单
 */
@XmlAccessorType(XmlAccessType.FIELD)
@SubModels(subModels = {
		@SubModel(foreignKeys = "bizGuid", target = Biz.class, targetKeys = "guid"),
		@SubModel(foreignKeys = "bizGuid", target = Form.class, targetKeys = "guid") })
public class BizResourceFile extends Version implements Serializable {

	private static final long serialVersionUID = -7392443291405005409L;
	/**
	 * 所属业务
	 */
	@XmlAttribute
	private String bizGuid;
	@XmlAttribute
	private String fileKind;

	@XmlAttribute
	private String fileName;
	@XmlAttribute
	private String dispName;
	@XmlAttribute
	private Integer fileSize;
	@XmlAttribute
	private String md5;

	@XmlTransient
	private byte[] content;

	public String getBizGuid() {
		return bizGuid;
	}

	public void setBizGuid(String bizGuid) {
		this.bizGuid = bizGuid;
	}

	/**
	 * 文件名(英文)，同一业务下文件名唯一
	 * 
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * 文件类型
	 * 
	 */
	public String getFileKind() {
		return fileKind;
	}

	public void setFileKind(String fileKind) {
		this.fileKind = fileKind;
	}

	/**
	 * 文件显示名
	 * 
	 */
	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/**
	 * 文件内容
	 */
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}
