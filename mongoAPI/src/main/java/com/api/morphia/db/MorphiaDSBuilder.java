package com.api.morphia.db;

import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.api.config.EnvConfig;
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
	private static Datastore ds;
	private String userName;
	private String password;
	private EnvConfig config;
	
	
	public MorphiaDSBuilder(EnvConfig config){
		this.config = config;
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
		MongoCredential shaCredential = MongoCredential.createScramSha1Credential(userName, config.getDbName(), 
				password.toCharArray());
		Builder builder = MongoClientOptions.builder();
//		ENABLING SSL
		builder.sslEnabled(true)
		.sslInvalidHostNameAllowed(true);
		return new MongoClient(new ServerAddress(environment.getServer(), environment.getPort()), Arrays.asList(shaCredential), builder.build());
	}
	
	private MongoClient getSSLClient(EnvConfig environment,Builder builder){
		if(userName == null || password == null){
			throw new NullPointerException("username or password should not be null");
		}
		MongoCredential shaCredential = MongoCredential.createScramSha1Credential(userName, config.getDbName(), 
				password.toCharArray());
//		ENABLING SSL
		builder.sslEnabled(true)
		.sslInvalidHostNameAllowed(true);
		return new MongoClient(new ServerAddress(environment.getServer(), environment.getPort()), Arrays.asList(shaCredential), builder.build());
	}
	
	public void config(MongoClient client){
		morphia.mapPackage(config.getEntitiesPackage());
		ds = morphia.createDatastore(client, config.getDbName());
		ds.ensureIndexes();
	}
	
	public Datastore build(Boolean isSSLClient){
		MongoClient client;
		if(isSSLClient){
			if(userName == null || password == null){
				throw new RuntimeException("Username or Password can't be null for SSL Client");
			}else{
				client = getSSLClient(config);
			}
		}else{
			client = getSimpleClient(config);
		}
		config(client);
		return ds;
	}
	public Datastore build(Builder builder){
		config(getSSLClient(config, builder));
		return ds;
	}
}
