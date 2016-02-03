package com.pet.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pet.ejbs.AgendamentoSessionBean;
import com.pet.mongo.db.dao.AgendamentoDao;
import com.pet.mongo.morphia.entities.Agendamento;
import com.pet.mongo.morphia.entities.PetShop;

@Path("/agendamentos")
public class AgendamentosService extends BaseCrudService<Agendamento>{
	
	@EJB
	protected AgendamentoSessionBean bean;
	
	public AgendamentosService(){
		dao = new AgendamentoDao();
	}
	
	@GET
	@Path("petshop/{_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Agendamento> getAgendamentoByPetShop(@PathParam("_id")String id){
		return (List<Agendamento>) bean.getAgendamentosByPetShop(id);
	}
	
	@GET
	@Path("data/{dataSelecionada}/servico/{servicoId}/endereco/{estado}/{cidade}/{bairro}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PetShop> getAgendamentoByServicoEndereco(@PathParam("dataSelecionada")String dataSelecionada,
			@PathParam("servicoId")String servicoId,
			@PathParam("estado")String UF,
			@PathParam("cidade")String cidade,
			@PathParam("bairro")String bairro){
		return (List<PetShop>) bean.getPetShopsDisponiveis(dataSelecionada,servicoId,UF, cidade, bairro);
	}
	
}
