package com.pet.mongo.morphia.entities;

import org.mongodb.morphia.annotations.Id;

public class DomainSuperClass {
	
	@Id
	private long _id;

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}
	
	

}
