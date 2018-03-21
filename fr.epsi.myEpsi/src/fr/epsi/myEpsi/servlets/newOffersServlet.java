package fr.epsi.myEpsi.servlets;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet("/newOffersServlet")
public class newOffersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LogManager.getLogger(newOffersServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doGet de la servlet newOffersServlet");
		}
		
		
        String title = request.getParameter("TITLE");
        String content = request.getParameter("CONTENT");
        Double prix = Double.parseDouble(request.getParameter("PRICE"));
        String idVendeur = request.getParameter("USER");

        Offer offer = new Offer();

        offer.setId(OfferDao.getNbOffer() + 1);
        offer.setTitre(title);
        offer.setDescription(content);
        offer.setPrix(prix);
        
        java.util.Date dateJava = new java.util.Date();
        java.sql.Date DateSQL = new java.sql.Date(dateJava.getTime());
        offer.setCreation(DateSQL);
        offer.setModification(DateSQL);
        
        User vendeur = UserDao.getUserById(idVendeur);
        offer.setVendeur(vendeur);

        OfferDao.saveOffer(offer);
		List<Offer> myOffers = OfferDao.getOffers(idVendeur);
		
		request.setAttribute("OFFERS", myOffers);
		request.setAttribute("USER", vendeur);
		request.getRequestDispatcher("offers.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
