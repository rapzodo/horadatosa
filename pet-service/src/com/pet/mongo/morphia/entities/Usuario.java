package com.pet.mongo.morphia.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;

@Entity(value="usuarios",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="nome"),@Field(value="dataCadastro", type=IndexType.DESC)}))
public class Usuario extends DomainSuperClass{

	private String emailId;
	private String nome;
	private Date dataCadastro;
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataCriacao() {
		return dataCadastro;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCadastro = dataCriacao;
	}
	
	
}
