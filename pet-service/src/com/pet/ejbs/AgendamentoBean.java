package com.pet.ejbs;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.db.factory.DaoFactory;
import com.pet.mongo.morphia.entities.Agendamento;
import com.pet.mongo.morphia.entities.PetShop;

/**
 * Session Bean implementation class AgendamentoBean
 */
@Stateless
@LocalBean
public class AgendamentoBean {

	private BaseMongoDao<PetShop> petDao = new DaoFactory<PetShop>().getDao(PetShop.class);
	private BaseMongoDao<Agendamento> agenDao = new DaoFactory<Agendamento>().getDao(Agendamento.class);
    /**
     * Default constructor. 
     */
    public AgendamentoBean() {
    }

    public List<Agendamento> getAllAgendamentos(){
    	return agenDao.listAll();
    }
    public List<PetShop> getPetshopsWithinDateRange(Date initialDateTime,Date finalDateTime ){
    	return petDao.getByDateRange(initialDateTime, finalDateTime);
    }
}
