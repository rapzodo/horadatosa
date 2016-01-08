package pet.mongo.morphia.entities;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

@Entity
@Indexes(@Index(fields={@Field(value="nome")}))
public class Usuario {

	@Id
	private String emailId;
	private String nome;
	private Date dataCriacao;
	@Reference(lazy=true)
	private List<PetShop> petShops;
	
	public List<PetShop> getPetShops() {
		return petShops;
	}
	public void setPetShops(List<PetShop> petShops) {
		this.petShops = petShops;
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
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	
}
