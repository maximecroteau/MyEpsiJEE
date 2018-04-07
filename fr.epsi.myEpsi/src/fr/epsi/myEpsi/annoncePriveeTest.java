package fr.epsi.myEpsi;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.Status;

public class annoncePriveeTest {
	
	public static List<Offer> offers = new ArrayList<Offer>();
	
	public static List<Offer> getOffers(String loginId){
		List<Offer> myOffers = new ArrayList<>();

		for (Offer offer : offers) {
			// Annonce publiées ou créées par l'utilisateur
			if (offer.getStatut() == Status.PUBLIE || offer.getVendeur().getId().equals(loginId)) {
				myOffers.add(offer);
			}
		}
		return myOffers;
	}
	
	@Test
	public void testAnnoncePrivee() {
		User user1 = new User();
		user1.setId("user1@epsi.fr");
		
		User user2 = new User();
		user2.setId("user2@epsi.fr");
		
		Offer offer1 = new Offer();
		offer1.setId(0);
		offer1.setTitre("Offer 1");
		offer1.setVendeur(user1);
		offer1.setStatut(Status.TEMPORAIRE);
		
		offers.add(offer1);
		
		List<Offer> VisibleOffersUser2 = getOffers(user2.getId());
		
		boolean isVisible = false;
		for(Offer offer : VisibleOffersUser2) {
			if(offer.getId() == offer1.getId()) {
				isVisible = true;
			}
		}
		
		assertFalse("AnnoncePrivee", isVisible);
	}

}
