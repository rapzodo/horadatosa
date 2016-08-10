package com.api.config;

import java.io.Serializable;

import com.api.constants.DBConstants;

public class EnvConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4655452003709466489L;
	private String dbName;
	private String entitiesPackage;
	private String server = DBConstants.LOCAL_SERVER;
	private int port = DBConstants.PORT;
	
	public EnvConfig(){
	}
	
	public EnvConfig( String dbName, String entitiesPackage){
		this.dbName=dbName;
		this.entitiesPackage=entitiesPackage;
	}
	public EnvConfig(String server, int port, String dbName, String entitiesPackage){
		this.server = server;
		this.port = port;
		this.dbName=dbName;
		this.entitiesPackage=entitiesPackage;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getEntitiesPackage() {
		return entitiesPackage;
	}

	public void setEntitiesPackage(String entitiesPackage) {
		this.entitiesPackage = entitiesPackage;
	}
	
}
