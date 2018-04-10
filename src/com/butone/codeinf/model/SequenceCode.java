package com.butone.codeinf.model;

import java.util.List;

import com.butone.codeinf.model.node.ParameterNode;
import com.butone.codeinf.model.node.VariableNode;

public interface SequenceCode {
	/**
	 * 计算参数节点值
	 * @param node
	 * @return
	 */
	String calcNodeValue(ParameterNode node);

	/**
	 * 计算变量节点值
	 * @param node
	 * @return
	 */
	String calcNodeValue(VariableNode node);

	/**
	 * 初始化通用编码序列日志
	 * @param codeGuid
	 * @param groupValue
	 * @param relTableName
	 * @return
	 */
	int initSequenceCodeLog(String codeGuid, String groupValue, String relTableName, String codeName);

	/**
	 * 更新通用编码序列日志，返回更新后的序列值
	 * @param codeGuid
	 * @param groupValue
	 * @param relTableName
	 * @param interval
	 * @param codeName
	 * @return
	 */
	int updateSequenceCodeLog(String codeGuid, String groupValue, String relTableName, int interval, boolean immediate);

	/**
	 * 更新通用编码序列使用记录
	 * @param codeGuid
	 * @param groupValue
	 * @param relTableName
	 * @param userTable
	 * @param userField
	 * @param userKeyValues
	 * @param sequenceValue
	 */
	void makeSequenceUseRecord(String codeGuid, String groupValue, String relTableName, String userTable, String userField, String userKeyValues,
			String sequenceValue);

	/**
	 * 释放通用编码使用记录
	 * @param userTable
	 * @param userField
	 * @param userKeyValues
	 */
	void releaseCodeValue(String userTable, String userField, String userKeyValues);

	/**
	 * 查询未使用的通用编码
	 * @param userTable
	 * @param userField
	 * @param userKeyValues
	 */
	List<String> queryUnusedCodeValues(String codeGuid, String groupValue, String relTableName);

	/**
	 * 锁定未使用的编码值
	 * @param codeGuid
	 * @param groupValue
	 * @param relTableName
	 * @param userTable
	 * @param userField
	 * @param userKeyValues
	 */
	void lockUnusedCodeValue(String codeGuid, String groupValue, String relTableName, String sequenceValue, String userTable, String userField,
			String userKeyValues);

}
