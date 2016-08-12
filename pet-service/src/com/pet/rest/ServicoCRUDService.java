package com.pet.rest;

import javax.ws.rs.Path;

import com.pet.mongo.db.dao.ServicoDao;
import com.pet.mongo.morphia.entities.Servico;

@Path("/servicos")
public class ServicoCRUDService extends BaseCrudService<Servico>{
	
	public ServicoCRUDService(){
		dao = new ServicoDao();
	}
	
}
