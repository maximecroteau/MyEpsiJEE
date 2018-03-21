package fr.epsi.myEpsi.dao.hsqlImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.Constants;
import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.Status;
import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;


public class OfferDao {
	private static final Logger logger = LogManager.getLogger(UserDao.class);

	private static void logInsert(boolean succes, int id, String titre, String description, int statut, Double prix, Date creation, String idVendeur, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requête suivante éxécutée avec succès : ");
			} else {
				toDebug.append ("Échec de la requête suivante : ");
			}
			toDebug.append ("INSERT INTO ANNONCES (ID,TITLE,CONTENT,PRICE,USER_ID,CREATION_DATE,STATUS,NB_VIEW,UPDATE_DATE) ");
			toDebug.append ("VALUES (" + id + "," + titre + "," + description + "," + prix + "," + idVendeur+ "," + creation + "," + statut + ",0," + creation + ")");
			if(!succes) {
				toDebug.append(e);
			}
		
	    	logger.debug(toDebug);
	    }
	}
	
	private static void logSelect(boolean succes, String req, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requête suivante éxécutée avec succès : ");
			} else {
				toDebug.append ("Échec de la requête suivante : ");
			}
			toDebug.append (req);
	    	logger.debug(toDebug);
	    }
	}
	
	public static List<Offer> getOffers(String loginId){
		List<Offer> myOffers = new ArrayList<>();
		
		for (Offer offer : getAllOffers()) {
			// Vérifie si on peut la voir
			if (offer.getStatut() == Status.PUBLIE || offer.getVendeur().getId() == loginId) {
				myOffers.add(offer);
			}
		}
		return myOffers;
	}
	
	public static List<Offer> getAllOffers() {
		List<Offer> offers = new ArrayList<>();
		String req = "SELECT * FROM ANNONCES";
		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			
		    Statement stmt = con.createStatement();
		    ResultSet results = stmt.executeQuery(req);
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
			logSelect(true, req, null);
			con.close();
		} catch (SQLException e) {
			logSelect(false, req, e);
		}
		return offers;
	}

    public static int getNbOffer() {
        int nbAnnonces = 0;
        String req = "SELECT COUNT(ID) FROM ANNONCES";
        try {
            Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
            Statement stmt = con.createStatement();
            ResultSet offers = stmt.executeQuery(req);
            offers.next();
            nbAnnonces = offers.getInt(1);
                      
            con.close();
            logSelect(true, req, null);
        }  catch (SQLException e) {
        	logSelect(false, req, e);
        }
        return nbAnnonces;
    }

	public static void saveOffer(Offer newOffer) {
        Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
            PreparedStatement psmt = con.prepareStatement("INSERT INTO ANNONCES (ID,TITLE,CONTENT,PRICE,USER_ID,CREATION_DATE,STATUS,NB_VIEW,UPDATE_DATE) VALUES(?,?,?,?,?,?,?)");

            psmt.setInt(1, newOffer.getId());
			psmt.setString(2, newOffer.getTitre());
			psmt.setString(3, newOffer.getDescription());
            psmt.setDouble(4, newOffer.getPrix());
            psmt.setString(5, newOffer.getVendeur().getId());
            java.sql.Date DateSQL = new java.sql.Date(newOffer.getCreation().getTime());
			psmt.setDate(6, DateSQL);
			psmt.setInt(7, newOffer.getStatut());
			psmt.setLong(8, newOffer.getNbVues());
			psmt.setDate(9, DateSQL);
			psmt.executeUpdate();
			
			logInsert(true,newOffer.getId(),newOffer.getTitre(), newOffer.getDescription(), newOffer.getStatut(), newOffer.getPrix(), (Date)newOffer.getCreation(), newOffer.getVendeur().getId(), null);
			con.close();
		}  catch (SQLException e) {
			logInsert(false,newOffer.getId(),newOffer.getTitre(), newOffer.getDescription(), newOffer.getStatut(), newOffer.getPrix(), (Date)newOffer.getCreation(), newOffer.getVendeur().getId(), e);
		}
	}

}
