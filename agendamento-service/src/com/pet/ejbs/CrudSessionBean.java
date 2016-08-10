package com.pet.ejbs;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.api.morphia.dao.BaseMongoDao;
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
	
	public long saveOrUpdate(BaseMongoDao<DomainSuperClass> dao, DomainSuperClass domain){
		return dao.saveOrUpdate(domain);
	}
	
	public List<?> getByRegex(BaseMongoDao<DomainSuperClass> dao,String field,String value){
		Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
		return dao.getModelByFilter(field, pattern);
	}
	
	public List<?> getByField(BaseMongoDao<DomainSuperClass> dao,String field,Object value){
		return dao.getModelByfield(field, value);
	}
	
	public List<?> getGetByManyFields(BaseMongoDao<DomainSuperClass> dao,Map<String, Object> keyValuePairs){
		return dao.getByComplexQueryAnd(keyValuePairs);
	}
	
	public int delete(BaseMongoDao<DomainSuperClass> dao,String id) {
		return dao.delete(new Long(id));
	}
	
	public DomainSuperClass getById(BaseMongoDao<DomainSuperClass> dao, String _id){
		return dao.getById(new Long(_id));
	}

}
