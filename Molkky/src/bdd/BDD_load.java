package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import servlets.PageAccueil;

public class BDD_load {
	
	public static Connection loadDatabase(Connection connexion) {
        // Chargement du driver
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {}
		 
        try {
			connexion = DriverManager.getConnection(PageAccueil.BDD_CONF.getString("URL_BDD"), PageAccueil.BDD_CONF.getString("ID_BDD"), PageAccueil.BDD_CONF.getString("MDP_BDD"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return connexion;
    }
}
