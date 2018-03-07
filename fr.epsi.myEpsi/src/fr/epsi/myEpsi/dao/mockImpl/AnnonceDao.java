package fr.epsi.myEpsi.dao.mockImpl;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.Status;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.IAnnonceDao;
import java.util.ArrayList;
import java.util.List;

public class AnnonceDao implements IAnnonceDao {

    private static List<Offer> listOfOffers;

    public AnnonceDao(){
        listOfOffers = new ArrayList<>();

        Offer offer = new Offer();
        offer.setId(1);
        offer.setTitre("Livre 1");
        offer.setVendeur(new User());
        offer.getVendeur().setId("ADMIN");
        listOfOffers.add(offer);

        offer = new Offer();
        offer.setId(1);
        offer.setTitre("Livre 2");
        offer.setVendeur(new User());
        offer.getVendeur().setId("ADMIN");
        listOfOffers.add(offer);

        offer = new Offer();
        offer.setId(1);
        offer.setTitre("Livre 1");
        offer.setVendeur(new User());
        offer.getVendeur().setId("TOTO");
        offer.setStatut(Status.ANNULE);
        listOfOffers.add(offer);
    }


    @Override
    public List<Offer> getAnnonces(String loginId){
        List<Offer> myOffers = new ArrayList<>();
        for (Offer offer : listOfOffers){
            //Vérifie si on peut la voir
            if (offer.getVendeur().getId().equals(loginId) || offer.getStatut() == Status.PUBLIE) {
                myOffers.add(offer);
            }
        }
        return myOffers;
    }
}
