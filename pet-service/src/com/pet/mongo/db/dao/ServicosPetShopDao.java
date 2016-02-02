package com.pet.mongo.db.dao;

import java.util.List;

import org.mongodb.morphia.query.Query;

import com.pet.mongo.morphia.entities.PetShop;
import com.pet.mongo.morphia.entities.Servico;
import com.pet.mongo.morphia.entities.ServicosPetShops;

public class ServicosPetShopDao extends BaseMongoDao<ServicosPetShops>{

	public ServicosPetShopDao(){
		super(ServicosPetShops.class);
	}
	
	public List<ServicosPetShops> getServicos(PetShop petshop){
		return getModelByfield("petshop", petshop);
	}
	public ServicosPetShops getServicosByPetShopAndServico(PetShop petshop, Servico servico){
		Query<ServicosPetShops> query = getDs().createQuery(ServicosPetShops.class)
				.field("petshop").equal(petshop)
				.field("servico").equal(servico);
		return getModelByQuery(query);
	}
}
