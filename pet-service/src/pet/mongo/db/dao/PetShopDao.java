package pet.mongo.db.dao;

import pet.mongo.morphia.entities.PetShop;

public class PetShopDao extends BaseMongoDao<PetShop> {

	public PetShopDao() {
		super(PetShop.class);
	}

}
