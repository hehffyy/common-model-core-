package com.butone.model.validation;

public enum ActionType {
	create, update, select, delete ;
	
	public String getDisplayName() {
		switch (this) {
		case create:
			return "create";
		case update:
			return "update";
		case select:
			return "select";
		case delete:
			return "delete";
		default:
			throw new RuntimeException(this.toString() + "未申明显示名称");
		}
	}
}



