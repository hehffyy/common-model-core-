package com.butone.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
/**
 * 数据包中数据行的字段信息，包括字段ID和字段值
 * 
 * <p>
 * Java class for ColumnData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="dataField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @see RowData.exchange.data.DataRow
 * @author Administrator
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ColumnData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8115752992789956839L;
	/**
	 * 字段ID
	 */
	private String columnName;
	/**
	 * 字段值
	 */
	private Object value;

	public ColumnData() {

	}

	public ColumnData(String columnName, Object value) {
		this.columnName = columnName;
		this.value = value;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
