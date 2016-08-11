package com.pet.mongo.db;

import org.mongodb.morphia.Datastore;

import com.api.config.EnvConfig;
import com.api.morphia.db.MorphiaDSBuilder;

public class PetDbConfig {
	
	private static EnvConfig config = new EnvConfig("petdb", "com.pet.mongo.morphia.entities");
	private static Datastore ds;
	
	private PetDbConfig(){
		ds = new MorphiaDSBuilder(config).build(false);
	}
	
	public static Datastore getDs(Boolean isSSBoolean){
		if(ds == null){
			ds = new MorphiaDSBuilder(config).build(false);
		}
		return ds;
	}
	
}
