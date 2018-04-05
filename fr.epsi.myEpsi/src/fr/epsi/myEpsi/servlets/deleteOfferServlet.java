package fr.epsi.myEpsi.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;

/**
 * Servlet implementation class deleteOfferServlet
 */
@WebServlet("/deleteOfferServlet")
public class deleteOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(deleteOfferServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteOfferServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doGet de la servlet deleteOfferServlet");
		}
		
		String offerId = request.getParameter("ID");
		Offer myOffer = OfferDao.getOfferById(offerId);

		request.setAttribute("OFFER", myOffer);
		request.getRequestDispatcher("deleteOffer.jsp").forward(request, response);

	}

}
