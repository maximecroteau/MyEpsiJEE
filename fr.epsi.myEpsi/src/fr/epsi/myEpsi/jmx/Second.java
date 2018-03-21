package fr.epsi.myEpsi.jmx;

import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;

public class Second implements SecondMBean {
		
	@Override
	public int getOffersNb() {
		return OfferDao.getNbOffer();
	}
	
	public Second() {
		
	}

}
