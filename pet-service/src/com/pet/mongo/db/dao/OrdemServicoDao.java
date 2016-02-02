package com.pet.mongo.db.dao;

import com.pet.mongo.morphia.entities.OrdemServico;

public class OrdemServicoDao extends BaseMongoDao<OrdemServico>{

	public OrdemServicoDao(){
		super(OrdemServico.class);
	}
}
