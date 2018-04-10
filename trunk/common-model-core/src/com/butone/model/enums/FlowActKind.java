package com.butone.model.enums;

public enum FlowActKind {
	fakBusiness, fakStatic, fakQuery;

	public String getDisplayName() {
		switch (this) {
		case fakStatic:
			return "静态功能";
		case fakQuery:
			return "查询功能";
		default:
			return "流程功能";
		}
	}
}
