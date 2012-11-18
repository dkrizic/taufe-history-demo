package com.prodyna.kibu.taufe.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

	@PersistenceContext(name="kibu")
	private EntityManager em;
	
	@Produces
	public EntityManager createEntityManager() {
		return em;
	}
}
