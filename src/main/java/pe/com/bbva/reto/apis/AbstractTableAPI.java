package pe.com.bbva.reto.apis;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import pe.com.bbva.reto.exceptions.NotFoundException;
import pe.com.bbva.reto.jersey.methods.PATCH;
import pe.com.bbva.reto.services.GenericTableService;

public class AbstractTableAPI<ID extends Serializable, E> {

	private final Class<E> entityClass;
	protected GenericTableService<ID, E> tableService;
	
	@SuppressWarnings("unchecked")
	public AbstractTableAPI(){
		this.entityClass =(Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@SuppressWarnings("unchecked")
	public AbstractTableAPI(GenericTableService<ID, E> tableService){
		this.entityClass =(Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		this.tableService = tableService;
	}
	
	@GET
	public Response findAll(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) throws Exception{
		List<E> list = tableService.findAll();
		return Response.ok(list).build();
	}
	
	@Path("/{id}")
	@GET
	public Response findById(@PathParam("id") ID id) throws Exception{
		E entity = tableService.findById(id);
		if(entity == null)
			throw new NotFoundException("No se encontro registro");
		
		return Response.ok(entity).build();
	}
	
	@POST
	public Response save(E entity) throws Exception{
		tableService.save(entity);
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}
	
	@Path("/{id}")
	@PUT
	public Response update(@PathParam("id") ID id, E entity) throws Exception{
		setValueIdProperty(id, entity);
		E newEntity = tableService.update(entity);
		return Response.ok(newEntity).build();
	}
	
	@Path("/{id}")
	@PATCH
	public Response patch(@PathParam("id") ID id, E entity) throws Exception{
		setValueIdProperty(id, entity);
		E newEntity = tableService.patch(entity);
		return Response.ok(newEntity).build();
	}
	
	@Path("/{id}")
	@DELETE
	public Response delete(@PathParam("id") ID id) throws Exception{
		E entity = tableService.delete(id);
		if(entity == null)
			throw new NotFoundException("No se encontro registro");
		
		return Response.ok(entity).build();
	}
	
	private void setValueIdProperty(ID id, E entity) throws Exception{
		String idProperty = tableService.getIdProperty();
		String setMethod = tableService.getMethodSet(idProperty);
		entityClass.getMethod(setMethod, id.getClass()).invoke(entity, id);
	}
}
