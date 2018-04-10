package com.butone.model.enums;

public enum TableOperation {
	append, newChild,newBrother, save, delete,refresh,prevPage,nextPage,prevRow,nextRow,loadNextPage,loadAllPage;

	public String getDispName() {
		switch (this) {
		case append:
			return "新增";
		case newChild:
			return "新增子节点";
		case newBrother:
			return "新增兄弟节点";
		case save:
			return "保存";
		case delete:
			return "删除";
		case refresh:
			return "刷新";
		case prevPage:
			return "上页";
		case nextPage:
			return "下页";
		case prevRow:
			return "上一条";
		case nextRow:
			return "下一条";
		case loadNextPage:
			return "下页";
		case loadAllPage:
			return "全部";
			
		default:
			break;
		}
		return this.toString();
	}

	@Override
	public String toString() {
		if (this.equals(append))
			return "new";
		else
			return super.toString();
	}

	public static TableOperation valueOfOperation(String value) {
		if ("new".equals(value))
			return TableOperation.append;
		else
			return TableOperation.valueOf(value);
	}
}
