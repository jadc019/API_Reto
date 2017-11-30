package pe.com.bbva.reto.daos.impl;

import org.springframework.stereotype.Repository;

import pe.com.bbva.reto.daos.AbstractDao;
import pe.com.bbva.reto.daos.NotaDao;
import pe.com.bbva.reto.entities.NotaEntity;

@Repository
public class NotaDaoImpl extends AbstractDao<Integer, NotaEntity> implements NotaDao{

	@Override
	public String getNameID() {
		return "idNota";
	}

}
