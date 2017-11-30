package pe.com.bbva.reto.jersey.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import pe.com.bbva.reto.exceptions.InternalErrorException;

@Component
@Provider
public class InternalErrorExceptionMapper implements ExceptionMapper<InternalErrorException> {

	@Override
	public Response toResponse(InternalErrorException exception) {
		return Response.status(exception.getResponse().getStatus()).entity(exception.getResponse()).build();
	}

}
