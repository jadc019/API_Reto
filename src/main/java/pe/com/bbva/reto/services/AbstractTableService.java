package pe.com.bbva.reto.services;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pe.com.bbva.reto.APIConstants;
import pe.com.bbva.reto.daos.GenericInterfaceDao;
import pe.com.bbva.reto.exceptions.NotFoundException;
import pe.com.bbva.reto.spring.utils.BeanUtilsNotNull;

public class AbstractTableService<ID extends Serializable, E> {
	
	protected GenericInterfaceDao<ID, E> entityDao;

	public AbstractTableService(GenericInterfaceDao<ID, E> entityDao){
		this.entityDao = entityDao;
	}
	
	@Transactional(readOnly=true)
	public List<E> findAll() throws Exception{
		return entityDao.findAll();
	}
	
	@Transactional(readOnly=true)
	public E findById(ID id) throws Exception{
		return entityDao.findById(id);
	}
	
	@Transactional
	public E save(E entity) throws Exception{
		try {
			if(entity.getClass().getMethod("getCreatedAt").invoke(entity) == null)
				entity.getClass().getMethod("setCreatedAt", Date.class).invoke(entity, new Date());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		}
		
		entityDao.persist(entity);
		
		return entity;
	}
	
	@Transactional
	public E update(E entity) throws Exception{
		try {
			if(APIConstants.IND_SI.equals( invokeMethodGet(entity, "getPartialUpdate") ))
				entity = partialUpdate(entity);
			
			if(invokeMethodGet(entity, "getCreatedAt") == null)
				entity.getClass().getMethod("setCreatedAt", Date.class).invoke(entity, new Date());
			if(invokeMethodGet(entity, "getUpdatedAt") == null)
				entity.getClass().getMethod("setUpdatedAt", Date.class).invoke(entity, new Date());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		}
		
		entityDao.update(entity);
		
		return entity;
	}
	
	@Transactional
	public E patch(E entity) throws Exception{
		try {
			entity = partialUpdate(entity);
			
			if(invokeMethodGet(entity, "getCreatedAt") == null)
				entity.getClass().getMethod("setCreatedAt", Date.class).invoke(entity, new Date());
			if(invokeMethodGet(entity, "getUpdatedAt") == null)
				entity.getClass().getMethod("setUpdatedAt", Date.class).invoke(entity, new Date());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		}
		
		entityDao.update(entity);
		
		return entity;
	}
	
	@Transactional
	public E delete(ID id) throws Exception{
		return entityDao.delete(id);
	}
	
	@SuppressWarnings("unchecked")
	private E partialUpdate(E entity) throws Exception{
		E entityActual = findById( (ID) invokeMethodGet(entity, getMethodGet(entityDao.getNameID()) ));
		if(entityActual != null){
			BeanUtilsNotNull.copyPropertiesNotNull(entity, entityActual, entityDao.getNameID(), "partialUpdate", "version");
		} else {
			throw new NotFoundException("No se encontró registro en BD");
		}
		
		return entityActual;
	}
	
	private Object invokeMethodGet(E entity, String method){
		try {
			return entity.getClass().getMethod(method).invoke(entity);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			return null;
		}
	}
	
	public String getMethodGet(String property){
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		return "get".concat(upper);
	}
	
	public String getMethodSet(String property){
		String upper = property.substring(0, 1).toUpperCase() + property.substring(1);
		return "set".concat(upper);
	}

	public String getIdProperty(){
		return entityDao.getNameID();
	}
}
