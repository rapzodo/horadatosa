package pet.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pet.ejb.petshops.PetShopSessionBean;
import pet.mongo.morphia.entities.PetShop;

@Path("/petshops")
public class PetShopService {

	@EJB
	private PetShopSessionBean sBean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PetShop> getAll(){
		return sBean.getAllPetshops();
	}
	
	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String save(PetShop petshop){
		return sBean.save(petshop).getId().toString();
	}
	
	@Path("remove")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int delete(PetShop petshop){
		return sBean.delete(petshop);
	}
	
}
