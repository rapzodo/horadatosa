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
@Indexes(@Index(fields={@Field(value="_id"),@Field(value="agendamento._id"),@Field(value="dataServico", type=IndexType.DESC)}))
public class OrdemServico extends DomainSuperClass {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5409591338565274848L;
	@Reference(idOnly=true, lazy=true)
	private List<Servico> servicosPrestados;
	@Property("realizadoEm")
	private Date dataServico;
	@Reference
	private Agendamento agendamento;
	
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
	public Agendamento getAgendamento() {
		return agendamento;
	}
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
}