package fr.epsi.myEpsi;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.Status;
import fr.epsi.myEpsi.beans.User;

public class deleteAnnoncePriveeTest {
	
	public static void deleteOffer(Offer offer, String userId) {
		if(offer.getVendeur().getId().equals(userId)) {
			offer.setStatut(Status.ANNULE);
		}
	}
	
	@Test
	public void testAnnoncePrivee() {
		User user1 = new User();
		user1.setId("user1@epsi.fr");
		
		User user2 = new User();
		user2.setId("user2@epsi.fr");
		
		Offer offer = new Offer();
		offer.setId(0);
		offer.setTitre("Offer 1");
		offer.setVendeur(user1);
		offer.setStatut(Status.PUBLIE);
		
		deleteOffer(offer, user2.getId());
		
		boolean isDeleted = false;
		if(offer.getStatut() == Status.ANNULE) {
			isDeleted = true;
		}
		
		assertFalse("SuppressionAnnonce", isDeleted);
	}

}
