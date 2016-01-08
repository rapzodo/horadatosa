package pet.mongo.db.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import pet.mongo.morphia.db.MorphiaDS;


public class BaseMongoDao<MODEL> {
	
	private Class<?> classe;
	private Datastore ds;
	
	public BaseMongoDao (){
	}
	
	public BaseMongoDao (Class<?> classe){
		this.classe = classe;
		ds = MorphiaDS.getinstance().getDataStore();
	}
	
	@SuppressWarnings("unchecked")
	public List<MODEL> listAll(){
		Query<MODEL> query = (Query<MODEL>) ds.createQuery(classe);
		return query.asList();
	}
}
