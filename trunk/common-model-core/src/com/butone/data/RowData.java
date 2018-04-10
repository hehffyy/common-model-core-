package com.butone.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 数据行,包含多个ColumnData<BR>
 * 
 * <p>
 * Java class for RowData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="dataRow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fields" type="{http://data.butone.com/}dataField" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @see com.butone.data.ColumnData
 * @see com.butone.data.PacketData
 * @author Administrator
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class RowData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2224849289125744029L;
	@XmlElement(nillable = true)
	private List<ColumnData> columns;

	/**
	 * Gets the value of the fields property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the fields property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFields().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ColumnData }
	 * 
	 * 
	 */
	public List<ColumnData> getColumns() {
		if (columns == null) {
			columns = new ArrayList<ColumnData>();
		}
		return this.columns;
	}

	public void setColumns(List<ColumnData> fields) {
		this.columns = fields;
	}

	public void addColumn(ColumnData data) {
		getColumns().add(data);
	}

	public Object getValue(String column) {
		for (ColumnData data : getColumns()) {
			if (data.getColumnName().equalsIgnoreCase(column))
				return data.getValue();
		}
		return null;
	}

	public <T> T getValue(String column, Class<T> cls) {
		for (ColumnData data : getColumns()) {
			if (data.getColumnName().equalsIgnoreCase(column))
				return (T) data.getValue();
		}
		return null;
	}

}
