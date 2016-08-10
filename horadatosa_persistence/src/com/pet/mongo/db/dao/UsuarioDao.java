package com.pet.mongo.db.dao;

import com.api.morphia.dao.BaseMongoDao;
import com.pet.mongo.db.PetDbConfig;
import com.pet.mongo.morphia.entities.Usuario;

public class UsuarioDao extends BaseMongoDao<Usuario>{

	public UsuarioDao(){
		super(Usuario.class,PetDbConfig.getDs(false));
	}
}
