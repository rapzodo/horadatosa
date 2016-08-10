package com.pet.mongo.db.dao;

import com.api.morphia.dao.BaseMongoDao;
import com.pet.mongo.db.PetDbConfig;
import com.pet.mongo.morphia.entities.Animal;

public class AnimalDao extends BaseMongoDao<Animal>{

	public AnimalDao(){
		super(Animal.class,PetDbConfig.getDs(false));
	}
}
