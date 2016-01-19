package com.pet.ejbs;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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
	
	public long saveOrUpdate(BaseMongoDao<DomainSuperClass> dao, DomainSuperClass domain){
		String id = String.valueOf(domain.get_id());
		if(dao.getById(id) == null){
			domain.set_id(dao.getCounterSeq());
		}
		return dao.save(domain);
	}
	
	public List<?> getByRegex(BaseMongoDao<DomainSuperClass> dao,String field,String value){
		Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
		return dao.getModelByFilter(field, pattern);
	}
	
	public int delete(BaseMongoDao<DomainSuperClass> dao,String id) {
		return dao.delete(id);
	}

	
	public DomainSuperClass getById(BaseMongoDao<DomainSuperClass> dao, String _id){
		return dao.getById(_id);
	}

	public void getPetShopsComHorario(BaseMongoDao<DomainSuperClass> dao, String serviceId, Date initDt,
			Date finalDt) {
		dao.getByDateRange(initDt, finalDt);
	}
}
