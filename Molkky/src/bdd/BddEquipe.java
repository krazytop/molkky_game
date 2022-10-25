package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import beans.BeanEquipe;

public class BddEquipe {
	private Connection connexion;
	
	public List<BeanEquipe> recupererEquipes(){
		List<BeanEquipe> equipes = new ArrayList<BeanEquipe>();
		Statement stat = null;
		ResultSet result = null;
		
		connexion = BDD_load.loadDatabase(connexion);
		try { 
			stat = connexion.createStatement();
			result = stat.executeQuery("SELECT nom FROM equipes;");
			
			while (result.next()) {
				String nom = result.getString("nom");
				
				BeanEquipe equipe = new BeanEquipe(nom);
				equipes.add(equipe);
			}
		}catch(SQLException e) {
		}finally{
			try {
            if (result != null)
                result.close();
            if (stat != null)
                stat.close();
            if (connexion != null)
                connexion.close();
        } catch (SQLException ignore) {
        }
    }
		return equipes;
	}
	
	public void addTeam(BeanEquipe equipe) {
		connexion = BDD_load.loadDatabase(connexion);
		
		try {
			if (!recupererNomsEquipes().contains(equipe.getNom())) {
				connexion = BDD_load.loadDatabase(connexion);
				PreparedStatement prepStat = connexion.prepareStatement("INSERT INTO equipes(nom) VALUE(?);");
				prepStat.setString(1, equipe.getNom());
				prepStat.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> recupererNomsEquipes() {
		List<BeanEquipe> existingTeams = recupererEquipes();
		List<String> existingTeamsName = new ArrayList<String>();
		for (BeanEquipe e : existingTeams ) {
			existingTeamsName.add(e.getNom());
		}
		return existingTeamsName;
		
	}

}
