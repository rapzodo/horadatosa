package com.pet.mongo.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.api.morphia.dao.BaseMongoDao;
import com.pet.mongo.db.PetDbConfig;
import com.pet.mongo.morphia.entities.Agendamento;
import com.pet.mongo.morphia.entities.PetShop;
import com.pet.mongo.morphia.entities.Servico;

public class AgendamentoDao extends BaseMongoDao<Agendamento>{

	public AgendamentoDao(){
		super(Agendamento.class, PetDbConfig.getDs(false));
	}
	
	public List<Agendamento> getAppointments(DateTime data, Servico servico,
			PetShop petShop) {
		Map<String, Object> agendamentoParams = new HashMap<String, Object>();
		agendamentoParams .put("petShop", petShop);
		agendamentoParams.put("dataAgendamento", data.toDate());
		agendamentoParams.put("servico", servico);
		List<Agendamento> agendamentos = getByComplexQueryAnd(agendamentoParams);
		return agendamentos;
	}
}
