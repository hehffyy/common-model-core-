/**
 *
 * Title: <br>
 * Description: <br>
 * Date:  <br>
 * Copyright (c) 2014 Butone<br>
 *
 * @author heqiang
 */
package com.butone.model;

/*
 *  西安交大博通资讯股份有限公司
 */
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.butone.model.Version;

/**
 * Codekeydef Base Java Bean, base class for the.oa.model, mapped directly to
 * database table
 * 
 * Avoid changing this file if not necessary, will be overwritten.
 * 
 * 通用编码定义
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Codekeydef extends Version implements Serializable, Sort {
	private static final long serialVersionUID = 2005266591310283929L;

	// //通用编码编号
	// protected String fguid;
	// 编码名称
	protected String fname;
	// 编码格式
	protected byte[] fformat;
	// 编码描述
	protected String fdescription;
	// 编码属性
	protected Integer fproperty;
	// 生成方式
	protected String fcreatekind;
	// 显示次序
	protected Integer forder;
	// 上级编号
	protected String fparentid;

	/**
	 * Default Empty Constructor for class Codekeydef
	 */
	public Codekeydef() {
		super();
	}

	/**
	 * 编码名称 @return String
	 * 
	 * @hibernate.property column="fname" type="java.lang.String" length="200"
	 *                     not-null="true" unique="false"
	 */
	public String getFname() {
		return this.fname;
	}

	/**
	 * Set the fname 编码名称 * @spring.validator type="required"
	 * 
	 */
	public void setFname(String aValue) {
		this.fname = aValue;
	}

	/**
	 * 编码描述 @return String
	 * 
	 * @hibernate.property column="fdescription" type="java.lang.String"
	 *                     length="255" not-null="false" unique="false"
	 */
	public String getFdescription() {
		return this.fdescription;
	}

	/**
	 * Set the fdescription 编码描述 *
	 */
	public void setFdescription(String aValue) {
		this.fdescription = aValue;
	}

	/**
	 * 编码属性 @return Integer
	 * 
	 * @hibernate.property column="fproperty" type="java.lang.Integer"
	 *                     length="10" not-null="false" unique="false"
	 */
	public Integer getFproperty() {
		return this.fproperty;
	}

	/**
	 * Set the fproperty 编码属性 *
	 */
	public void setFproperty(Integer aValue) {
		this.fproperty = aValue;
	}

	/**
	 * 生成方式 @return Integer
	 * 
	 * @hibernate.property column="fcreatekind" type="java.lang.Integer"
	 *                     length="10" not-null="true" unique="false"
	 */
	public String getFcreatekind() {
		return this.fcreatekind;
	}

	/**
	 * Set the fcreatekind 生成方式 * @spring.validator type="required"
	 * 
	 */
	public void setFcreatekind(String aValue) {
		this.fcreatekind = aValue;
	}

	/**
	 * 显示次序 @return Integer
	 * 
	 * @hibernate.property column="forder" type="java.lang.Integer" length="10"
	 *                     not-null="false" unique="false"
	 */
	public Integer getForder() {
		return this.forder;
	}

	/**
	 * Set the forder 显示次序 *
	 */
	public void setForder(Integer aValue) {
		this.forder = aValue;
	}

	/**
	 * 上级编号 @return String
	 * 
	 * @hibernate.property column="fparentid" type="java.lang.String"
	 *                     length="32" not-null="false" unique="false"
	 */
	public String getFparentid() {
		return this.fparentid;
	}

	/**
	 * Set the fparentid 上级编号 *
	 */
	public void setFparentid(String aValue) {
		this.fparentid = aValue;
	}

	/**
	 * 格式 @return String
	 * 
	 * @hibernate.property column="Fformat"
	 *                     type="org.springframework.orm.hibernate3.support.BlobByteArrayType"
	 */

	public byte[] getFformat() {
		return fformat;
	}

	public void setFformat(byte[] fformat) {
		this.fformat = fformat;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Codekeydef)) {
			return false;
		}
		Codekeydef rhs = (Codekeydef) object;
		return new EqualsBuilder()
				// .append(this.fguid, rhs.fguid)
				.append(this.fname, rhs.fname)
				// .append(this.fformat, rhs.fformat)
				.append(this.fdescription, rhs.fdescription).append(
						this.fproperty, rhs.fproperty).append(this.fcreatekind,
						rhs.fcreatekind).append(this.forder, rhs.forder)
				.append(this.fparentid, rhs.fparentid).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
		// .append(this.fguid)
				.append(this.fname)
				// .append(this.fformat)
				.append(this.fdescription).append(this.fproperty).append(
						this.fcreatekind).append(this.forder).append(
						this.fparentid).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				// .append("fguid", this.fguid)
				.append("fname", this.fname)
				// .append("fformat", this.fformat)
				.append("fdescription", this.fdescription).append("fproperty",
						this.fproperty).append("fcreatekind", this.fcreatekind)
				.append("forder", this.forder).append("fparentid",
						this.fparentid).toString();
	}

	@Override
	public Object getOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrder(Object order) {
		// TODO Auto-generated method stub

	}

}
