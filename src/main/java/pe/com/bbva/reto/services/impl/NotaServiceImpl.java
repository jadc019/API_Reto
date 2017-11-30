package pe.com.bbva.reto.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.bbva.reto.daos.NotaDao;
import pe.com.bbva.reto.entities.NotaEntity;
import pe.com.bbva.reto.services.AbstractTableService;
import pe.com.bbva.reto.services.NotaService;

@Service
public class NotaServiceImpl extends AbstractTableService<Integer, NotaEntity> implements NotaService{

	@Autowired
	public NotaServiceImpl(NotaDao notaDao) {
		super(notaDao);
	}

}
