package com.horadatosa.client.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.horadatosa.service.client.RestClientCaller;
import com.pet.mongo.db.dao.PetShopDao;
import com.pet.mongo.morphia.entities.Endereco;
import com.pet.mongo.morphia.entities.PetShop;

@ManagedBean(name="petBean")
@ViewScoped
public class PetShopsManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PetShop petshop ;
	private List<PetShop> petshops = new ArrayList<PetShop>();
	private String uri = RestClientCaller.CRUDSERVICEBASEURI;
	private PetShopDao dao = new PetShopDao();
	
	
	@PostConstruct
	private void init(){
		try {
//			petshops = (List<PetShop>) RestClientCaller.get(uri, MediaType.APPLICATION_JSON_TYPE,"petshops");
			petshops = dao.listAll();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"DEU BOSTA", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	public void cadastraPetShop(){
		try {
			String msg="Falha ao executar";
			if(saveUpdate()){
				msg="Executado com Sucesso!";
			}
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(String.valueOf(msg)));
		} catch (Exception e) {	
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"DEU BOSTA", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	private Boolean saveUpdate() throws Exception {
//		ClientResponse response = RestClientCaller.post(uri, MediaType.APPLICATION_JSON,
//				petshop, "petshops","createUpdate");
//		int status = response.getStatus();
//		if(status == HttpURLConnection.HTTP_OK){
//			petshop = null;
//			return true;
//		}
		if(dao.saveOrUpdate(petshop)!= 0){
			petshop = null;
			return true;
		}
		return false;
	}
	
	public void edit(RowEditEvent event){
		
		petshop = (PetShop) event.getObject();
		
		try {
				saveUpdate();
				petshops = dao.listAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void cancel(RowEditEvent event){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("cancelou"));
	}

	public PetShop getPetshop() {
		if(petshop == null){
			petshop = new PetShop();
			petshop.setEndereco(new Endereco());
		}
		return petshop;
	}


	public void setPetshop(PetShop petshop) {
		this.petshop = petshop;
	}


	public List<PetShop> getPetshops() {
		return petshops;
	}


	public void setPetshops(List<PetShop> petshops) {
		this.petshops = petshops;
	}
}
