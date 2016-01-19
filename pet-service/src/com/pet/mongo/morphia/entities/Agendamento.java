package com.pet.mongo.morphia.entities;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

@Entity(value="agendamentos",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="_id"),@Field(value="petshop._id"),
		@Field(value="cliente._id"),@Field(value="dataAgendamento", type=IndexType.DESC)}))
public class Agendamento extends DomainSuperClass {
	
	@Reference
	private PetShop petShop;
	@Reference
	private Usuario cliente;
	@Reference(idOnly=true, lazy=true)
	private List<Servico> servicos;
	@Property("dataAgendamento")
	private Date dataAgendamento;
	
	public PetShop getPetShop() {
		return petShop;
	}
	public void setPetShop(PetShop petShop) {
		this.petShop = petShop;
	}
	public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
}
