package com.prodyna.kibu.taufe.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.kibu.taufe.Taufe;
import com.prodyna.kibu.taufe.TaufeHistory;
import com.prodyna.kibu.taufe.TaufeHistoryService;
import com.prodyna.kibu.taufe.monitoring.Monitored;

@Stateless
@Monitored
public class TaufeHistoryServiceBean implements TaufeHistoryService {

	@Inject
	private EntityManager em;

	@Inject
	private Logger log;

	@Override
	public TaufeHistory add(TaufeHistory th) {
		em.persist(th);
		return th;
	}

	@Override
	public List<TaufeHistory> list(Taufe t) {
		return em.createNamedQuery("TaufeHistoryByTaufe", TaufeHistory.class)
				.setParameter("t", t).getResultList();
	}

}
