package com.pet.mongo.db.dao;

import com.pet.mongo.morphia.entities.Usuario;

public class UsuarioDao extends BaseMongoDao<Usuario>{

	public UsuarioDao(){
		super(Usuario.class);
	}
}
