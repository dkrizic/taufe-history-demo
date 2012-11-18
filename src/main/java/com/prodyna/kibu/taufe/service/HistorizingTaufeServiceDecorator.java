package com.prodyna.kibu.taufe.service;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;

import com.prodyna.kibu.taufe.Taufe;
import com.prodyna.kibu.taufe.TaufeHistory;
import com.prodyna.kibu.taufe.TaufeHistoryService;
import com.prodyna.kibu.taufe.TaufeService;
import com.prodyna.kibu.taufe.monitoring.Monitored;

@Decorator
@Monitored
public abstract class HistorizingTaufeServiceDecorator implements TaufeService {

	@Inject
	@Delegate
	private TaufeService ts;

	@Inject
	private TaufeHistoryService ths;
	
	@Inject
	private UserTransaction ta;

	@Inject
	private Logger log;

	@Override
	public Taufe add(Taufe t) {
		log.info("Adding " + t);
		t = ts.add(t);
		ths.add(new TaufeHistory(t));
		return t;
	}

	@Override
	public void update(Taufe t) {
		log.info("Updating " + t);
		ts.update(t);
		ths.add(new TaufeHistory(t));
	}

}
