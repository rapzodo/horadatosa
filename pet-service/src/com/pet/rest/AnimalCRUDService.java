package com.pet.rest;

import javax.ws.rs.Path;

import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.morphia.entities.Animal;

@Path("/animais")
public class AnimalCRUDService extends BaseCrudService<Animal>{
	
	private BaseMongoDao<Animal> dao= new BaseMongoDao<>(Animal.class);
	
	public AnimalCRUDService(){
		dao = new BaseMongoDao<>(Animal.class);
	}
	
}
