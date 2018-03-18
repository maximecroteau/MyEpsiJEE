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
import fr.epsi.myEpsi.dao.IOfferDao;
import fr.epsi.myEpsi.dao.IUserDao;
//import fr.epsi.myEpsi.dao.mockImpl.AnnonceDao;
//import fr.epsi.myEpsi.dao.mockImpl.UtilisateurDao;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(LoginServlet.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doPost de la servlet LoginServlet");
		}
		String login = request.getParameter("LOGIN");
		String password = request.getParameter("PWD");
		
		User user = new User();
		user.setId(login);
		user.setPassword(password);
		
		IUserDao userDao = new UserDao();
		if (userDao.checkLogin(user)) {
			IOfferDao offerDao = new OfferDao();
			List<Offer> myOffers = offerDao.getOffers(login);
			request.setAttribute("OFFERS", myOffers);
			request.setAttribute("USER", UserDao.getUserById(login));
			request.getRequestDispatcher("offers.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

}
