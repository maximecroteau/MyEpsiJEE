package fr.epsi.myEpsi.dao.hsqlImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import fr.epsi.myEpsi.Constants;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.Status;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.IOfferDao;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;
import fr.epsi.myEpsi.listeners.StartupListner;

public class OfferDao implements IOfferDao {
	
	@Override
	public List<Offer> getOffers(String loginId){
		List<Offer> myOffers = new ArrayList<>();
		
		for (Offer offer : getAllOffers()) {
			// V�rifie si on peut la voir
			if (offer.getStatut() == Status.PUBLIE) {
				myOffers.add(offer);
			}
		}
		return myOffers;
	}
	
	public static List<Offer> getAllOffers() {
		List<Offer> offers = new ArrayList<>();

		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			
		    Statement stmt = con.createStatement();
		    ResultSet results = stmt.executeQuery("SELECT * FROM ANNONCES");
		    while (results.next()) {
		    	Offer offer = new Offer();
		    	offer.setId(results.getInt(1));
		    	offer.setTitre(results.getString(2));
		    	offer.setDescription(results.getString(3));
		    	offer.setVendeur(UserDao.getUserById(results.getString(4)));
		    	offer.setCreation(results.getDate(5));
		    	offer.setModification(results.getDate(6));
		    	offer.setStatut(results.getInt(7));
		    	if(results.getString(8) != null) {
		    		offer.setAcheteur(UserDao.getUserById(results.getString(8)));
		    		offer.setAchat(results.getDate(9));
		    	}
		    	
		    	offers.add(offer);
		    }
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offers;
	}

    public static int getNbOffer() {
        int nbAnnonces = 0;
        try {

            Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
            Statement stmt = con.createStatement();
            ResultSet offers = stmt.executeQuery("SELECT COUNT(ID) FROM ANNONCES");
            offers.next();
            nbAnnonces = offers.getInt(1);
            //StartupListner.insertOfferSucceed(newOffer.getId());
            //con.close();
        }  catch (SQLException e) {
            // StartupListner.insertOfferFailed(newOffer.getId(), e);
            System.out.println(e);
        }
        return nbAnnonces;
    }

	public static void saveOffer(Offer newOffer) {
        Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			Statement stmt = con.createStatement();

            PreparedStatement psmt = con.prepareStatement("INSERT INTO ANNONCES (ID,TITLE,CONTENT,PRICE,USER_ID,CREATION_DATE,STATUS) VALUES(?,?,?,?,?,?,?)");


            psmt.setInt(1, newOffer.getId());
			psmt.setString(2, newOffer.getTitre());
			psmt.setString(3, newOffer.getDescription());
            psmt.setDouble(4, newOffer.getPrix());
            psmt.setString(5, newOffer.getVendeur().getId());
			psmt.setDate(6, (Date) newOffer.getCreation());
			psmt.setInt(7, newOffer.getStatut());

			psmt.executeUpdate();


			//StartupListner.insertOfferSucceed(newOffer.getId());
			//con.close();
		}  catch (SQLException e) {
			// StartupListner.insertOfferFailed(newOffer.getId(), e);
            System.out.println(e);
		}
	}

}
