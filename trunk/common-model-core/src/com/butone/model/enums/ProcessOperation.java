package com.butone.model.enums;

public enum ProcessOperation {
	back, advance,transfer, suspend, abort,customized,showChart,executeList ;

	public String getDispName() {
		switch (this) {
		case back:
			return "回退";
		case advance:
			return "流转";
		case transfer:
			return "转发";
		case suspend:
			return "暂停";
		case abort:
			return "终止";
		case customized:
			return "流程定制";
		case showChart:
			return "流程图";
		case executeList:
			return "执行列表";
		default:
			break;
		}
		return this.toString();
	}
 
}
