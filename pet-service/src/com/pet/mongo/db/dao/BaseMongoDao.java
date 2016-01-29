package com.pet.mongo.db.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.pet.constants.QueriesConstants;
import com.pet.constants.ServiceConstants;
import com.pet.mongo.morphia.db.MorphiaDS;
import com.pet.mongo.morphia.entities.DomainSuperClass;
import com.pet.mongo.morphia.entities.Sequence;


@SuppressWarnings("unchecked")
public class BaseMongoDao<MODEL> {
	
	private Class<?> classe;
	private Datastore ds;
	
	
	public BaseMongoDao (){
	}
	
	public BaseMongoDao (Class<?> classe){
		this.classe = classe;
		ds = MorphiaDS.getinstance().getDataStore();
	}

	public Datastore getDs() {
		return ds;
	}

	public void setDs(Datastore ds) {
		this.ds = ds;
	}
	
	public long save(MODEL model){
		return (long) ds.save(model).getId();
	}

	public long getCounterSeq() {
		UpdateOperations<Sequence> inc = ds.createUpdateOperations(Sequence.class).inc("counter", 1);
		Query<Sequence> query = ds.createQuery(Sequence.class).field(QueriesConstants.ID_FIELD).
				equal(classe.getSimpleName().toLowerCase()+QueriesConstants.SEQUENCE_SUFIX);
		Sequence counter = ds.findAndModify(query, inc, true);
		return counter.getCounter();
	}
	
	public MODEL getById(String id){
		long _id = Long.valueOf(id);
		return (MODEL) ds.createQuery(classe).field("_id").equal(_id).get();
	}
	
	public List<MODEL> listAll(){
		Query<MODEL> query = (Query<MODEL>) ds.createQuery(classe);
		return query.asList();
	}
	
	public List<MODEL> getByRegex(String field, String value){
		Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
		return getModelByFilter(field, pattern);
	}
	
	
	public List<MODEL> getModelByQuery(Query<MODEL> query){
		return (List<MODEL>) query.asList();
	}
	
	public List<MODEL> getModelByfieldLessThanOrEqual(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).lessThanOrEq(value).asList();
	}
	
	public List<MODEL> getModelByfieldLessThan(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).lessThan(value).asList();
	}
	
	public List<MODEL> getModelByfieldGreaterThanOrEqual(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).greaterThanOrEq(value).asList();
	}
	
	public List<MODEL> getModelByfieldGreaterThan(String fieldName,String value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).greaterThan(value).asList();
	}
	
	public List<MODEL> getModelByfield(String fieldName,Object value){
		return (List<MODEL>) ds.createQuery(classe).field(fieldName).equal(value).asList();
	}
	public List<MODEL> getModelByFilter(String filter,Object value){
		return (List<MODEL>) ds.createQuery(classe).filter(filter, value).asList();
	}
	
	public int delete(String id){
		MODEL model = getById(id);
		return ds.delete(model).getN();
	}

	public long saveOrUpdate(MODEL model) {
		if(model instanceof DomainSuperClass){
			
			DomainSuperClass domain = (DomainSuperClass) model;
			String id = String.valueOf(domain.get_id());
			if(getById(id) == null ){
				domain.set_id(getCounterSeq());
				domain.setDateCadastro(new Date());
			}
			return save((MODEL)domain);
		}
		return -1;
	}
	
	public List<MODEL> getByComplexQueryAnd(Map<String, Object> fieldValuePairs){
		Query<?> query = ds.createQuery(classe);
		for(String key : fieldValuePairs.keySet()){
			query.field(key).equal(fieldValuePairs.get(key));
		}
		System.out.println(query);
		return (List<MODEL>) query.asList();
	}
	
	public List<MODEL> getByDateRange(String DateFieldName, Date inDt, Date endDt){
		return (List<MODEL>) ds.createQuery(classe)
				.field(DateFieldName).greaterThanOrEq(inDt)
				.field(DateFieldName).lessThanOrEq(endDt).asList();
	}
	
	public List<MODEL> getAllOnlyFields(String fieldsQuery){
	    	String[] fields = fieldsQuery.split(ServiceConstants.QUERY_SEPARATOR);
	    	Query<?> query = ds.createQuery(classe);
	    	if(fields.length > 0){
	    		query.retrievedFields(true, fields);
	    	}else
	    		query.retrievedFields(true, fieldsQuery);
		return (List<MODEL>) query.asList();
	}
	
	
}
