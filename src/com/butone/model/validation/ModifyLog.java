package com.butone.model.validation;

import java.io.Serializable;
import java.util.Date;

import com.butone.model.Version;

/**
 * 变动表
 * @author hehffyy
 *
 */
public class ModifyLog extends Version implements Serializable{

	private static final long serialVersionUID = -6059954737185541333L;
	    //表名
		private String name;
		//模块名
		private String displayName;

		//字段名
		private String fieldName;
		//行数据Guid
		private String modelGuid;
		//操作动作类型
		private String action;
		//新值
		private String newValue;
		//旧值
		private String oldValue;
		//改动日期
		private Date logDate;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getModelGuid() {
			return modelGuid;
		}
		public void setModelGuid(String modelGuid) {
			this.modelGuid = modelGuid;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getNewValue() {
			return newValue;
		}
		public void setNewValue(String newValue) {
			this.newValue = newValue;
		}
		public String getOldValue() {
			return oldValue;
		}
		public void setOldValue(String oldValue) {
			this.oldValue = oldValue;
		}
		public Date getLogDate() {
			return logDate;
		}
		public void setLogDate(Date logDate) {
			this.logDate = logDate;
		}
		
}
