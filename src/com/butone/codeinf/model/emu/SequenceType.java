package com.butone.codeinf.model.emu;


public enum SequenceType {
	/**
	 * 小写补零(0099)
	 */
	IS_Lower_FillZero,

	/**
	 * 小写不补零(99)
	 */
	IS_Lower_NotFillZero,

	/**
	 * 大写补零(零零九九)
	 */
	IS_Capital_FillZero,

	/**
	 * 大写不补零(九九)
	 */
	IS_Capital_NotFillZero
}
