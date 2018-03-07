package fr.epsi.myEpsi.dao.hsqlImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.epsi.myEpsi.listeners.StartupListner;
import fr.epsi.myEpsi.Constants;
import fr.epsi.myEpsi.beans.User;
import fr.epsi.myEpsi.dao.IUserDao;

public class UserDao implements IUserDao {

	public static List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		Connection con;
		try {
			con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PWD);
			
		    Statement stmt = con.createStatement();
		    ResultSet results = stmt.executeQuery("SELECT * FROM UTILISATEURS");
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
			e.printStackTrace();
		}
		return users;
	}
	
	public static User getUserById(String id) {
		User user = new User();

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
		    
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public boolean checkLogin(User user) {
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
		return accesOk;
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
		    
		    StartupListner.insertUserSucceed(newUser.getId());		
			con.close();
		} catch (SQLException e) {
			StartupListner.insertUserFailed(newUser.getId(), e);
		}
	}
}
