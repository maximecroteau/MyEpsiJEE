package fr.epsi.myEpsi.listeners;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.Constants;
import fr.epsi.myEpsi.jmx.Premier;


/**
 * Application Lifecycle Listener implementation class StartupListner
 *
 */
@WebListener
public class StartupListner implements ServletContextListener {

	private static final Logger logger = LogManager.getLogger(StartupListner.class);

    /**
     * Default constructor. 
     */
    public StartupListner() {
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void insertUserSucceed(String id) {
		logger.info("L'utilisateur " + id + " a été inséré en base de données.");
	}
	
	public static void insertUserFailed(String id, SQLException e) {
		logger.error("L'utilisateur " + id + " n'a pas été inséré en base de données : " + e);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Démarrage de l'application");
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			logger.info("Démarrage BDD OK");
			con.close();
		} catch (ClassNotFoundException e) {
			logger.error("Erreur au démarrage. Pas de driver...", e);
		} catch (SQLException e) {
			logger.error("Erreur au démarrage. Probleme de connexion...", e);
			
		}
		
		try {
			Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			Statement stmt = con.createStatement();
			
			ResultSet users = stmt.executeQuery("SELECT COUNT(ID) FROM UTILISATEURS");
			users.next();
			int nbUsers = users.getInt(1);
			
			ResultSet annonces = stmt.executeQuery("SELECT COUNT(ID) FROM ANNONCES");
			annonces.next();
			int nbAnnonces = annonces.getInt(1);
			
			logger.info("Données récupérées : " + nbUsers + " utilisateur(s) et " + nbAnnonces + " annonce(s).");
			con.close();
		} catch (SQLException e) {
			logger.error("Erreur lors de récupération de données dans la BDD", e);
			
		}
		
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name = null;
	    
	    try {
	      name = new ObjectName("fr.epsi.myEpsi.jmx:type=PremierMBean");
	      Premier mbean = new Premier();

	      mbs.registerMBean(mbean, name);

	      System.out.println("Lancement ...");
	    } catch (MalformedObjectNameException e) {
	    	e.printStackTrace();
	    } catch (NullPointerException e) {
	    	e.printStackTrace();
	    } catch (InstanceAlreadyExistsException e) {
	    	e.printStackTrace();
	    } catch (MBeanRegistrationException e) {
	    	e.printStackTrace();
	    } catch (NotCompliantMBeanException e) {
	    	e.printStackTrace();
	    }

	}
	
}
