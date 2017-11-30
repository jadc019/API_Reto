package pe.com.bbva.reto.daos;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.bbva.reto.APIConstants;

public abstract class AbstractDao<ID extends Serializable, E> {
	
	private final Class<E> persistentClass;
	
	@PersistenceContext(unitName=APIConstants.PU_RETOBBVA)
	protected EntityManager entityManager; 
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	protected EntityManager getEntityManager(){
		return this.entityManager;
	}

	public E findById(ID id) {
		return (E) entityManager.find(persistentClass, id);
	}

	public void persist(E entity) {
		entityManager.persist(entity);
	}
	
	public E update(E entity) {
		return entityManager.merge(entity);
	}

	public void delete(E entity) {
		entityManager.remove(entity);
	}
	
	public E delete(ID id) {
		E entity = this.findById(id);
		entityManager.remove(entity);
		return entity;
	}
	
	public List<E> findAll(){
		String namedQuery = String.format("%s.findAll", this.persistentClass.getSimpleName());
		TypedQuery<E> query = entityManager.createNamedQuery(namedQuery, this.persistentClass);		
		return query.getResultList();
	}

}
