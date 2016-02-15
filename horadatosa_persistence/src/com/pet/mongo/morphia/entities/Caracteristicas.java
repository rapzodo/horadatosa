package com.pet.mongo.morphia.entities;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Caracteristicas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8091826497975986895L;
	private String pelo;
	private String cor;
	private String porte;
	
	public String getPelo() {
		return pelo;
	}
	public void setPelo(String pelo) {
		this.pelo = pelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getPorte() {
		return porte;
	}
	public void setPorte(String porte) {
		this.porte = porte;
	}
}
