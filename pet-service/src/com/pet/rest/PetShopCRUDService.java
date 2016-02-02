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
import com.pet.mongo.db.dao.PetShopDao;
import com.pet.mongo.db.dao.ServicosPetShopDao;
import com.pet.mongo.db.dao.UsuarioDao;
import com.pet.mongo.morphia.entities.PetShop;
import com.pet.mongo.morphia.entities.ServicosPetShops;
import com.pet.mongo.morphia.entities.Usuario;

@Path("/petshops")
public class PetShopCRUDService extends BaseCrudService<PetShop>{
	
	private UsuarioDao userDao= new UsuarioDao();
	private ServicosPetShopDao servPetDao = new ServicosPetShopDao();
	
	public PetShopCRUDService(){
		dao = new PetShopDao();
	}
	
	@Path("addServico")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addServico(ServicosPetShops servPet){
		long id = servPetDao.saveOrUpdate(servPet);
		return Response.ok(id).build();
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
	
	@Path("servicos/{idPetShop}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ServicosPetShops> getServicos(@PathParam("idPetShop")String id){
		PetShop petshop = dao.getById(id);
		return servPetDao.getServicos(petshop);
	}
	
	@GET
	@Path("cep/{cep}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PetShop> getPetshopsByCep(@PathParam("cep")String cep){
		return dao.getModelByfield("endereco.cep",cep);
	}
}
