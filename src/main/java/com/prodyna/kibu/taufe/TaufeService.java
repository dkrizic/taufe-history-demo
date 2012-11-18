package com.prodyna.kibu.taufe;

import java.util.List;

public interface TaufeService {
	Taufe add( Taufe t );
	void update( Taufe t );
	List<Taufe> list();
}
