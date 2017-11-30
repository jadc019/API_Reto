package pe.com.bbva.reto.apis;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.bbva.reto.entities.DocumentoEntity;
import pe.com.bbva.reto.exceptions.NotFoundException;
import pe.com.bbva.reto.services.DocumentoService;

@Component
@Path("/documento")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class DocumentoAPI extends AbstractTableAPI<Integer, DocumentoEntity> {

	private static final Logger logger = Logger.getLogger(DocumentoAPI.class);

	private DocumentoService documentoService;

	@Autowired
	public DocumentoAPI(DocumentoService documentoService) {
		super(documentoService);
		this.documentoService = documentoService;
	}
	
	@GET
	@Path("/url")
	public Response findByPath(@QueryParam("url") String url) throws Exception{
		DocumentoEntity entity = documentoService.findByUrl(url);
		if(entity == null)
			throw new NotFoundException(String.format("PDF '%s' no registrado", url));
		
		return Response.ok(entity).build();
	}

}
