package com.pet.ejbs;

import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.mongodb.morphia.Key;

import com.pet.constants.QueriesConstants;
import com.pet.mongo.db.dao.BaseMongoDao;
import com.pet.mongo.db.factory.DaoFactory;
import com.pet.mongo.morphia.entities.PetShop;
import com.pet.mongo.morphia.entities.Usuario;

/**
 * Session Bean implementation class PetShopSessionBean
 */
@Stateless
@LocalBean
public class PetShopSessionBean {
	private BaseMongoDao<PetShop> petDao = new DaoFactory<PetShop>().getDao(PetShop.class);
	@EJB
	private UsuarioSessionBean userBean;
    /**
     * Default constructor. 
     */
    public PetShopSessionBean() {
    	
	}
 	
	public List<PetShop> listAll(){
		return petDao.listAll();
	}
	
	public long save(PetShop petshop){
		petshop.set_id(petDao.getCounterSeq());
		return petDao.save(petshop);
	}
	
	public long saveClientesPetshop(Usuario usuario, PetShop petshop){
		Key<Usuario> result = userBean.save(usuario);
		if(result != null){
			petshop.getClientes().add(usuario);
		}
		return petDao.save(petshop);
	}

	public List<PetShop> getByCpfCnpj(String cpfCnpj){
		return petDao.getModelByfield(QueriesConstants.CNPJ_CPF_FIELD, cpfCnpj);
	}
	
	public List<PetShop> getByRazaoSocial(String razaoSocial){
		return petDao.getModelByfield(QueriesConstants.RAZAO_SOCIAL_FIELD, razaoSocial);
	}
	public List<PetShop> getByRegex(String field,String value){
		Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
		return petDao.getModelByFilter(field, pattern);
	}
	
	public PetShop getById(String id) {
		return petDao.getById(id);
	}

	public int delete(String id) {
		return petDao.delete(id);
	}

}
