package com.butone.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class QueryStruct {
	@XmlElementWrapper(name = "columns")
	@XmlElement(name = "column")
	List<ColumnMetaData> columns = new ArrayList<ColumnMetaData>();

	String paramNames;

	public List<ColumnMetaData> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnMetaData> columns) {
		this.columns = columns;
	}

	public String getParamNames() {
		return paramNames;
	}

	public void setParamNames(String paramNames) {
		this.paramNames = paramNames;
	}
}
