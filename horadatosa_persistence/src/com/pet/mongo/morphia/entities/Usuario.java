package com.pet.mongo.morphia.entities;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

@Entity(value="usuarios",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="_id"),@Field(value="nome"),@Field(value="dateCadastro", type=IndexType.DESC)}))
public class Usuario extends UsuarioSistema{

	private String nome;
	private String celular;
	private Date dataNascimento;
	@Reference(idOnly=true,lazy=true)
	private List<Animal> pets;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Animal> getPets() {
		return pets;
	}
	public void setPets(List<Animal> pets) {
		this.pets = pets;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
