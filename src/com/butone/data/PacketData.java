package com.butone.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 数据包，包元数据描述及数据行列表
 * 
 * <p>
 * Java class for DataPacket complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="dataSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="meta" type="{http://data.butone.com/}meta" minOccurs="0"/>
 *         &lt;element name="rows" type="{http://data.butone.com/}dataRow" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @see com.butone.data.RowData
 * @author Administrator
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class PacketData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8432101911880961192L;
	@XmlElement(nillable = true)
	private List<RowData> rows;

	/**
	 * Gets the value of the rows property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the rows property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRows().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link RowData }
	 * 
	 * 
	 */
	public List<RowData> getRows() {
		if (rows == null) {
			rows = new ArrayList<RowData>();
		}
		return this.rows;
	}

	public void setRows(List<RowData> rows) {
		this.rows = rows;
	}

}
