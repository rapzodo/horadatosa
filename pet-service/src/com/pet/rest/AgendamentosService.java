package com.pet.rest;

import java.util.Calendar;
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
import com.pet.mongo.morphia.entities.Agendamento;
import com.pet.mongo.morphia.entities.DomainSuperClass;
import com.pet.mongo.morphia.entities.PetShop;

@SuppressWarnings("unchecked")
@Path("/agendamentos")
public class AgendamentosService{
	
	@EJB
	protected CrudSessionBean bean;
	private BaseMongoDao<DomainSuperClass> dao= new DaoFactory<DomainSuperClass>().getDao(Agendamento.class);
	private BaseMongoDao<DomainSuperClass> petDao= new DaoFactory<DomainSuperClass>().getDao(PetShop.class);
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Agendamento> getAll(){
		return (List<Agendamento>) bean.listAll(dao);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regex/{field}/{regex}")
	public List<Agendamento> getByRegex(@PathParam("field")String field, @PathParam("regex")String regex){
		return (List<Agendamento>) bean.getByRegex(dao,field, regex);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Agendamento getById(@PathParam("id") String id){
		return (Agendamento) bean.getById(dao,id);
	}
	
	@Path("createUpdate")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response save(Agendamento Agendamento){
		return Response.ok(bean.saveOrUpdate(dao,Agendamento)).build();
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
	
	@Path("horarios/{serviceId}/{initDateTime}/{finalDateTime}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PetShop> getPetShopsComHorario(@PathParam("serviceId")String serviceId,
			@PathParam("initDateTime")String initDateTime, 
			@PathParam("finalDateTime") String finalDateTime){
		Calendar initDt = Calendar.getInstance();
		Calendar finalDt = Calendar.getInstance();
		bean.getPetShopsComHorario(dao,serviceId, initDt.getTime(), finalDt.getTime());
		return null;
	}
	@Path("horarios/{serviceId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<PetShop> testeData(@PathParam("serviceId")String serviceId,
			String rangeDate){
		System.out.println(rangeDate);
		Calendar initDt = Calendar.getInstance();
		Calendar finalDt = Calendar.getInstance();
		bean.getPetShopsComHorario(petDao,serviceId, initDt.getTime(), finalDt.getTime());
		return null;
	}
	
	
	
}
