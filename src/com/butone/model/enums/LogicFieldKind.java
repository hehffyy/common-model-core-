package com.butone.model.enums;

/**
 * 逻辑字段类型
 * 
 * @author Administrator
 * 
 */
public enum LogicFieldKind {
	lfkTemp, lfkOrg, lfkSign, lfkUnit, lfkUnitControl, lfkAttachment, lfkCode, lfkCoSign, lfkGeometry;

	public boolean isTable() {
		return this == lfkCoSign || this == lfkGeometry;
	}

	public boolean isUseAsSlave() {
		return false;
	}

	public String getDispName() {
		switch (this) {
		case lfkTemp:
			return "临时字段";
		case lfkOrg:
			return "机构字段";
		case lfkSign:
			return "签名字段";
		case lfkUnit:
			return "单位字段";
		case lfkUnitControl:
			return "单位控制字段";
		case lfkAttachment:
			return "附件字段";
		case lfkCode:
			return "通用编码字段";
		case lfkCoSign:
			return "会签表";
		case lfkGeometry:
			return "GIS图形表";
		default:
			return this.toString();
		}
	}
}
