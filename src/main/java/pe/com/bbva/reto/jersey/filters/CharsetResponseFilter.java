package pe.com.bbva.reto.jersey.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;

import pe.com.bbva.reto.APIConstants;

public class CharsetResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		MediaType contentType = responseContext.getMediaType();
		if(contentType == null)
			responseContext.getHeaders().putSingle("Content-Type", APIConstants.HEADER_CT_JSON_UTF8);
		else
			responseContext.getHeaders().putSingle("Content-Type", contentType.toString() + APIConstants.CT_CHARSET_UTF8);
	}
}