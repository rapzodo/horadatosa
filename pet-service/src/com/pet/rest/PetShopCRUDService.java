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
import com.pet.ejbs.CrudSessionBean;
import com.pet.ejbs.PetShopSessionBean;
import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.db.factory.DaoFactory;
import com.pet.mongo.morphia.entities.DomainSuperClass;
import com.pet.mongo.morphia.entities.PetShop;

@SuppressWarnings("unchecked")
@Path("/petshops")
public class PetShopCRUDService{
	
	@EJB
	protected CrudSessionBean bean;
	private BaseMongoDao<DomainSuperClass> dao= new DaoFactory<DomainSuperClass>().getDao(PetShop.class);
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PetShop> getAll(){
		return (List<PetShop>) bean.listAll(dao);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regex/{field}/{regex}")
	public List<PetShop> getByLike(@PathParam("field")String field, @PathParam("regex")String regex){
		return (List<PetShop>) bean.getByRegex(dao,field, regex);
	}
	
	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String save(PetShop petshop){
		Key<PetShop> key = (Key<PetShop>) bean.save(dao,petshop);
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
		if(bean.delete(dao,id) > 0){
			message = DBConstants.SUCCESS_DELETED;
		}
		return Response.ok(message).build();
	}
	
}
