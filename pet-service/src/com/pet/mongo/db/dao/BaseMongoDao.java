package com.pet.mongo.db.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;

import com.pet.mongo.morphia.db.MorphiaDS;


public class BaseMongoDao<MODEL> {
	
	private Class<?> classe;
	private Datastore ds;
	
	public BaseMongoDao (){
	}
	
	public BaseMongoDao (Class<?> classe){
		this.classe = classe;
		ds = MorphiaDS.getinstance().getDataStore();
	}

	public Key<MODEL> save(MODEL model){
		return ds.save(model);
	}
	
	@SuppressWarnings("unchecked")
	public MODEL getById(String id){
		return (MODEL) ds.createQuery(classe).field("_id").equal(id).get();
	}
	
	@SuppressWarnings("unchecked")
	public List<MODEL> listAll(){
		Query<MODEL> query = (Query<MODEL>) ds.createQuery(classe);
		return query.asList();
	}
	
	
	public List<MODEL> getModelByQuery(Query<MODEL> query){
		return (List<MODEL>) query.asList();
	}
	
	@SuppressWarnings("unchecked")
	public List<MODEL> getModelByfieldLessThanOrEqual(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).lessThanOrEq(value).asList();
	}
	
	@SuppressWarnings("unchecked")
	public List<MODEL> getModelByfieldLessThan(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).lessThan(value).asList();
	}
	
	@SuppressWarnings("unchecked")
	public List<MODEL> getModelByfieldGreaterThanOrEqual(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).greaterThanOrEq(value).asList();
	}
	
	@SuppressWarnings("unchecked")
	public List<MODEL> getModelByfieldGreaterThan(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).greaterThan(value).asList();
	}
	
	@SuppressWarnings("unchecked")
	public List<MODEL> getModelByfield(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).endsWithIgnoreCase(value).asList();
	}
	@SuppressWarnings("unchecked")
	public List<MODEL> getModelByFilter(String filter,Object value){
		return (List<MODEL>) ds.createQuery(classe).filter(filter, value).asList();
	}
	
	public int delete(MODEL model){
		return ds.delete(model).getN();
	}
	
}
