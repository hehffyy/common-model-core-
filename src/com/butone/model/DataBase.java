package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @hibernate.class table="M_DataBase"
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataBase extends Version implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String desc;
	/**
	 * 数据库类型ORACLE、DB2
	 */
	private String type;
	private String jdbcUrl;
	private String jdbcClass;
	private String userName;
	private String password;
	private String catalog;
	private String schema;
	private boolean system;
	private boolean defaultDatabase;

	/**
	 * 数据库名称
	 * 
	 * @hibernate.id generator-class="assigned" column="FName" length="20"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="FDesc" length="200"
	 */
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @hibernate.property column="FType" length="20"
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @hibernate.property column="FJdbcUrl" length="100"
	 */
	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	/**
	 * @hibernate.property column="FJdbcClass" length="100"
	 */
	public String getJdbcClass() {
		return jdbcClass;
	}

	public void setJdbcClass(String jdbcClass) {
		this.jdbcClass = jdbcClass;
	}

	/**
	 * @hibernate.property column="FUserName" length="30"
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @hibernate.property column="FPassword" length="30"
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @hibernate.property column="FCatalog" length="30"
	 */
	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	/**
	 * @hibernate.property column="FSchema" length="30"
	 */
	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * @hibernate.property column="FIsSystem"
	 */
	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

	/**
	 * @hibernate.property column="FIsDefaultDatabase"
	 */
	public boolean isDefaultDatabase() {
		return defaultDatabase;
	}

	public void setDefaultDatabase(boolean defaultDatabase) {
		this.defaultDatabase = defaultDatabase;
	}

}
