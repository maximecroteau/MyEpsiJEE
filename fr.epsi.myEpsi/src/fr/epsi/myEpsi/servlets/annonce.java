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
 * Servlet implementation class annonce
 */
@WebServlet("/annonce")
public class annonce extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(annonce.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public annonce() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doGet de la servlet annonce");
		}
	
		Offer myOffer = OfferDao.getOfferById(request.getParameter("ID"));
		
		request.setAttribute("OFFER", myOffer);
		request.setAttribute("USER", "ADMIN");
		request.setAttribute("EDIT", "2");
		request.getRequestDispatcher("editOffer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
