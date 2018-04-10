package com.butone.codeinf.model.node;

import com.butone.codeinf.model.emu.SequenceType;

/**
 * 序号节点
 * @author HQ
 * @version  1.0, 2014-3-9 下午02:37:55
 */
public class SequenceNode extends Node {

	private static final long serialVersionUID = -2771056957114412897L;
	// 序号长度
	private int sequenceLength = 5;

	//序号与使用表相关
	private boolean relTableName;
	//序号自动增长(否则为指定格式的数字节点)
	//private boolean isAuto;
	//废后利用（默认是开启）
	private boolean recycle = true;
	//序号规则
	private String ruleType = SequenceType.IS_Lower_FillZero.name();

	public int getSequenceLength() {
		return sequenceLength;
	}

	public void setSequenceLength(int sequenceLength) {
		this.sequenceLength = sequenceLength;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public boolean isRecycle() {
		return recycle;
	}

	public void setRecycle(boolean recycle) {
		this.recycle = recycle;
	}

	public boolean isRelTableName() {
		return relTableName;
	}

	public void setRelTableName(boolean relTableName) {
		this.relTableName = relTableName;
	}
}
