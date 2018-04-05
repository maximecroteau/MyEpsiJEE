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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Appel doPost de la servlet SignupServlet");
		}

		/* REPWD permet de voir si les deux mots de passe correspondent lors de la saisie */
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

		boolean alreadyExist = UserDao.checkIfInBase(user, UserDao.getAllUsers());
		boolean emailOK = UserDao.validationEmail(login);
		boolean mdpOK = UserDao.validationMotDePasse(password, repassword);
		
		if (alreadyExist && emailOK && mdpOK) {
			UserDao.saveUser(user);
			request.setAttribute("OKSIGNUP", "Compte créé !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if(!mdpOK) {
			request.setAttribute("PWDERROR", "Mot de passe incorrect : Il doit contenir au minimum 3 caractères et les 2 mots de passe doivent être identiques entre eux");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} else if(!emailOK){
			request.setAttribute("MAILERROR", "Login incorrect");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} else if(alreadyExist){
			request.setAttribute("LOGINERROR", "Login déjà  utilisé");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}

	}

}
