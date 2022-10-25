package servlets;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bdd.BddMatch;
import bdd.BddEquipe;
import beans.BeanEquipe;
import beans.BeanMatch;


@WebServlet("/Accueil")
public class PageAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Fichier de configuration de la BDD.
	 */
	public static final ResourceBundle BDD_CONF = ResourceBundle.getBundle("BDD");
   
    public PageAccueil() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BddEquipe teams = new BddEquipe();
		List<String> existingTeamsName = teams.recupererNomsEquipes();
		HttpSession session = request.getSession();
		session.setAttribute("existingTeamsName", existingTeamsName);
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			BeanEquipe equipe1 = new BeanEquipe(request.getParameter("name1").toString());
			equipe1.setTour(true);
			
			BeanEquipe equipe2 = new BeanEquipe(request.getParameter("name2").toString());
			
			BeanMatch match = new BeanMatch(equipe1, equipe2);
			
			BddEquipe teams = new BddEquipe();
			teams.addTeam(equipe1);
			teams.addTeam(equipe2);
			
			BddMatch matchs = new BddMatch();
			matchs.addMatch(match, equipe1, equipe2);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("equipe1", equipe1);
			session.setAttribute("equipe2", equipe2);
			session.setAttribute("match", match);
			session.setAttribute("matchs", matchs);
			
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/match.jsp").forward(request, response);
	
	}
}

 

