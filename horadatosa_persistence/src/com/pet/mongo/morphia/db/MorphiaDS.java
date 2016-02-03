package com.pet.mongo.morphia.db;

import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.pet.constants.DBConstants;

public class MorphiaDS {
	private Morphia morphia = new Morphia();
	private Datastore ds;
	private static MorphiaDS instance;
	
	private MorphiaDS(){
		config();
	}
	
	public void config(){
		
		morphia.mapPackage(DBConstants.ENTITIES_PKG);
		ds = morphia.createDatastore(getSimpleClient(), DBConstants.DB_NAME);
		ds.ensureIndexes();
	}
	
	public static MorphiaDS getinstance(){
		if(instance == null){
			instance = new MorphiaDS();
		}
		return instance;
	}
	public Datastore getDataStore(){
		return ds;
	}
	private MongoClient getSimpleClient(){
		return new MongoClient(new ServerAddress(DBConstants.LOCAL_SERVER, DBConstants.PORT));
	}
	private MongoClient getSSLClient(){
		MongoCredential shaCredential = MongoCredential.createScramSha1Credential(DBConstants.USER, DBConstants.DB_NAME, DBConstants.PWD.toCharArray());
		Builder builder = MongoClientOptions.builder();
//		TIMEOUT
		builder.connectTimeout(DBConstants.TIME_OUT)
		.socketTimeout(DBConstants.TIME_OUT)
		.serverSelectionTimeout(DBConstants.TIME_OUT)
//		ENABLING SSL
		.sslEnabled(true)
		.sslInvalidHostNameAllowed(true);
		return new MongoClient(new ServerAddress(DBConstants.SERVER, DBConstants.PORT), Arrays.asList(shaCredential), builder.build());
	}
}
