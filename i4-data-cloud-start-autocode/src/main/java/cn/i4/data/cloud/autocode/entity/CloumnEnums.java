package cn.i4.data.cloud.autocode.entity;

import java.util.HashMap;
import java.util.Map;

public enum CloumnEnums {
	
	BIGINT("BIGINT", "Long" , null),
	TIMESTAMP("TIMESTAMP", "java.util.Date" , "java.util.Date"),
	DATE("DATE", "java.util.Date" , "java.util.Date"),
	DATETIME("DATETIME", "java.util.Date" , "java.util.Date"),
	DECIMAL("DECIMAL", "java.math.BigDecimal" , "java.math.BigDecimal"),
	TEXT("TEXT", "String" , null),
	LONGTEXT("LONGTEXT", "String" , null),
	VARCHAR("VARCHAR", "String" , null),
	INT("INT", "Integer" , null),
	TINYINT("TINYINT", "Integer" , null),
	DOUBLE("DOUBLE", "Double" , null),
	FLOAT("FLOAT", "Float" , null),
	BIGINTUNSIGNED("BIGINT UNSIGNED", "Long" , null),
	BIT("BIT", "Boolean" , null),
	
	;
	
	static Map<String , CloumnEnums> DICT = new HashMap<String , CloumnEnums>();
	
	static{
		CloumnEnums[] es = CloumnEnums.values();
		for(CloumnEnums e : es){
			DICT.put(e.getDbType(), e);
		}
	}
	
	private String dbType;
	
	private String javaType;
	
	private String javaPakage;
	
	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJavaPakage() {
		return javaPakage;
	}

	public void setJavaPakage(String javaPakage) {
		this.javaPakage = javaPakage;
	}

	CloumnEnums(String dbType  , String javaType , String javaPakage){
		this.dbType = dbType;
		this.javaType = javaType;
		this.javaPakage = javaPakage;
	}
	
	
	public static CloumnEnums matchCloumnEnums(String dbType){
		return DICT.get(dbType);
	}
	

}
