package com.pet.ejbs;

import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.mongodb.morphia.Key;

import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.morphia.entities.DomainSuperClass;

/**
 * Session Bean implementation class PetShopSessionBean
 */
@Stateless
@LocalBean
public class CrudSessionBean{
	
    /**
     * Default constructor. 
     */
    public CrudSessionBean() {
    	
	}
 	
	public List<?> listAll(BaseMongoDao<DomainSuperClass> dao){
		return dao.listAll();
	}
	
	public Key<?> save(BaseMongoDao<DomainSuperClass> dao, DomainSuperClass domain){
		domain.set_id(dao.getCounterSeq());
		return dao.save(domain);
	}
	
	public List<?> getByRegex(BaseMongoDao<DomainSuperClass> dao,String field,String value){
		Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
		return dao.getModelByFilter(field, pattern);
	}
	
	public int delete(BaseMongoDao<DomainSuperClass> dao,String id) {
		return dao.delete(id);
	}

}
