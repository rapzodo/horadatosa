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
import com.pet.ejbs.CrudSessionBean;
import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.db.factory.DaoFactory;
import com.pet.mongo.morphia.entities.DomainSuperClass;
import com.pet.mongo.morphia.entities.Servico;

@SuppressWarnings("unchecked")
@Path("/servicos")
public class ServicoCRUDService{
	
	@EJB
	protected CrudSessionBean bean;
	private BaseMongoDao<DomainSuperClass> dao= new DaoFactory<DomainSuperClass>().getDao(Servico.class);
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Servico> getAll(){
		return (List<Servico>) bean.listAll(dao);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regex/{field}/{regex}")
	public List<Servico> getByRegex(@PathParam("field")String field, @PathParam("regex")String regex){
		return (List<Servico>) bean.getByRegex(dao,field, regex);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Servico getById(@PathParam("id") String id){
		return (Servico) bean.getById(dao,id);
	}
	
	@Path("createUpdate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Servico Servico){
		return Response.ok(bean.saveOrUpdate(dao,Servico)).build();
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
