package com.butone.codeinf.model.node;

/**
 * 计算节点
 * @author HQ
 * @version  1.0, 2014-3-9 下午10:28:33
 */
public class VariableNode extends Node {

	private static final long serialVersionUID = 4812665557758186799L;

	//取值sql 脚步
	private String script;

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
}
