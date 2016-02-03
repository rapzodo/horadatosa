package com.pet.mongo.db.dao;

import com.pet.mongo.morphia.entities.Animal;

public class AnimalDao extends BaseMongoDao<Animal>{

	public AnimalDao(){
		super(Animal.class);
	}
}
