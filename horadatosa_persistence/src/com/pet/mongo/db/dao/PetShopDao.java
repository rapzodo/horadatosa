package com.pet.mongo.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pet.mongo.morphia.entities.PetShop;

public class PetShopDao extends BaseMongoDao<PetShop>{

	public PetShopDao(){
		super(PetShop.class);
	}
	public List<PetShop> getPetShopsByEndereco(String cidade, String bairro, String UF){
		Map<String,Object> petShopParams = new HashMap<String, Object>();
		petShopParams.put("endereco.cidade", cidade);
		petShopParams.put("endereco.bairro", bairro);
		petShopParams.put("endereco.UF", UF);
		return getByComplexQueryAnd(petShopParams);
	}
}
