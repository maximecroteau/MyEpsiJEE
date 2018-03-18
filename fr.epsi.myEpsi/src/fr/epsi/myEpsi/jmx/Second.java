package fr.epsi.myEpsi.jmx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.Constants;
import fr.epsi.myEpsi.beans.logLevel;

public class Second implements SecondMBean {
	
	private static Logger logger = LogManager.getLogger(Second.class);

	private static void logSelect(boolean succes, SQLException e) {
		if(logLevel.actualLogLevel == logLevel.DEBUG) {
			StringBuilder toDebug = new StringBuilder();
			if(succes) {
				toDebug.append ("Requête suivante éxécutée avec succès : ");
			} else {
				toDebug.append ("Échec de la requête suivante : ");
			}
			toDebug.append ("SELECT COUNT(ID) FROM ANNONCES");
	    	logger.debug(toDebug);
	    }
	}
	
	@Override
	public int getOffersNb() {
		int nbAnnonces = 0;
		try {
			Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			Statement stmt = con.createStatement();
			
			ResultSet annonces = stmt.executeQuery("SELECT COUNT(ID) FROM ANNONCES");
			annonces.next();
			nbAnnonces = annonces.getInt(1);
			
			logSelect(true,null);
			con.close();
		} catch (SQLException e) {
			logSelect(false,e);
		}
		return nbAnnonces;
	}
	
	public Second() {
		
	}

}
