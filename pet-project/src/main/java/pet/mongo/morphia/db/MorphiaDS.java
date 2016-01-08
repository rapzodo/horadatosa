package pet.mongo.morphia.db;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import pet.constants.DBConstants;

import com.mongodb.MongoClient;

public class MorphiaDS {
	private Morphia morphia = new Morphia();
	private Datastore ds;
	private static MorphiaDS instance;
	
	private MorphiaDS(){
		config();
	}
	
	public void config(){
		System.out.println("Starting Morphia");
		morphia.mapPackage(DBConstants.ENTITIES_PKG);
		ds = morphia.createDatastore(new MongoClient(), DBConstants.DB_NAME);
		ds.ensureIndexes();
	}
	
	public static MorphiaDS getinstance(){
		if(instance == null){
			instance = new MorphiaDS();
		}
		return instance;
	}
	public Datastore getDataStore(){
		return ds;
	}
}
