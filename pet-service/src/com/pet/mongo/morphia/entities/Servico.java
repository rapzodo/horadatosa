package com.pet.mongo.morphia.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;

@Entity(value="servicos",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="_id"),@Field(value="_id"),@Field(value="codigo"),@Field(value="preco", type=IndexType.ASC)}))
public class Servico extends DomainSuperClass {

	private String categoria;
	private String descricao;
	private int capacidade;
	private double preco;
	
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
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}
