package com.butone.vss;

import java.io.Serializable;
import java.util.Date;

/**
 * VSS文件系统
 * 
 * @hibernate.class table="M_VssFileSystem"
 * 
 */
public class VssFileSystem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String guid;
	private String modelGuid;
	private String modelKind;
	private Date createTime;
	private String content;

	/**
	 * 主键
	 * 
	 * @hibernate.id generator-class="assigned" column="FGUID" length="32"
	 */
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * 模型GUID
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FModelGuid" length="32"
	 *                   index="M_VssFileSystem_Model"
	 */
	public String getModelGuid() {
		return modelGuid;
	}

	public void setModelGuid(String modelGuid) {
		this.modelGuid = modelGuid;
	}

	/**
	 * 模型类型
	 * 
	 * @hibernate.property column="FModelKind" length="20"
	 */
	public String getModelKind() {
		return modelKind;
	}

	public void setModelKind(String modelKind) {
		this.modelKind = modelKind;
	}

	/**
	 * 创建时间
	 * 
	 * @hibernate.property column="FCreateTime"
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 模型XML对象
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

}
