package com.api.morphia.db;

import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.api.config.EnvConfig;
import com.api.constants.DBConstants;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


/**
 * 
 * @author Danilo De Castro
 * Builder class for Morphia DataStore
 *
 */
public class MorphiaDSBuilder {
	private Morphia morphia = new Morphia();
	private Datastore ds;
	private String dbName = "defaultDB";
	private String entitiesPackage = "morphia.entities";
	private String userName;
	private String password;
	
	
	public MorphiaDSBuilder(){
	}
	
	public MorphiaDSBuilder setDBName(String dbName){
		this.dbName = dbName;
		return this;
	}
	
	public  MorphiaDSBuilder setEntitiesPackage(String entitiesPackage){
		this.entitiesPackage = entitiesPackage;
		return this;
	}
	
	public  MorphiaDSBuilder setUsername(String username){
		this.userName = username;
		return this;
	}
	
	public  MorphiaDSBuilder setPassword(String password){
		this.password = password;
		return this;
	}
	
	private MongoClient getSimpleClient(EnvConfig environment){
		return new MongoClient(new ServerAddress(environment.getServer(), environment.getPort()));
	}
	
	private MongoClient getSSLClient(EnvConfig environment){
		if(userName == null || password == null){
			throw new NullPointerException("username or password should not be null");
		}
		MongoCredential shaCredential = MongoCredential.createScramSha1Credential(userName, dbName, 
				password.toCharArray());
		Builder builder = MongoClientOptions.builder();
//		TIMEOUT
		builder.connectTimeout(DBConstants.TIME_OUT)
		.socketTimeout(DBConstants.TIME_OUT)
		.serverSelectionTimeout(DBConstants.TIME_OUT)
//		ENABLING SSL
		.sslEnabled(true)
		.sslInvalidHostNameAllowed(true);
		return new MongoClient(new ServerAddress(environment.getServer(), environment.getPort()), Arrays.asList(shaCredential), builder.build());
	}
	
	public void config(MongoClient client){
		morphia.mapPackage(entitiesPackage);
		ds = morphia.createDatastore(client, dbName);
		ds.ensureIndexes();
	}
	
	public Datastore build(EnvConfig config, Boolean isSSLClient){
		MongoClient client;
		if(isSSLClient){
			client = getSSLClient(config);
		}else{
			client = getSimpleClient(config);
		}
		config(client);
		return ds;
	}
}
