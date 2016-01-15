package com.pet.ejbs;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.db.factory.DaoFactory;
import com.pet.mongo.morphia.entities.PetShop;

/**
 * Session Bean implementation class AgendamentoBean
 */
@Stateless
@LocalBean
public class AgendamentoBean {

	private BaseMongoDao<PetShop> petDao = new DaoFactory<PetShop>().getDao(PetShop.class);
    /**
     * Default constructor. 
     */
    public AgendamentoBean() {
    }

    public Date getPetshopsWithinDateRange(Date initialDateTime,Date finalDateTime ){
    	
    	return null;
    }
}
