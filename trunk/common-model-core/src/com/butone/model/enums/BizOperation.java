package com.butone.model.enums;

public enum BizOperation {
	// 作废
	abort,
	// 补正告知
	apprize,
	// 批量批转
	batchAdvance,
	// 删除办结
	delete,
	// 跳转
	special,
	// 特别程序
	specialProcedure,
	// 转报办结
	submit,
	// 挂起
	suspend,
	// 退文
	untread,
	// 转发
	transfer,
	// 回退
	back;

	public String getDisplayName() {
		switch (this) {
		case abort:
			return "作废";
		case apprize:
			return "补正告知";
		case batchAdvance:
			return "批量流转";
		case delete:
			return "删除办结";
		case special:
			return "跳转/特事特办";
		case specialProcedure:
			return "特别程序";
		case submit:
			return "转报办结";
		case suspend:
			return "挂起";
		case untread:
			return "退文";
		case transfer:
			return "转发";
		case back:
			return "回退";
		default:
			throw new RuntimeException("无效业务操作");
		}
	}
}
