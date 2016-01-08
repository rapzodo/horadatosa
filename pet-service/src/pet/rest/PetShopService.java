package pet.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pet.business.petshops.PetShopSessionBean;
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
}
