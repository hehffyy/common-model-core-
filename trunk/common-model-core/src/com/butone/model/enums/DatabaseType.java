package com.butone.model.enums;

public enum DatabaseType {
	ORACLE, DB2, SQLSERVER, MYSQL, SYBASE;

	public static String getHibernamteDialect(String dbType) {
		DatabaseType theType = null;
		for (DatabaseType type : DatabaseType.values()) {
			if (dbType.toUpperCase().startsWith(type.toString()))
				theType = type;
		}
		if (theType == null)
			throw new RuntimeException("不支持的数据库类型:" + dbType);

		switch (theType) {
		case ORACLE:
			return "com.butone.builder.OracleDialectEx";
		case MYSQL:
			return org.hibernate.dialect.MySQLDialect.class.toString();
		case DB2:
			return org.hibernate.dialect.DB2Dialect.class.toString();
		case SQLSERVER:
			return org.hibernate.dialect.SQLServerDialect.class.toString();
		case SYBASE:
			return org.hibernate.dialect.Sybase11Dialect.class.toString();
		}
		throw new RuntimeException("不支持的数据库类型:" + dbType);

	}

	public static DatabaseType getDatabaseType(String dbType) {
		for (DatabaseType type : DatabaseType.values()) {
			if (dbType.toUpperCase().startsWith(type.toString()))
				return type;
		}
		return null;
	}
}
