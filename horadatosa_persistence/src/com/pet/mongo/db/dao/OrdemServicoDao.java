package com.pet.mongo.db.dao;

import com.api.morphia.dao.BaseMongoDao;
import com.pet.mongo.db.PetDbConfig;
import com.pet.mongo.morphia.entities.OrdemServico;

public class OrdemServicoDao extends BaseMongoDao<OrdemServico>{

	public OrdemServicoDao(){
		super(OrdemServico.class,PetDbConfig.getDs(false));
	}
}
