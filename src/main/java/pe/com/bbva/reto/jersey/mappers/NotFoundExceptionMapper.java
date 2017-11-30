package pe.com.bbva.reto.jersey.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import pe.com.bbva.reto.exceptions.NotFoundException;

@Component
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>{

	@Override
	public Response toResponse(NotFoundException exception) {
		return Response.status(exception.getResponse().getStatus()).entity(exception.getResponse()).build();
	}

}
