package pe.com.bbva.reto.services;

import pe.com.bbva.reto.entities.DocumentoEntity;

public interface DocumentoService extends GenericTableService<Integer, DocumentoEntity>{

	DocumentoEntity findByUrl(String path);

}
