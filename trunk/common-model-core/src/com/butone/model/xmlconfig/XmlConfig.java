package com.butone.model.xmlconfig;

import org.dom4j.Document;

/**
 * XML配置接口
 * 
 * @author Administrator
 * 
 */
public interface XmlConfig {
	/**
	 * 输出为XML
	 * 
	 * @return
	 */
	public String asXml();

	public Document getDocument();

	/**
	 * 解析XML
	 * 
	 * @param xml
	 * @throws Exception
	 */
	public void parseXml(String xml) throws Exception;

	/**
	 * 主要版本号
	 * 
	 * @return
	 */
	public Integer getMajorVersionNumber();

	/**
	 * 次要版本号
	 * 
	 * @return
	 */
	public Integer getMinorVersionNumber();

	/**
	 * 版本兼容判断
	 * 
	 * @param marjorVersion
	 * @param minorVer
	 * @return
	 */
	public boolean compatible(Integer marjorVersion, Integer minorVer);
}
