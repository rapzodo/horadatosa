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
public class CrudSessionBean{
	
	private BaseMongoDao<PetShop> dao = new DaoFactory<PetShop>().getDao(PetShop.class);
	@EJB
	private UsuarioSessionBean userBean;
    /**
     * Default constructor. 
     */
    public CrudSessionBean() {
    	
	}
 	
	public List<?> listAll(BaseMongoDao<?> dao){
		return dao.listAll();
	}
	
	public Key<?> save(BaseMongoDao<?> dao, Object object){
		petshop.set_id(dao.getCounterSeq());
		return dao.save(petshop);
	}
	
	public Key<PetShop> saveClientesPetshop(Usuario usuario, PetShop petshop){
		Key<Usuario> result = userBean.save(usuario);
		if(result != null){
			petshop.getClientes().add(usuario);
		}
		return dao.save(petshop);
	}

	public List<PetShop> getByCpfCnpj(String cpfCnpj){
		return dao.getModelByfield(QueriesConstants.CNPJ_CPF_FIELD, cpfCnpj);
	}
	
	public List<PetShop> getByRazaoSocial(String razaoSocial){
		return dao.getModelByfield(QueriesConstants.RAZAO_SOCIAL_FIELD, razaoSocial);
	}
	public List<PetShop> getByRegex(String field,String value){
		Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
		return dao.getModelByFilter(field, pattern);
	}
	
	@Override
	public PetShop getById(String id) {
		return dao.getById(id);
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

}
