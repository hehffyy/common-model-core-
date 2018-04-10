package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 数据版本，Hibernate乐观锁机制，系统自动维护
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Version implements Serializable {
	private static final long serialVersionUID = 6487332954094142432L;

	@XmlAttribute
	private Integer verNum;
	@XmlAttribute
	private String guid;

	// 模块路径
	private String modelPath;

	private String searchID;
	private String searchName;

	/**
	 * 主键 Guid
	 * 
	 * @hibernate.id generator-class="assigned" column="fguid" length="32"
	 */
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getModelGuid() {
		return guid + "/" + this.getClass().getSimpleName();
	}

	/**
	 * @hibernate.version column="FVerNum"
	 */
	public Integer getVerNum() {
		return verNum;
	}

	public void setVerNum(Integer verNum) {
		this.verNum = verNum;
	}

	public String getSearchID() {
		return searchID;
	}

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

	@Override
	public int hashCode() {
		int ret = 17;
		ret += 37 * (this.getGuid() == null ? 0 : this.getGuid().hashCode());
		return ret;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Version))
			return false;
		Version castOther = (Version) other;

		return this.getGuid() != null
				&& this.getGuid().equals(castOther.getGuid());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
