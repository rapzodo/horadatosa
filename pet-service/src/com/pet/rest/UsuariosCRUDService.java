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

import org.mongodb.morphia.Key;

import com.pet.constants.DBConstants;
import com.pet.ejbs.UsuarioSessionBean;
import com.pet.mongo.morphia.entities.Usuario;

@Path("/usuarios")
public class UsuariosCRUDService{
	
	@EJB
	protected UsuarioSessionBean uBean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getAll(){
		return uBean.listAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("regex/{field}/{regex}")
	public List<Usuario> getByLike(@PathParam("field")String field, @PathParam("regex")String regex){
		return uBean.getByRegex(field, regex);
	}
	
	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String save(Usuario usuario){
		Key<Usuario> key = uBean.save(usuario);
		if(key != null){
			return key.getId().toString();
		}
		return DBConstants.FAIL_MESSAGE;
	}
	
}
