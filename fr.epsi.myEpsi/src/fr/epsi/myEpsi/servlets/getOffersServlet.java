package fr.epsi.myEpsi.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epsi.myEpsi.beans.Offer;
import fr.epsi.myEpsi.dao.IOfferDao;
//import fr.epsi.myEpsi.dao.mockImpl.AnnonceDao;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;

/**
 * Servlet implementation class getAnnoncesServlet
 */
@WebServlet("/getOffersServlet")
public class getOffersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOffersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("LOGIN");
		IOfferDao offerDao = new OfferDao();
		List<Offer> myOffers = offerDao.getOffers(login);
		
		request.setAttribute("OFFERS", myOffers);
		request.getRequestDispatcher("offers.jsp").forward(request, response);
	}

}