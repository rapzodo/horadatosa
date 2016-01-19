package com.pet.mongo.morphia.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Version;

public class DomainSuperClass {
	
	@Id
	private long _id;
	@Version("version")
	private long version;
	private Date dateCadastro = new Date();

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getDateCadastro() {
		return dateCadastro;
	}

	public void setDateCadastro(Date dateCadastro) {
		this.dateCadastro = dateCadastro;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}
	
	

}
