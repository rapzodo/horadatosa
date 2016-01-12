package com.pet.ejbs;

import java.util.List;

import org.mongodb.morphia.Key;

import com.pet.mongo.morphia.entities.Usuario;

public class UsuarioSessionBean implements BasicCrudSBean<Usuario> {

	@Override
	public Key<Usuario> save(Usuario modelo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
