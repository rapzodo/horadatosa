package com.pet.mongo.db.dao;

import com.api.morphia.dao.BaseMongoDao;
import com.pet.mongo.db.PetDbConfig;
import com.pet.mongo.morphia.entities.Servico;

public class ServicoDao extends BaseMongoDao<Servico>{

	public ServicoDao(){
		super(Servico.class,PetDbConfig.getDs(false));
	}
}
