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
	private static final Logger logger = LogManager.getLogger(OfferDao.class);

	private static void logUpdate(boolean succes, int id, String titre, String description, int statut, Double prix, long nbVues, Date modification, SQLException e) {
		if (logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if (succes) {
				toDebug.append("Requête suivante éxécutée avec succès : ");
			} else {
				toDebug.append("Échec de la requête suivante : ");
			}
			toDebug.append("UPDATE ANNONCES SET TITLE = \"" + titre + "\", CONTENT = \"" + description + "\", PRICE = " + prix + ", STATUS = "+ statut +", UPDATE_DATE = \"" + modification + "\", NB_VIEW = " + nbVues + " WHERE ID = " + id);
			if (!succes) {
				toDebug.append(" : " + e);
			}

			logger.debug(toDebug);
		}
	}
	
	private static void logInsert(boolean succes, int id, String titre, String description, int statut, Double prix, Date creation, String idVendeur, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requête suivante éxécutée avec succès : ");
			} else {
				toDebug.append ("Échec de la requête suivante : ");
			}
			toDebug.append ("INSERT INTO ANNONCES (ID , TITLE , CONTENT , PRICE , USER_ID , CREATION_DATE , STATUS , NB_VIEW , UPDATE_DATE) ");
			toDebug.append ("VALUES (" + id + " , \"" + titre + "\" , \"" + description + "\" , " + prix + " , \"" + idVendeur+ "\" , \"" + creation + "\" , " + statut + " , 0 , \"" + creation + "\")");
			if(!succes) {
				toDebug.append(" : " + e);
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
				toDebug.append ("échec de la requête suivante : ");
			}
			toDebug.append (req);
			if(!succes) {
				toDebug.append(" : " + e);
			}
	    	logger.debug(toDebug);
	    }
	}
	
	private static void logDelete(boolean succes, int id, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requête suivante éxécutée avec succès : ");
			} else {
				toDebug.append ("échec de la requête suivante : ");
			}
			toDebug.append ("UPDATE ANNONCES SET STATUS = 3 WHERE ID = " + id);
			if(!succes) {
				toDebug.append(" : " + e);
			}
	    	logger.debug(toDebug);
	    }
	}
	
	private static void logSell(boolean succes, int id, String buyer, java.sql.Date Date, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requête suivante éxécutée avec succès : ");
			} else {
				toDebug.append ("échec de la requête suivante : ");
			}
			toDebug.append ("UPDATE ANNONCES SET STATUS = 2, BUYER = \"" + buyer + "\", PURCHASE_DATE = \"" + Date + "\" WHERE ID = " + id);
			if(!succes) {
				toDebug.append(" : " + e);
			}
	    	logger.debug(toDebug);
	    }
	}
	
	public static List<Offer> getOffers(String loginId){
		List<Offer> myOffers = new ArrayList<>();

		for (Offer offer : getAllOffers()) {
			// Annonce publiées ou créées par l'utilisateur
			if (offer.getStatut() == Status.PUBLIE || offer.getVendeur().getId().equals(loginId)) {
				myOffers.add(offer);
			}
		}
		return myOffers;
	}

	public static Offer getOfferById(String id) {
		Offer offer = new Offer();

		String req = "SELECT * FROM ANNONCES WHERE ID = ?";
		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);

			PreparedStatement psmt = con.prepareStatement(req);
			psmt.setString(1, id);
			ResultSet results = psmt.executeQuery();

			if (results.next()) {
				offer = new Offer();
				offer.setId(results.getInt(1));
				offer.setTitre(results.getString(2));
				offer.setDescription(results.getString(3));
				offer.setVendeur(UserDao.getUserById(results.getString(4)));
				offer.setCreation(results.getDate(5));
				offer.setModification(results.getDate(6));
				offer.setNbVues(results.getInt(10));
				offer.setStatut(results.getInt(7));
				if (results.getString(8) != null) {
					offer.setAcheteur(UserDao.getUserById(results.getString(8)));
					offer.setAchat(results.getDate(9));
				}
				offer.setPrix(results.getDouble(11));
			}
			logSelect(true, req.replace("?", "\""+id+"\""), null);
			con.close();
		} catch (SQLException e) {
			logSelect(false, req.replace("?", "\""+id+"\""), e);
		}
		return offer;
	}

	public static void deleteOffer(int id) {
		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
					
			PreparedStatement psmt = con.prepareStatement("UPDATE ANNONCES SET STATUS = ? WHERE ID = ?");

			psmt.setInt(1, 3);
			psmt.setInt(2, id);
			psmt.executeUpdate();
			
			logDelete(true, id, null);
			con.close();
		} catch (SQLException e) {
			logDelete(false, id, e);
		}

	}
	
	public static void sellOffer(int id, String buyer) {
		Connection con;
		java.util.Date dateJava = new java.util.Date();
        java.sql.Date DateSQL = new java.sql.Date(dateJava.getTime());
        
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
					
			PreparedStatement psmt = con.prepareStatement("UPDATE ANNONCES SET STATUS = ?, BUYER = ?, PURCHASE_DATE = ? WHERE ID = ?");

			psmt.setInt(1, 2);
			psmt.setString(2, buyer);
			psmt.setDate(3, DateSQL);
			psmt.setInt(4, id);
			psmt.executeUpdate();
			
			logSell(true, id, buyer, DateSQL, null);
			con.close();
		} catch (SQLException e) {
			logSell(false, id, buyer, DateSQL, e);
		}

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
		    	offer.setNbVues(results.getInt(10));
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
    
    public static int getMaxId() {
        int maxId = 0;
        String req = "SELECT MAX(ID) FROM ANNONCES";
        try {
            Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
            Statement stmt = con.createStatement();
            ResultSet offers = stmt.executeQuery(req);
            offers.next();
            maxId = offers.getInt(1);
                      
            con.close();
            logSelect(true, req, null);
        }  catch (SQLException e) {
        	logSelect(false, req, e);
        }
        return maxId;
    }

	public static void saveOffer(Offer newOffer) {
        Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
            PreparedStatement psmt = con.prepareStatement("INSERT INTO ANNONCES (ID,TITLE,CONTENT,PRICE,USER_ID,CREATION_DATE,STATUS,NB_VIEW,UPDATE_DATE) VALUES(?,?,?,?,?,?,?,?,?)");

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

	public static void updateOffer(Offer newOffer) {
		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
					
			PreparedStatement psmt = con.prepareStatement("UPDATE ANNONCES SET TITLE = ?, CONTENT = ?, PRICE = ?, STATUS = ?, UPDATE_DATE = ?, NB_VIEW = ? WHERE ID = ?");

			psmt.setString(2, newOffer.getDescription());
			psmt.setString(1, newOffer.getTitre());
			if (newOffer.getPrix() != null) {
				psmt.setDouble(3, newOffer.getPrix());
			} else {
				psmt.setDouble(3, -1d);
			}
			java.sql.Date DateSQL = new java.sql.Date(newOffer.getModification().getTime());
			psmt.setInt(4, newOffer.getStatut());
			psmt.setDate(5, DateSQL);
			psmt.setInt(6, (int)newOffer.getNbVues());
			psmt.setInt(7, newOffer.getId());
			psmt.executeUpdate();
			logUpdate(true, newOffer.getId(), newOffer.getTitre(), newOffer.getDescription(), newOffer.getStatut(),newOffer.getPrix(), newOffer.getNbVues(), DateSQL, null);
			con.close();
		} catch (SQLException e) {
			logUpdate(false, newOffer.getId(), newOffer.getTitre(), newOffer.getDescription(), newOffer.getStatut(),newOffer.getPrix(), newOffer.getNbVues(), new java.sql.Date(newOffer.getModification().getTime()), e);
		}
	}
}
