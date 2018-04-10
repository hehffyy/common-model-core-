package com.butone.model.validation;

public enum OperatType {
	unread, pass,passed ;
	
	public String getDisplayName() {
		switch (this) {
		case unread:
			return "未解决";
		case pass:
			return "已忽略";
		case passed:
			return "已解决";
		default:
			throw new RuntimeException(this.toString() + "未申明显示名称");
		}
	}
}
