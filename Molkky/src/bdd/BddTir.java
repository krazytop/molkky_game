package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import beans.BeanEquipe;
import beans.BeanTir;

public class BddTir {
	private Connection connexion;
	
	public List<BeanTir> recupererTirs(){
		List<BeanTir> tirs = new ArrayList<BeanTir>();
		Statement stat = null;
		ResultSet lance = null;
		ResultSet result = null;
		String nomEquipe;
		List<String> idLance = new ArrayList<String>();
		List<String> idEquipe= new ArrayList<String>();
		List<String> valeur = new ArrayList<String>();
		
		connexion = BDD_load.loadDatabase(connexion);
		
		try { 
			stat = connexion.createStatement();
			lance = stat.executeQuery("SELECT * FROM lances WHERE idMatch = (SELECT idMatch FROM matchs ORDER BY idMatch DESC LIMIT 1);");
			while(lance.next()) {
				idLance.add(lance.getString("idLance"));
				idEquipe.add(lance.getString("idEquipe"));
				valeur.add(lance.getString("valeurLancer"));
				
			}
			for(int i=0; i< idEquipe.size(); i++) {
				result = stat.executeQuery("SELECT nom FROM equipes WHERE idEquipe = " + Integer.parseInt(idEquipe.get(i)));
				
				if(result.next()) {
					nomEquipe = result.getString("nom");
					BeanTir tir = new BeanTir(new BeanEquipe(nomEquipe), Integer.parseInt(valeur.get(i)));
					tirs.add(tir);
				}
				
			}
		} catch (SQLException e) {
		       e.printStackTrace();
		}
		return tirs;
	}

public void addLance(BeanEquipe equipe, int score) {
	connexion = BDD_load.loadDatabase(connexion);
	Statement stat = null;
	ResultSet resultIDEquipe = null;
	ResultSet resultIDMatch = null;
	String idEquipe = null;
	String idMatch = null;
	
	try {
		stat = connexion.createStatement();
		
		resultIDEquipe = stat.executeQuery("SELECT idEquipe FROM equipes WHERE nom = '" + equipe.getNom() + "'");
		while(resultIDEquipe.next()) {
			idEquipe = resultIDEquipe.getString("idEquipe");
		}
		
		resultIDMatch = stat.executeQuery("SELECT idMatch FROM matchs ORDER BY idMatch DESC LIMIT 1;");
		while(resultIDMatch.next()) {
			idMatch = resultIDMatch.getString("idMatch");
		}
		
		PreparedStatement prepStat = connexion.prepareStatement("INSERT INTO lances(idMatch, idEquipe, valeurLancer) VALUE(?, ?, ?);");
		prepStat.setString(1,  idMatch);
		prepStat.setString(2,  idEquipe);
		prepStat.setInt(3, score);
		prepStat.executeUpdate();
			
	}catch(SQLException e) {
		e.printStackTrace();
	}
}

}
