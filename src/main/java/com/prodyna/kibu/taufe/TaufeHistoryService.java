package com.prodyna.kibu.taufe;

import java.util.List;

public interface TaufeHistoryService {
	TaufeHistory add(TaufeHistory th);
	List<TaufeHistory> list(Taufe t);
}
