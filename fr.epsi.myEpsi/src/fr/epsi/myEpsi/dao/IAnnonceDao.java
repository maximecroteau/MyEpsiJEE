package fr.epsi.myEpsi.dao;

import java.util.List;

import fr.epsi.myEpsi.beans.Offer;

public interface IAnnonceDao {
	List<Offer> getAnnonces(String loginId);
}
