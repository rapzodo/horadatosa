package com.pet.ejbs;

import java.util.List;

import org.mongodb.morphia.Key;

public interface BasicCrudSBean<MODELO> {
	
	public Key<MODELO> save(MODELO modelo);
	public List<MODELO> listAll();
	public MODELO getById(String id);

}
