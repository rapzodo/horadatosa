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

@Entity(value="pedidos",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="_id"),@Field(value="nome"),@Field(value="raca"),@Field(value="dataServico", type=IndexType.DESC)}))
public class ordemServico extends DomainSuperClass {
	
	@Reference
	private PetShop petShop;
	@Property("itens")
	@Reference(idOnly=true, lazy=true)
	private List<Servico> servicosPrestados;
	@Property("realizadoEm")
	private Date dataServico;
	@Property("agendadoPara")
	private Date dataAgendamento;
	@Reference
	private Usuario cliente;
	
	public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	public PetShop getPetShop() {
		return petShop;
	}
	public void setPetShop(PetShop petShop) {
		this.petShop = petShop;
	}
	public List<Servico> getServicosPrestados() {
		return servicosPrestados;
	}
	public void setServicosPrestados(List<Servico> servicosPrestados) {
		this.servicosPrestados = servicosPrestados;
	}
	public Date getDataServico() {
		return dataServico;
	}
	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}
	
	
}
