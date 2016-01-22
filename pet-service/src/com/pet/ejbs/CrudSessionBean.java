package com.pet.ejbs;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.pet.constants.QueriesConstants;
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
			domain.setDateCadastro(new Date());
		}
		return dao.save(domain);
	}
	
	public List<?> getByRegex(BaseMongoDao<DomainSuperClass> dao,String field,String value){
		Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
		return dao.getModelByFilter(field, pattern);
	}
	
	public List<?> getByField(BaseMongoDao<DomainSuperClass> dao,String field,Object value){
		return dao.getModelByfield(field, value);
	}
	
	public List<?> getGetByManyFields(BaseMongoDao<DomainSuperClass> dao,Map<String, Object> keyValuePairs, Class<?> classe){
		Query<?> query = dao.getDs().createQuery(classe);
		for(String key : keyValuePairs.keySet()){
			query.field(key).equal(keyValuePairs.get(key));
		}
		return query.asList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int updateByFields(BaseMongoDao<DomainSuperClass> dao,Map<String, Object> keyValuePairs,Class<?> classe){
		Datastore ds = dao.getDs();
		Query query = (Query) ds.createQuery(classe).field(QueriesConstants.ID_FIELD);
		UpdateOperations update = ds.createUpdateOperations(classe);
		for(String key : keyValuePairs.keySet()){
			update.set(key,keyValuePairs.get(key));
		}
		return ds.update(query, update).getUpdatedCount();
	}
	
	public int delete(BaseMongoDao<DomainSuperClass> dao,String id) {
		return dao.delete(id);
	}
	
	public DomainSuperClass getById(BaseMongoDao<DomainSuperClass> dao, String _id){
		return dao.getById(_id);
	}

}
