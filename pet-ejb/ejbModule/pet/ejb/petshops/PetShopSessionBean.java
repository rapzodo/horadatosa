package pet.ejb.petshops;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pet.mongo.db.dao.BaseMongoDao;
import pet.mongo.db.factory.DaoFactory;
import pet.mongo.morphia.entities.PetShop;

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

}
