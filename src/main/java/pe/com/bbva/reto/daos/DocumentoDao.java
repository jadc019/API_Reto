package pe.com.bbva.reto.daos;

import pe.com.bbva.reto.entities.DocumentoEntity;

public interface DocumentoDao extends GenericInterfaceDao<Integer, DocumentoEntity>{

	DocumentoEntity findByUrl(String path);

}
