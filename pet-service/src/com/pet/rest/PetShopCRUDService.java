package com.pet.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;

import com.google.gson.JsonArray;
import com.pet.constants.ServiceConstants;
import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.morphia.entities.PetShop;
import com.pet.mongo.morphia.entities.Servico;
import com.pet.mongo.morphia.entities.Usuario;

@Path("/petshops")
public class PetShopCRUDService extends BaseCrudService<PetShop>{
	
	private BaseMongoDao<Servico> svcDao= new BaseMongoDao<>(Servico.class);
	private BaseMongoDao<Usuario> userDao= new BaseMongoDao<>(Usuario.class);
	
	public PetShopCRUDService(){
		dao = new BaseMongoDao<>(PetShop.class);
	}
	
	@Path("addServico/{_id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addServico(Servico servico,@PathParam("_id")String userId){
		PetShop petshop = (PetShop) dao.getById(userId);
		String msg = ServiceConstants.FAIL_MESSAGE;
		if(petshop != null){
//			IF NEW SERVICO
			if(servico.get_id() == 0){
				petshop.getServicos().add(servico);
			}
			svcDao.saveOrUpdate(servico);
			dao.saveOrUpdate(petshop);
			msg = ServiceConstants.SUCCESS;
		}else{
			msg = ServiceConstants.NOT_FOUND;
		}
		return Response.ok(msg).build();
	}
	
	@Path("addCliente/{_id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUser(Usuario usuario,@PathParam("_id")String userId){
		PetShop petshop = (PetShop) dao.getById(userId);
		String msg = ServiceConstants.FAIL_MESSAGE;
		if(petshop != null){
//			IF NEW
			if(usuario.get_id() == 0){
				petshop.getClientes().add(usuario);
			}
			userDao.saveOrUpdate(usuario);
			dao.saveOrUpdate(petshop);
			msg = ServiceConstants.SUCCESS;
		}else{
			msg = ServiceConstants.NOT_FOUND;
		}
		return Response.ok(msg).build();
	}
	
	
	@Path("diasFuncionamento/{idPetShop}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiasDeFuncionamento(@PathParam("idPetShop")String id){
		PetShop petshop = (PetShop) dao.getById(id);
		JsonArray jsonArray = new JsonArray();
		List<Integer> diasFuncionamento = petshop.getDiasFuncionamento();
		for(int i =0 ; i < diasFuncionamento.size() ; i++) {
			jsonArray.add(new DateTime().withDayOfWeek(diasFuncionamento.get(i)).dayOfWeek().getAsShortText());
		}
		Response resp = Response.ok(jsonArray.toString()).build();
		return resp;
	}
}
