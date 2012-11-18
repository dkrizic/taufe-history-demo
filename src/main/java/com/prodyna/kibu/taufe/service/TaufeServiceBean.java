package com.prodyna.kibu.taufe.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.kibu.taufe.Taufe;
import com.prodyna.kibu.taufe.TaufeService;
import com.prodyna.kibu.taufe.monitoring.Monitored;

@Stateless
@Monitored
public class TaufeServiceBean implements TaufeService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	public Taufe add(Taufe t) {
		em.persist(t);
		return t;
	}

	public void update(Taufe t) {
		em.merge(t);
	}

	@Override
	public List<Taufe> list() {
		return em.createQuery("select t from Taufe t", Taufe.class)
				.getResultList();
	}

}
