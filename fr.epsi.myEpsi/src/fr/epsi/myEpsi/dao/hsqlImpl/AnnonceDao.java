package fr.epsi.myEpsi.dao.hsqlImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.epsi.myEpsi.Constants;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.Status;
import fr.epsi.myEpsi.dao.IAnnonceDao;

public class AnnonceDao implements IAnnonceDao {

    @Override
    public List<Offer> getAnnonces(String loginId){
        List<Offer> myOffers = new ArrayList<>();

        for (Offer offer : getAllAnnonces()) {
            // Vérifie si on peut la voir
            if (offer.getStatut() == Status.PUBLIE) {
                myOffers.add(offer);
            }
        }
        return myOffers;
    }

    public static List<Offer> getAllAnnonces() {
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

}