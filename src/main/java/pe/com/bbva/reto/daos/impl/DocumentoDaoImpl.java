package pe.com.bbva.reto.daos.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pe.com.bbva.reto.daos.AbstractDao;
import pe.com.bbva.reto.daos.DocumentoDao;
import pe.com.bbva.reto.entities.DocumentoEntity;

@Repository
public class DocumentoDaoImpl extends AbstractDao<Integer, DocumentoEntity> implements DocumentoDao {

	@Override
	public String getNameID() {
		return "idePlan";
	}

	@Override
	public DocumentoEntity findByUrl(String url) {
		try {
			TypedQuery<DocumentoEntity> query = entityManager.createNamedQuery("DocumentoEntity.findByUrl",
					DocumentoEntity.class);
			query.setParameter("url", url);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
