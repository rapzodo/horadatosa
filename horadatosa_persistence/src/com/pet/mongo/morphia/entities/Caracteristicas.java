package com.pet.mongo.morphia.entities;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Caracteristicas {

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
