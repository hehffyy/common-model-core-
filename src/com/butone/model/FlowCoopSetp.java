package com.butone.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 流程协同步骤
 * 
 * @hibernate.class table="M_FlowCoopSetp"
 * @author Administrator
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FlowCoopSetp extends Version implements Serializable {

	private static final long serialVersionUID = 3324987481658463666L;

	private String name;
	private String dispName;
	/**
	 * 发起流程
	 */
	private String fromFlow;
	/**
	 * 发起环节
	 */
	private String fromAct;
	/**
	 * 发起发等待环节
	 */
	private String waitAct;
	/**
	 * 接收流程
	 */
	private String toFlow;
	/**
	 * 接收环节
	 */
	private String toAct;
	/**
	 * 交换的信息
	 */
	private String infoPacket;
	/**
	 * 是否允许重复，默认为fasle。即只发起一次交换
	 */
	private Boolean allowRepeat;
	/**
	 * 是否同步交换，默认为false。
	 */
	private Boolean sync;

	/**
	 * 名称英文
	 * 
	 * @hibernate.property
	 * @hibernate.column name="FName" length="100" not-null="true"
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
	 * @hibernate.property column="FDispName" length="60" not-null="true"
	 */
	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/**
	 * 发起流程
	 * 
	 * @hibernate.property column="FfromFlow" length="32" not-null="true"
	 */
	public String getFromFlow() {
		return fromFlow;
	}

	public void setFromFlow(String fromFlow) {
		this.fromFlow = fromFlow;
	}

	/**
	 * 发起环节
	 * 
	 * @hibernate.property column="FfromAct" length="32" not-null="true"
	 */
	public String getFromAct() {
		return fromAct;
	}

	public void setFromAct(String fromAct) {
		this.fromAct = fromAct;
	}

	/**
	 * 等待环节
	 * 
	 * @hibernate.property column="FWaitAct" length="32"
	 */
	public String getWaitAct() {
		return waitAct;
	}

	public void setWaitAct(String waitAct) {
		this.waitAct = waitAct;
	}

	/**
	 * 接收流程
	 * 
	 * @hibernate.property column="FtoFlow" length="32" not-null="true"
	 */
	public String getToFlow() {
		return toFlow;
	}

	public void setToFlow(String toFlow) {
		this.toFlow = toFlow;
	}

	/**
	 * 接收环节
	 * 
	 * @hibernate.property column="FtoAct" length="32" not-null="true"
	 */
	public String getToAct() {
		return toAct;
	}

	public void setToAct(String toAct) {
		this.toAct = toAct;
	}

	/**
	 * 允许重复
	 * 
	 * @hibernate.property column="FallowRepeat"
	 */
	public Boolean getAllowRepeat() {
		return allowRepeat;
	}

	public void setAllowRepeat(Boolean allowRepeat) {
		this.allowRepeat = allowRepeat;
	}

	/**
	 * 是否同步过程
	 * 
	 * @hibernate.property column="fsync"
	 */
	public Boolean getSync() {
		return sync;
	}

	public void setSync(Boolean sync) {
		this.sync = sync;
	}

	/**
	 * 交换信息
	 * 
	 * @hibernate.property column="FInfoPacket"
	 *                     type="org.springframework.orm.hibernate3.support.BlobStringType"
	 */
	public String getInfoPacket() {
		return infoPacket;
	}

	public void setInfoPacket(String infoPacket) {
		this.infoPacket = infoPacket;
	}

	
}
