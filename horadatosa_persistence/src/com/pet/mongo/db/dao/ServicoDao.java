package com.pet.mongo.db.dao;

import com.pet.mongo.morphia.entities.Servico;

public class ServicoDao extends BaseMongoDao<Servico>{

	public ServicoDao(){
		super(Servico.class);
	}
}
