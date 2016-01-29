package com.pet.rest;

import javax.ws.rs.Path;

import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.morphia.entities.Servico;

@Path("/servicos")
public class ServicoCRUDService extends BaseCrudService<Servico>{
	
	public ServicoCRUDService(){
		dao = new BaseMongoDao<>(Servico.class);
	}
	
}
