package com.butone.model.validation;

import java.io.Serializable;

import com.butone.model.Version;

/**
 * 受牵连的表
 * @author hehffyy
 *
 */
public class InvolveLog extends Version implements Serializable {

	private static final long serialVersionUID = -8103496644309084647L;
	//变动表guid
	private String modifyGuid;
	//表名
	private String name;
	//模块名
	private String displayName;
	//字段名
	private String fieldName;
	//行数据Guid
	private String modelGuid;
	//操作类型
	private String operate;

	
	
	public String getModifyGuid() {
		return modifyGuid;
	}
	public void setModifyGuid(String modifyGuid) {
		this.modifyGuid = modifyGuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getModelGuid() {
		return modelGuid;
	}
	public void setModelGuid(String modelGuid) {
		this.modelGuid = modelGuid;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	
	
	

	
	
}
