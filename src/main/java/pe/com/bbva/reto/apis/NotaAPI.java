package pe.com.bbva.reto.apis;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.bbva.reto.entities.NotaEntity;
import pe.com.bbva.reto.services.NotaService;

@Component
@Path("/nota")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class NotaAPI extends AbstractTableAPI<Integer, NotaEntity> {

	private static final Logger logger = Logger.getLogger(NotaAPI.class);

	private NotaService notaService;

	@Autowired
	public NotaAPI(NotaService notaService) {
		super(notaService);
		this.notaService = notaService;
	}

}
