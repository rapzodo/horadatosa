package com.pet.mongo.morphia.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

@Entity(value="animais",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="_id"),@Field(value="nome"),@Field(value="raca")}))
public class Animal extends DomainSuperClass {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2932486028693819217L;
	private String nome;
	private String tipo;
	private String raca;
	private Caracteristicas caracteristicas;
	@Reference
	private Usuario dono;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public Caracteristicas getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(Caracteristicas caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public Usuario getDono() {
		return dono;
	}
	public void setDono(Usuario dono) {
		this.dono = dono;
	}

}
