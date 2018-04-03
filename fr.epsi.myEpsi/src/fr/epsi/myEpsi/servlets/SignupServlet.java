package fr.epsi.myEpsi.servlets;

import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(SignupServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doPost de la servlet SignupServlet");
		}

		/*REPWD permet de voir si les deux mots de passe correspondent lors de la saisie*/
		String name = request.getParameter("NAME");
		String tel = request.getParameter("TEL");
		String login = request.getParameter("LOGIN");
		String password = request.getParameter("PWD");
		String repassword = request.getParameter("REPWD");

		User user = new User();
		user.setNom(name);
		user.setTelephone(tel);
		user.setId(login);
		user.setPassword(password);
		user.setAdministrateur(false);

		if (UserDao.checkIfInBase(user, UserDao.getAllUsers())) {
			logger.info("Login pas dans la base : OK ! ");

			if(UserDao.validationEmail(login)){
				logger.info("Email : OK ");
				if(UserDao.validationMotDePasse(password, repassword)){
					logger.info("Password : OK ");
					UserDao.saveUser(user);
				}
				else {
					request.setAttribute("PWDERROR", "Mot de passe incorrect : Minimum 3 caractères et identiques entre eux");
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}
			}
			else {
				logger.info("Email : NEIN ");
				request.setAttribute("MAILERROR", "Login déjà  utilisé ou incorrect");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}

			request.setAttribute("OKSIGNUP", "Compte crée !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			logger.info("L'inscription ne va pas se faire ! ");
			request.setAttribute("LOGINERROR", "Login déjà  utilisé ou incorrect");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

}
