package com.pet.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.api.morphia.dao.BaseMongoDao;
import com.pet.constants.DBConstants;

public class BaseCrudService<MODEL>{
	
	protected BaseMongoDao<MODEL> dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MODEL> getAll(){
		return dao.listAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regex/{field}/{regex}")
	public List<MODEL> getByRegex(@PathParam("field")String field, @PathParam("regex")String regex){
		return (List<MODEL>) dao.getByRegex(field, regex);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public MODEL getById(@PathParam("id") String id){
		return (MODEL) dao.getById(new Long(id));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("onlyFields/{id}")
	public MODEL getByIdWithSelectedFields(@PathParam("id") String id){
		return (MODEL) dao.getById(new Long(id));
	}
	
	@Path("createUpdate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(MODEL model){
		return Response.ok(dao.saveOrUpdate(model)).build();
	}
	
	@Path("remove/{id}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam("id") String id){
		String message = DBConstants.FAIL_MESSAGE;
		if(dao.delete(new Long(id)) > 0){
			message = DBConstants.SUCCESS_DELETED;
		}
		return Response.ok(message).build();
	}
	
	@GET
	@Path("campos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MODEL> getAgendamentosField(@QueryParam("q")String fields){
		return (List<MODEL>) dao.getAllOnlyFields(true,fields);
	}
	
}
