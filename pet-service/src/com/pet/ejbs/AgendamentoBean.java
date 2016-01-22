package com.pet.ejbs;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.mongodb.morphia.query.Query;

import com.pet.constants.QueriesConstants;
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
    
    public boolean estaDentroDoHorarioDeFuncionamento(Date dataDesejada, PetShop petshop){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(dataDesejada);
    	cal.get(Calendar.DAY_OF_WEEK);
    	
    	return false;
    }
    
    public List<PetShop> getPetShopsComAgendamentoDisponivel(String servicoId, Date dataDesejada){
    	Query<PetShop> query = petDao.getDs().createQuery(PetShop.class);
    	query.field("servicos." + QueriesConstants.ID_FIELD).equal(servicoId)
    	.field("agendamentos.dataAgendamento").notEqual(dataDesejada).asList();
    	
    	return null;
    }
}
