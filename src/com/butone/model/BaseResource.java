package com.butone.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 资源基类，描述所有资源类（X5资源文件）的基础属性
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BaseResource extends Version{
	private static final long serialVersionUID = -8085456337060400496L;
	private Date lastModifyTime;
	private Date syncTime;

	/**
	 * 最后修改时间
	 * 
	 * @hibernate.property column="FLastModifyTime" type="java.util.Date"
	 */
	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	/**
	 * 资源同步时间
	 * 
	 * @hibernate.property column="FSyncTime" type="java.util.Date"
	 */
	public Date getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

}
