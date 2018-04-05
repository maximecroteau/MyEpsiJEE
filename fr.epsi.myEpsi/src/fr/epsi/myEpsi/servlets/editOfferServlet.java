package fr.epsi.myEpsi.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;

/**
 * Servlet implementation class editOfferServlet
 */
@WebServlet("/editOfferServlet")
public class editOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(editOfferServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editOfferServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doGet de la servlet editOfferServlet");
		}
		String offerId = request.getParameter("ID");
		String userId = request.getParameter("USER");
		String edit = request.getParameter("EDIT");
		Offer myOffer = OfferDao.getOfferById(offerId);
		
		request.setAttribute("OFFER", myOffer);
		request.setAttribute("USER", userId);
		request.setAttribute("EDIT", edit);
		request.getRequestDispatcher("editOffer.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doPost de la servlet editOfferServlet");
		}
		
		int offerId = Integer.valueOf(request.getParameter("OFFER"));
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		int status = Integer.valueOf(request.getParameter("STATUS"));
		Double prix = Double.parseDouble(request.getParameter("PRICE"));
		String idVendeur = request.getParameter("USER");

		Offer offer = new Offer();
		
		offer.setId(offerId);
		offer.setTitre(title);
		offer.setDescription(content);
		offer.setPrix(prix);
		offer.setStatut(status);

		java.util.Date dateJava = new java.util.Date();
		java.sql.Date DateSQL = new java.sql.Date(dateJava.getTime());
		offer.setModification(DateSQL);

		User vendeur = UserDao.getUserById(idVendeur);
		offer.setVendeur(vendeur);

		OfferDao.updateOffer(offer);
		List<Offer> myOffers = OfferDao.getOffers(idVendeur);

		request.setAttribute("OFFERS", myOffers);
		request.setAttribute("USER", vendeur);
		request.getRequestDispatcher("offers.jsp").forward(request, response);
	}

}
