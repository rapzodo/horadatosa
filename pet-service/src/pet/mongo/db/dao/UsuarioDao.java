package pet.mongo.db.dao;

import pet.mongo.morphia.entities.Usuario;

public class UsuarioDao extends BaseMongoDao<Usuario> {

	public UsuarioDao (){
		super(Usuario.class);
	}
}
