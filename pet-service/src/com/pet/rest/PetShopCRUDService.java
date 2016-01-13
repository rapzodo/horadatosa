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

import org.mongodb.morphia.Key;

import com.pet.constants.DBConstants;
import com.pet.ejbs.PetShopSessionBean;
import com.pet.mongo.morphia.entities.PetShop;

@Path("/petshops")
public class PetShopCRUDService{
	
	@EJB
	protected PetShopSessionBean petBean;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PetShop> getAll(){
		return petBean.listAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regex/{field}/{regex}")
	public List<PetShop> getByLike(@PathParam("field")String field, @PathParam("regex")String regex){
		return petBean.getByRegex(field, regex);
	}
	
	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String save(PetShop petshop){
		Key<PetShop> key = petBean.save(petshop);
		if(key != null){
			return key.getId().toString();
		}
		return DBConstants.FAIL_MESSAGE;
	}
	
	@Path("remove/{id}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam("id") String id){
		String message = DBConstants.FAIL_MESSAGE;
		if(petBean.delete(id) > 0){
			message = DBConstants.SUCCESS_DELETED;
		}
		return Response.ok(message).build();
	}
	
}
