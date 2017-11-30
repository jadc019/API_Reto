package pe.com.bbva.reto.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.bbva.reto.daos.DocumentoDao;
import pe.com.bbva.reto.entities.DocumentoEntity;
import pe.com.bbva.reto.services.AbstractTableService;
import pe.com.bbva.reto.services.DocumentoService;

@Service
public class DocumentoServiceImpl extends AbstractTableService<Integer, DocumentoEntity> implements DocumentoService{

	private DocumentoDao documentoDao;
	
	@Autowired
	public DocumentoServiceImpl(DocumentoDao documentoDao) {
		super(documentoDao);
		this.documentoDao = documentoDao;
	}
	
	@Override
	@Transactional(readOnly=true)
	public DocumentoEntity findById(Integer id) throws Exception{
		DocumentoEntity entity = documentoDao.findById(id);
		if(entity != null)
			entity.getNotas().size();
		return entity;
	}

	@Override
	public DocumentoEntity findByUrl(String url) {
		return documentoDao.findByUrl(url);
	}
	
}
