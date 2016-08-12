package com.pet.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.api.morphia.dao.BaseMongoDao;
import com.pet.constants.ServiceConstants;
import com.pet.mongo.db.dao.AnimalDao;
import com.pet.mongo.db.dao.UsuarioDao;
import com.pet.mongo.morphia.entities.Animal;
import com.pet.mongo.morphia.entities.Usuario;

@Path("/usuarios")
public class UsuarioCRUDService extends BaseCrudService<Usuario>{
	
	private BaseMongoDao<Animal> animalDao= new AnimalDao();
	
	public UsuarioCRUDService(){
		dao = new UsuarioDao();
	}
	
	@Path("addPet/{_id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Animal animal,@PathParam("_id")String userId){
		Usuario usuario = (Usuario) dao.getById(new Long(userId));
		String msg = ServiceConstants.FAIL_MESSAGE;
		if(usuario != null){
//			IF NEW ANIMAL
			if(animal.get_id() == 0){
				usuario.getPets().add(animal);
			}
			animalDao.saveOrUpdate(animal);
			dao.saveOrUpdate(usuario);
			msg = ServiceConstants.SUCCESS;
		}else{
			msg = ServiceConstants.NOT_FOUND;
		}
		return Response.ok(msg).build();
	}
	
}
