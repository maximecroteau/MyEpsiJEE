package fr.epsi.myEpsi.dao.hsqlImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import fr.epsi.myEpsi.Constants;
import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.beans.User;

public class UserDao {
	private static final Logger logger = LogManager.getLogger(UserDao.class);

	private static void logInsert(boolean succes, String id, String pwd, boolean admin, String nom, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requete suivante executee avec succes : ");
			} else {
				toDebug.append ("Echec de la requete suivante : ");
			}
			toDebug.append ("INSERT INTO UTILISATEURS (ID , PASSWORD , ISADMINISTRATOR , NAME)");
			toDebug.append ("VALUES (\"" + id + "\" , \"" + pwd + "\" , " + admin + " , \"" + nom + "\")");
			if(!succes) {
				toDebug.append(" : " + e);
			}

			logger.debug(toDebug);
		}
	}

	private static void logSelect(boolean succes, String[] where, String[] values, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requete suivante executee avec succes : ");
			} else {
				toDebug.append ("Echec de la requete suivante : ");
			}
			toDebug.append ("SELECT * FROM UTILISATEURS");
			if(where.length > 0) {
				toDebug.append (" WHERE ");
				int i = 0;
				for (String clause : where) {
					if(i > 0) {
						toDebug.append ("AND ");
					}
					toDebug.append ( clause + "=" + values[i]);
					i++;
				}
			}

			logger.debug(toDebug);
		}
	}

	private static void logCount(boolean succes, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requete suivante executee avec succes : ");
			} else {
				toDebug.append ("Echec de la requete suivante : ");
			}
			toDebug.append ("SELECT COUNT(ID) FROM UTILISATEURS");

			logger.debug(toDebug);
		}
	}

	public static List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		String where[] = {};
		String values[] = {};

		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);

			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM UTILISATEURS");
			logSelect(true, where, values, null);

			while (results.next()) {
				User user = new User();
				user.setId(results.getString(1));
				user.setPassword(results.getString(2));
				user.setAdministrateur(results.getBoolean(3));
				user.setNom(results.getString(4));

				users.add(user);
			}
			con.close();
		} catch (SQLException e) {
			logSelect(false, where, values, e);
		}
		return users;
	}

	public static int getNbUser() {
		int nbUsers = 0;
		String req = "SELECT COUNT(ID) FROM UTILISATEURS";
		try {
			Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(req);
			res.next();
			nbUsers = res.getInt(1);

			con.close();
			logCount(true, null);
		}  catch (SQLException e) {
			logCount(false, e);
		}
		return nbUsers;
	}

	public static User getUserById(String id) {
		User user = new User();
		String where[] = {"ID"};
		String values[] = {id};

		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);

			PreparedStatement psmt = con.prepareStatement("SELECT * FROM UTILISATEURS WHERE ID = ?") ;
			psmt.setString(1, id);
			ResultSet results = psmt.executeQuery();

			if(results.next()){
				user.setId(results.getString(1));
				user.setPassword(results.getString(2));
				user.setAdministrateur(results.getBoolean(3));
				user.setNom(results.getString(4));
			}
			logSelect(true, where, values, null);
			con.close();
		} catch (SQLException e) {
			logSelect(false, where, values, e);
		}
		return user;
	}

	public static boolean checkLogin(User user) {
		boolean accesOk = false;
		User existingUser = null;
		for (User u: getAllUsers()) {
			if (u.getId().equals(user.getId())) {
				existingUser = u;
				break;
			}
		}
		if (existingUser != null) {
			accesOk = (user.getId().equals(existingUser.getId())
					&& user.getPassword().equals(existingUser.getPassword()));
		}
		if(!accesOk) {
			if(logLevel.actualLogLevel <= logLevel.ERROR) {
				logger.error("La connexion a ete refusee a l'utilisateur " + user.getId() + ".");
			}
		}
		logger.info("Accesok " + accesOk);
		return accesOk;
	}

	public static boolean checkIfInBase(User user) {
		boolean accesOk = false;
		User existingUser = null;
		for (User u: getAllUsers()) {
			if (u.getId().equals(user.getId())) {
				existingUser = u;
				break;
			}
		}
		if (existingUser == null) {
			accesOk = true;
		}

		logger.info("Accesok " + accesOk);
		return accesOk;

	}

	/**
	 * Valide l'adresse email saisie.
	 */
	public static boolean validationEmail( String email ) {
		boolean isOK = false;
		if ( email != null && email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			isOK = true;
		}
		else {
			logger.info( "Merci de saisir une adresse mail valide." );
		}
		return isOK;
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	public static boolean validationMotDePasse( String motDePasse, String reMotDePasse ) {
		boolean isOK = false;
		if ( motDePasse != null ) {
			if ( motDePasse.length() >= 3 && motDePasse.equals(reMotDePasse) ) {
				isOK = true;
			}
		} else {
			logger.info( "Merci de saisir votre mot de passe correctement." );
		}

		return isOK;
	}

	public static void saveUser(User newUser) {
		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);

			PreparedStatement psmt = con.prepareStatement("INSERT INTO UTILISATEURS (ID,PASSWORD,ISADMINISTRATOR,NAME) VALUES (?,?,?,?)");
			psmt.setString(1, newUser.getId());
			psmt.setString(2, newUser.getPassword());
			psmt.setBoolean(3, newUser.isAdministrateur());
			psmt.setString(4, newUser.getNom());

			psmt.executeUpdate();

			logInsert(true, newUser.getId(), newUser.getPassword(), newUser.isAdministrateur(), newUser.getNom(), null);

			con.close();
		} catch (SQLException e) {
			logInsert(false, newUser.getId(), newUser.getPassword(), newUser.isAdministrateur(), newUser.getNom(), e);
		}
	}
}
