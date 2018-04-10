package com.butone.model.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.butone.model.config.LogicFieldType;
import com.butone.model.enums.LogicFieldKind;
import com.butone.xml.JaxbUtils;

/**
 * 逻辑字段工具,设计器应先同步服务器上的逻辑字段定义
 * 
 * @author Administrator
 * 
 */
public class LogicFieldUtils {
	public final static String LogicFieldsFileName = "com.butone.model.logicFieldsFileName";

	private static List<LogicFieldType> logicFieldTypes;

	public static void main(String[] a) {
		try {
			List<LogicFieldType> types = getDefaultLogicFields();
			for (LogicFieldType type : types) {
				System.out.println(ToStringBuilder.reflectionToString(type));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<LogicFieldType> getDefaultLogicFields() throws Exception {
		if (logicFieldTypes == null) {
			String fileName = System.getProperty(LogicFieldsFileName);
			if (fileName == null) {
				fileName = URLDecoder.decode(
						LogicFieldUtils.class.getResource("../../../../conf")
								.getFile(), "utf-8");
				fileName += File.separator + "logicFields.xml";
			}

			System.out.println(fileName);
			logicFieldTypes = parseLogicFields(fileName);
		}
		return logicFieldTypes;
	}

	/**
	 * 获得系统当前支持的逻辑字段定义，用于新建物理字段
	 * 
	 * @resource/conf/LogicFields.xml
	 * @param configFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws FileNotFoundException
	 */
	public static List<LogicFieldType> parseLogicFields(String configFile)
			throws FileNotFoundException {
		List<LogicFieldType> result = new ArrayList<LogicFieldType>();
		InputStream in = new FileInputStream(configFile);
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(LogicFieldType.class);// ,
			// LogicSubFieldDefine.class
			Unmarshaller unJaxbMarshaller = jaxbContext.createUnmarshaller();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(in);
			NodeList fldNodes = doc.getElementsByTagName("LogicField");
			for (int index = 0; index < fldNodes.getLength(); index++) {
				org.w3c.dom.Node node = fldNodes.item(index);
				LogicFieldType define = (LogicFieldType) unJaxbMarshaller
						.unmarshal(node);
				result.add(define);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static LogicFieldType findLogicFieldType(LogicFieldKind kind) {
		try {
			List<LogicFieldType> logicFieldTypes = LogicFieldUtils
					.getDefaultLogicFields();
			for (LogicFieldType type : logicFieldTypes)
				if (type.getName().equals(kind.toString()))
					return type;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过逻辑参数获得逻辑字段对象实例
	 * 
	 * @param logicKind
	 * @param logicFieldParam
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws JAXBException
	 */
	public static Object newInstance(LogicFieldKind logicKind,
			String logicFieldParam) throws Exception {
		Iterator<LogicFieldType> i = parseLogicFields("logicFields.xml")
				.iterator();
		while (i.hasNext()) {
			LogicFieldType lft = i.next();
			if (lft.getName().equals(logicKind.toString())) {
				Class<?> c = Class.forName(lft.getConfigure());
				InputStream in = new ByteArrayInputStream(
						logicFieldParam.getBytes());
				return JaxbUtils.unMarshal(in, "utf-8", c);
			}
		}
		return null;
	}

}
