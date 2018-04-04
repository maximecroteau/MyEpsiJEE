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
		// TODO Auto-generated constructor stub
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

}
