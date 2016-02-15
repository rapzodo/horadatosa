package com.pet.mongo.morphia.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

@Entity(value="servicos_petshops",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="_id"),@Field(value="petshop._id"),@Field(value="servico._id")}))
public class ServicosPetShops extends DomainSuperClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3592764028781079860L;
	@Reference(idOnly=true)
	private PetShop petshop;
	@Reference(idOnly=true)
	private Servico servico;
	private Integer capacidade;
	private Double preco;
	
	public PetShop getPetshop() {
		return petshop;
	}
	public void setPetshop(PetShop petshop) {
		this.petshop = petshop;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Integer getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
}
