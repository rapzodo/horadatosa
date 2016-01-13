package com.pet.mongo.morphia.entities;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

@Entity(noClassnameStored=true,value="petshops")
@Indexes(@Index(fields={@Field(value="cnpj"),@Field(value="dataCadastro", type=IndexType.DESC)}))
public class PetShop {
	
	@Id
	private long _id;
	private String cpfCnpj;
	private String razaoSocial;
	private Date dataCadastro = new Date();
	@Reference(lazy=true,idOnly=true)
	private List<Usuario> clientes;
	private boolean adimplente;
	@Reference(lazy=true,idOnly=true)
	private List<Servico> servicos;
	private Endereco endereco;
	
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public List<Usuario> getClientes() {
		return clientes;
	}
	public void setClientes(List<Usuario> clientes) {
		this.clientes = clientes;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public boolean isAdimplente() {
		return adimplente;
	}
	public void setAdimplente(boolean adimplente) {
		this.adimplente = adimplente;
	}
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
