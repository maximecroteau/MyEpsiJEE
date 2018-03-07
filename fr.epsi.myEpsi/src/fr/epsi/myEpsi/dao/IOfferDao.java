package fr.epsi.myEpsi.dao;

import java.util.List;

import fr.epsi.myEpsi.beans.Offer;

public interface IOfferDao {

	List<Offer> getOffers(String loginId);
}
