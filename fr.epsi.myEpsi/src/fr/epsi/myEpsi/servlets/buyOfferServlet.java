package fr.epsi.myEpsi.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;

/**
 * Servlet implementation class buyOfferServlet
 */
@WebServlet("/buyOfferServlet")
public class buyOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(buyOfferServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyOfferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doGet de la servlet buyOfferServlet");
		}
		
		String offerId = request.getParameter("ID");
		String buyerId = request.getParameter("BUYER");

		OfferDao.sellOffer(Integer.parseInt(offerId), buyerId);

		request.getRequestDispatcher("getOffersServlet?LOGIN=" + buyerId).forward(request, response);
	}

}
