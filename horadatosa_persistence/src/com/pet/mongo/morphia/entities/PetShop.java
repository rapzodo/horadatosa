package com.pet.mongo.morphia.entities;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

@Entity(noClassnameStored=true,value="petshops")
@Indexes(@Index(fields={@Field(value="_id"),@Field(value="cpfCnpj"),@Field(value="dateCadastro", type=IndexType.DESC)}))
public class PetShop extends UsuarioSistema{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4719950390811670176L;
	private String cpfCnpj;
	private String razaoSocial;
	@Reference(lazy=true,idOnly=true)
	private List<Usuario> clientes;
	private boolean adimplente;
	private String horarioAbertura;
	private String horarioFechamento;
	private List<Integer> diasFuncionamento;
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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
	public String getHorarioAbertura() {
		return horarioAbertura;
	}
	public void setHorarioAbertura(String horarioAbertura) {
		this.horarioAbertura = horarioAbertura;
	}
	public String getHorarioFechamento() {
		return horarioFechamento;
	}
	public void setHorarioFechamento(String horarioFechamento) {
		this.horarioFechamento = horarioFechamento;
	}
	public List<Integer> getDiasFuncionamento() {
		return diasFuncionamento;
	}
	public void setDiasFuncionamento(List<Integer> diasFuncionamento) {
		this.diasFuncionamento = diasFuncionamento;
	}
}
