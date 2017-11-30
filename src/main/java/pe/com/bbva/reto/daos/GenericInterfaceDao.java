package pe.com.bbva.reto.daos;

import java.io.Serializable;
import java.util.List;

public interface GenericInterfaceDao<ID extends Serializable, E> {
	
	public String getNameID();

	public E findById(ID id);

	public void persist(E entity);
	
	public E update(E entity);

	public void delete(E entity);
	
	public E delete(ID id);
	
	public List<E> findAll();
}
