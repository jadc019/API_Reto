package pe.com.bbva.reto.services;

import java.io.Serializable;
import java.util.List;

public interface GenericTableService<ID extends Serializable, E> {

	List<E> findAll() throws Exception;

	E findById(ID id) throws Exception;

	E save(E entity) throws Exception;

	E update(E entity) throws Exception;
	
	E patch(E entity) throws Exception;

	E delete(ID id) throws Exception;
	
	String getMethodGet(String property);
	
	String getMethodSet(String property);

	String getIdProperty();
	
}
