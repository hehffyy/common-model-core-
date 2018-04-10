package com.butone.codeinf.model.node;

/**
 * 常数节点
 * @author HQ
 * @version  1.0, 2014-3-9 下午10:21:31
 */
public class ConstantNode extends Node {

	private static final long serialVersionUID = -2366186272263738982L;
	//节点常量值
	private String constStr;

	public String getConstStr() {
		return constStr;
	}

	public void setConstStr(String constStr) {
		this.constStr = constStr;
	}
}
