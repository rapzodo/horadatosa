package pet.mongo.db.factory;

import pet.mongo.db.dao.BaseMongoDao;

public class DaoFactory<T> {

	public BaseMongoDao<T> getDao(Class<?> classe){
		return new BaseMongoDao<T>(classe);
	}
}
