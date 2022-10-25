package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import beans.BeanEquipe;
import beans.BeanMatch;

public class BddMatch {
	private Connection connexion;
	String nomEquipe1;
	String nomEquipe2;
	Integer scoreEquipe1;
	Integer scoreEquipe2;
	
	public List<BeanMatch> recupererMatchs(){
		List<BeanMatch> matchs = new ArrayList<BeanMatch>();
		Statement stat = null;
		ResultSet mat = null;
		ResultSet result1 = null;
		ResultSet result2 = null;
		ResultSet score1 = null;
		ResultSet score2 = null;
		List<String> idMatch = new ArrayList<String>();
		
		connexion = BDD_load.loadDatabase(connexion);
		
		try { 
			stat = connexion.createStatement();
			mat = stat.executeQuery("SELECT idMatch FROM matchs");
			while(mat.next()) {
				idMatch.add(mat.getString("idMatch"));
			}
			
			for(int i=0; i< idMatch.size(); i++) {
				result1 = stat.executeQuery("SELECT nom FROM equipes, matchs WHERE matchs.idMatch = " + idMatch.get(i) + " AND matchs.idEquipe1 = equipes.idEquipe");
				
				if(result1.next()) {
					nomEquipe1 = result1.getString("nom");
				}
				score1 = stat.executeQuery("SELECT scoreEquipe1 FROM matchs WHERE matchs.idMatch = " + idMatch.get(i));
				
				if(score1.next()) {
					scoreEquipe1 = Integer.parseInt(score1.getString("scoreEquipe1"));
				}
				
				result2 = stat.executeQuery("SELECT nom FROM equipes, matchs WHERE matchs.idMatch = " + idMatch.get(i) + " AND matchs.idEquipe2 = equipes.idEquipe");
				
				if(result2.next()) {
					nomEquipe2 = result2.getString("nom");
				}
				
				score2 = stat.executeQuery("SELECT scoreEquipe2 FROM matchs WHERE matchs.idMatch = " + idMatch.get(i));
				
				if(score2.next()) {
					scoreEquipe2 = Integer.parseInt(score2.getString("scoreEquipe2"));
				}
				
				BeanMatch match = new BeanMatch(new BeanEquipe(nomEquipe1), new BeanEquipe(nomEquipe2));
				match.setScoreEquipe1(scoreEquipe1);
				match.setScoreEquipe2(scoreEquipe2);
				matchs.add(match);
				
			}
			
		}catch(SQLException e) {
			
		}
		return matchs;
	}

public void addMatch(BeanMatch match, BeanEquipe equipe1, BeanEquipe equipe2) {
	connexion = BDD_load.loadDatabase(connexion);
	Statement stat = null;
	ResultSet resultID1 = null;
	ResultSet resultID2 = null;
	String id1 = null;
	String id2 = null;
	
	try {
		stat = connexion.createStatement();
		
		resultID1 = stat.executeQuery("SELECT idEquipe FROM equipes WHERE nom = '" + equipe1.getNom() + "'");
		while(resultID1.next()) {
			id1 = resultID1.getString("idEquipe");
		}
		
		resultID2 = stat.executeQuery("SELECT idEquipe FROM equipes WHERE nom = '" + equipe2.getNom() + "'");
		while(resultID2.next()) {
			id2 = resultID2.getString("idEquipe");
		}
		
		
		PreparedStatement prepStat = connexion.prepareStatement("INSERT INTO matchs(idEquipe1, idEquipe2, scoreEquipe1, scoreEquipe2) VALUE(?, ?, ?, ?);");
		prepStat.setString(1, id1);
		prepStat.setString(2, id2);
		prepStat.setString(3, String.valueOf(match.getScoreEquipe1()));
		prepStat.setString(4, String.valueOf(match.getScoreEquipe2()));
		prepStat.executeUpdate();
			
	}catch(SQLException e) {
		e.printStackTrace();
	}
}

public void updateMatch(BeanMatch match) {
	connexion = BDD_load.loadDatabase(connexion);
	Statement stat = null;
	ResultSet resultIDMatch = null;
	Integer idMatch = null;
	
	try {
		stat = connexion.createStatement();
		
		resultIDMatch = stat.executeQuery("SELECT idMatch FROM matchs ORDER BY idMatch DESC LIMIT 1;");
		while(resultIDMatch.next()) {
			idMatch = Integer.parseInt(resultIDMatch.getString("idMatch"));
		}

		PreparedStatement prepStat = connexion.prepareStatement("UPDATE matchs SET scoreEquipe1 = ?, scoreEquipe2 = ? WHERE idMatch = ?");
		prepStat.setInt(1, match.getScoreEquipe1());
		prepStat.setInt(2, match.getScoreEquipe2());
		prepStat.setInt(3, idMatch);
		prepStat.executeUpdate();
			
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}

}
