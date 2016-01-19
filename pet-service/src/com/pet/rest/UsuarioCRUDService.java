package com.pet.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pet.constants.DBConstants;
import com.pet.constants.ServiceConstants;
import com.pet.ejbs.CrudSessionBean;
import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.db.factory.DaoFactory;
import com.pet.mongo.morphia.entities.Animal;
import com.pet.mongo.morphia.entities.DomainSuperClass;
import com.pet.mongo.morphia.entities.Usuario;

@SuppressWarnings("unchecked")
@Path("/usuarios")
public class UsuarioCRUDService{
	
	@EJB
	protected CrudSessionBean bean;
	private BaseMongoDao<DomainSuperClass> dao= new DaoFactory<DomainSuperClass>().getDao(Usuario.class);
	private BaseMongoDao<DomainSuperClass> animalDao= new DaoFactory<DomainSuperClass>().getDao(Animal.class);
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getAll(){
		return (List<Usuario>) bean.listAll(dao);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regex/{field}/{regex}")
	public List<Usuario> getByRegex(@PathParam("field")String field, @PathParam("regex")String regex){
		return (List<Usuario>) bean.getByRegex(dao,field, regex);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Usuario getById(@PathParam("id") String id){
		return (Usuario) bean.getById(dao,id);
	}
	
	@Path("createUpdate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Usuario Usuario){
		return Response.ok(bean.saveOrUpdate(dao,Usuario)).build();
	}
	
	@Path("addPet/{_id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Animal animal,@PathParam("_id")String userId){
		Usuario usuario = (Usuario) dao.getById(userId);
		String msg = ServiceConstants.FAIL_MESSAGE;
		if(usuario != null){
//			IF NEW ANIMAL
			if(animal.get_id() == 0){
				usuario.getPets().add(animal);
			}
			bean.saveOrUpdate(animalDao,animal);
			bean.saveOrUpdate(dao,usuario);
			msg = ServiceConstants.SUCCESS;
		}else{
			msg = ServiceConstants.USER_NO_FOUND;
		}
		return Response.ok(msg).build();
	}
	
	@Path("remove/{id}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam("id") String id){
		String message = DBConstants.FAIL_MESSAGE;
		if(bean.delete(dao,id) > 0){
			message = DBConstants.SUCCESS_DELETED;
		}
		return Response.ok(message).build();
	}
	
}
