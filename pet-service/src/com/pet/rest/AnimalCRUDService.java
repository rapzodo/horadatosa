package com.pet.rest;

import javax.ws.rs.Path;

import com.pet.mongo.db.dao.AnimalDao;
import com.pet.mongo.morphia.entities.Animal;

@Path("/animais")
public class AnimalCRUDService extends BaseCrudService<Animal>{
	
	public AnimalCRUDService(){
		dao = new AnimalDao();
	}
	
}
