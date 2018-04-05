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
import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;

/**
 * Servlet implementation class getAnnoncesServlet
 */
@WebServlet("/getOffersServlet")
public class getOffersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LogManager.getLogger(getOffersServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOffersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doGet de la servlet getOffersServlet");
		}
		String login = request.getParameter("LOGIN");
		List<Offer> myOffers = OfferDao.getOffers(login);
		
		request.setAttribute("OFFERS", myOffers);
		request.getRequestDispatcher("offers.jsp").forward(request, response);
	}

}
