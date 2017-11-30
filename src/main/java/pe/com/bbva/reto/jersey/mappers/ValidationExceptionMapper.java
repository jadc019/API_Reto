package pe.com.bbva.reto.jersey.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import pe.com.bbva.reto.exceptions.ValidationException;

@Component
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException exception) {
		return Response.status(exception.getResponse().getStatus()).entity(exception.getResponse()).build();
	}

}
