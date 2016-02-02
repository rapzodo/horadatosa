package com.pet.ejbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import com.pet.mongo.db.dao.AgendamentoDao;
import com.pet.mongo.db.dao.PetShopDao;
import com.pet.mongo.db.dao.ServicoDao;
import com.pet.mongo.db.dao.ServicosPetShopDao;
import com.pet.mongo.morphia.entities.Agendamento;
import com.pet.mongo.morphia.entities.PetShop;
import com.pet.mongo.morphia.entities.Servico;
import com.pet.mongo.morphia.entities.ServicosPetShops;

/**
 * Session Bean implementation class AgendamentoBean
 */
@Stateless
@LocalBean
public class AgendamentoSessionBean{
	
	private AgendamentoDao dao = new AgendamentoDao();
	private PetShopDao petDao = new PetShopDao();
	private ServicoDao servDao = new ServicoDao();
	private ServicosPetShopDao servPetDao = new ServicosPetShopDao();

    public boolean estaDentroDoHorarioDeFuncionamento(DateTime dataSelecionada, PetShop petshop){
    	String[] horaIni = petshop.getHorarioAbertura().split(":");
    	String[] horaFinal = petshop.getHorarioFechamento().split(":");
    	LocalTime ini = new LocalTime(Integer.valueOf(horaIni[0]), Integer.valueOf(horaIni[1]));
    	LocalTime end = new LocalTime(Integer.valueOf(horaFinal[0]), Integer.valueOf(horaFinal[1]));
    	LocalTime horaSelecionada = new LocalTime(dataSelecionada.getHourOfDay(), dataSelecionada.getMinuteOfHour());
    	return horaSelecionada.isAfter(ini) && horaSelecionada.isBefore(end);
    }
    
    public List<Agendamento> getAgendamentosByPetShop(String petShopId){
    	return dao.getModelByfield("petShop", Long.valueOf(petShopId));
    }
    public List<Agendamento> getAgendamentos(){
    	return dao.listAll();
    }
    
    public List<PetShop> getPetShopsDisponiveis(String dataSelecionada, String servicoId,
    		String UF,String cidade, String bairro){
    	DateTime data = DateTime.parse(dataSelecionada);
    	Servico servico = servDao.getById(servicoId);
    	List<PetShop> petshops = petDao.getPetShopsByEndereco(cidade, bairro, UF);
    	List<PetShop> petshopsDisp = new ArrayList<PetShop>();
    	for (PetShop petShop : petshops) {
			if(checkIfAppointmentExistsAndHasCapacity(data,servico,petShop) && 
					checkIfPetShopOffersService(petShop,servico) 
					&& estaDentroDoHorarioDeFuncionamento(data, petShop)){
				petshopsDisp.add(petShop);
			}
		}
		return petshopsDisp;
    }

	public boolean checkIfPetShopOffersService(PetShop petShop, Servico servico) {
		return servPetDao.getServicosByPetShopAndServico(petShop, servico) != null;
	}

	public Boolean checkIfAppointmentExistsAndHasCapacity(DateTime data,
			Servico servico, PetShop petShop) {
		List<Agendamento> agendamentos = dao.getAppointments(data, servico, petShop);
		if(agendamentos == null){
			return true;
		}else{
			return checkServiceCapacity(petShop, servico, agendamentos.size());
		}
	}

	public Boolean checkServiceCapacity(PetShop petshop, Servico servico, int agendamentos){
		ServicosPetShops servicoPetshop = servPetDao.getServicosByPetShopAndServico(petshop, servico);
		return servicoPetshop.getCapacidade() > agendamentos;
	}
    
    public List<PetShop> getPetShopsDisponiveisByCep(String cep){
    	return petDao.getModelByfield("cep", cep);
    }
    
}
