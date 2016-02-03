package com.pet.mongo.morphia.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity(value="servicos",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="_id")}))
public class Servico extends DomainSuperClass {

	private String categoria;
	private String descricao;
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
