package fr.epsi.myEpsi.listeners;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
import fr.epsi.myEpsi.beans.logLevel;
import fr.epsi.myEpsi.dao.hsqlImpl.OfferDao;
import fr.epsi.myEpsi.dao.hsqlImpl.UserDao;
import fr.epsi.myEpsi.jmx.Premier;
import fr.epsi.myEpsi.jmx.Second;


/**
 * Application Lifecycle Listener implementation class StartupListner
 *
 */
@WebListener
public class StartupListner implements ServletContextListener {

	private static Logger logger = LogManager.getLogger(StartupListner.class);

    /**
     * Default constructor. 
     */
    public StartupListner() {
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		if(logLevel.actualLogLevel <= logLevel.INFO) {
			logger.info("Démarrage de l'application");
		}
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			if(logLevel.actualLogLevel <= logLevel.INFO) {
				logger.info("Démarrage BDD OK");
			}
			con.close();
		} catch (ClassNotFoundException e) {
			logger.error("Erreur au démarrage. Pas de driver...", e);
		} catch (SQLException e) {
			logger.error("Erreur au démarrage. Probleme de connexion...", e);
			
		}

		int nbUsers = UserDao.getNbUser();
		int nbAnnonces = OfferDao.getNbOffer();
		logger.error("Données récupérées : " + nbUsers + " utilisateur(s).");
		logger.error("Données récupérées : " + nbAnnonces + " annonce(s).");
		
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name = null;
	    
	    try {
	      name = new ObjectName("fr.epsi.myEpsi.jmx:type=PremierMBean");
	      Premier mbean = new Premier(logLevel.getLevel());
	      mbs.registerMBean(mbean, name);
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
	    
	    try {
		      name = new ObjectName("fr.epsi.myEpsi.jmx:type=SecondMBean");
		      Second mbean = new Second();
		      mbs.registerMBean(mbean, name);
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
