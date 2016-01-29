package com.pet.ejbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.morphia.entities.Agendamento;
import com.pet.mongo.morphia.entities.PetShop;
import com.pet.mongo.morphia.entities.Servico;

/**
 * Session Bean implementation class AgendamentoBean
 */
@Stateless
@LocalBean
public class AgendamentoSessionBean{
	
	private BaseMongoDao<Agendamento> dao = new BaseMongoDao<Agendamento>(Agendamento.class);
	private BaseMongoDao<PetShop> petDao = new BaseMongoDao<PetShop>(PetShop.class);
	private BaseMongoDao<Servico> servDao = new BaseMongoDao<Servico>(Servico.class);

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
    	Map<String,Object> petShopParams = new HashMap<String, Object>();
    	Servico servico = servDao.getById(servicoId);
    	petShopParams.put("servicos", servico);
    	petShopParams.put("endereco.cidade", cidade);
    	petShopParams.put("endereco.bairro", bairro);
    	petShopParams.put("endereco.UF", UF);
    	List<PetShop> petshops = petDao.getByComplexQueryAnd(petShopParams);
    	List<PetShop> petshopsDisp = new ArrayList<PetShop>();
    	Map<String,Object> agendamentoParams = new HashMap<String, Object>();
    	for (PetShop petShop : petshops) {
			agendamentoParams.put("petShop", petShop.get_id());
			agendamentoParams.put("dataAgendamento", data.toDate());
			List<Agendamento> agendamentos = dao.getByComplexQueryAnd(agendamentoParams);
			if(agendamentos != null && agendamentos.size() == 0){
				petshopsDisp.add(petShop);
			}
		}
		return petshopsDisp;
    }
    public List<PetShop> getPetShopsDisponiveisByCep(String cep){
    	return petDao.getModelByfield("cep", cep);
    }
    
}
