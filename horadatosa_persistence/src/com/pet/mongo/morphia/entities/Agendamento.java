package com.pet.mongo.morphia.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

@Entity(value="agendamentos",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="_id"),
		@Field(value="cliente._id"),@Field(value="dataAgendamento", type=IndexType.DESC)}))
public class Agendamento extends DomainSuperClass {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8734128324515039934L;
	@Reference(idOnly=true)
	private PetShop petShop;
	@Reference(idOnly=true)
	private Usuario cliente;
	@Reference(idOnly=true)
	private Servico servico;
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

	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
}
