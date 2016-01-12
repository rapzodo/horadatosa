package com.pet.ejb.petshops;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.mongodb.morphia.Key;

import com.pet.constants.QueriesConstants;
import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.db.factory.DaoFactory;
import com.pet.mongo.morphia.entities.PetShop;

/**
 * Session Bean implementation class PetShopSessionBean
 */
@Stateless
@LocalBean
public class PetShopSessionBean {
	private BaseMongoDao<PetShop> dao = new DaoFactory<PetShop>().getDao(PetShop.class);
    /**
     * Default constructor. 
     */
    public PetShopSessionBean() {
	}
 	
	public List<PetShop> getAllPetshops(){
		return dao.listAll();
	}
	
	public Key<PetShop> save(PetShop petshop){
		 return dao.save(petshop);
	}

	public List<PetShop> getByCpfCnpj(String cpfCnpj){
		return dao.getModelByfield(QueriesConstants.CNPJ_CPF_FIELD, cpfCnpj);
	}
	
	public List<PetShop> getByRazaoSocial(String razaoSocial){
		return dao.getModelByfield(QueriesConstants.RAZAO_SOCIAL_FIELD, razaoSocial);
	}
	
	public int delete(PetShop petshop){
		return dao.delete(petshop);
	}
}
