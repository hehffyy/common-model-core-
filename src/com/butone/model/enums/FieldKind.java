package com.butone.model.enums;

/**
 * 字段类型，表字段属性
 * 
 * @author Administrator
 * 
 */
public enum FieldKind {
	/**
	 * 普通字段
	 */
	fkNormal,
	/**
	 * 逻辑字段
	 */
	fkLogic,
	/**
	 * 关联字段，一般为逻辑字段的附属字段，字段属性只读不允许修改
	 */
	fkRelated,
	/**
	 * 数据集字段
	 */
	fkRefTableField,
	/**
	 * SQL计算字段
	 */
	fkSQLCalcField
}
