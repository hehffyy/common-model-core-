package com.butone.vss;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class HealthReport implements Serializable {
	// 修改的模型名
	String modifiedModelName;
	// 修改的模型主键
	String modifiedModelKey;
	// 什么操作
	String operation;
	// 受影响的模型名
	String affectedModelName;
	// 受影响的模型名
	String affectedModelKey;
	// 影响描述
	String affectedDesc;
	// 影响关联度
	int affectedScore;
	// 建议
	String suggest;

	// 日期
	Date createTime = new Date();

	/**
	 * @hibernate.property column="FModifiedModelName" length="50"
	 * @return
	 */
	public String getModifiedModelName() {
		return modifiedModelName;
	}

	public void setModifiedModelName(String modifiedModelName) {
		this.modifiedModelName = modifiedModelName;
	}

	/**
	 * @hibernate.property column="FModifiedModelKey" length="50"
	 * @return
	 */
	public String getModifiedModelKey() {
		return modifiedModelKey;
	}

	public void setModifiedModelKey(String modifiedModelKey) {
		this.modifiedModelKey = modifiedModelKey;
	}

	/**
	 * @hibernate.property column="FOperation" length="20"
	 * @return
	 */
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @hibernate.property column="FAffectedModelName" length="50"
	 * @return
	 */
	public String getAffectedModelName() {
		return affectedModelName;
	}

	public void setAffectedModelName(String affectedModelName) {
		this.affectedModelName = affectedModelName;
	}

	/**
	 * @hibernate.property column="FAffectedModelKey" length="50"
	 * @return
	 */
	public String getAffectedModelKey() {
		return affectedModelKey;
	}

	public void setAffectedModelKey(String affectedModelKey) {
		this.affectedModelKey = affectedModelKey;
	}

	/**
	 * @hibernate.property column="FAffectedDesc" length="50"
	 * @return
	 */
	public String getAffectedDesc() {
		return affectedDesc;
	}

	public void setAffectedDesc(String affectedDesc) {
		this.affectedDesc = affectedDesc;
	}

	/**
	 * @hibernate.property column="FAffectedScore"
	 * @return
	 */
	public int getAffectedScore() {
		return affectedScore;
	}

	public void setAffectedScore(int affectedScore) {
		this.affectedScore = affectedScore;
	}

	/**
	 * @hibernate.property column="FSuggest" length="50"
	 * @return
	 */
	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	/**
	 * @hibernate.property column="FCreateTime" type="java.util.Date"
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
