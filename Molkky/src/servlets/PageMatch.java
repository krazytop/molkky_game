package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.BddTir;
import bdd.BddMatch;
import beans.BeanEquipe;
import beans.BeanMatch;
import beans.BeanTir;

@WebServlet("/Match")
public class PageMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PageMatch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		BeanEquipe equipe1 = (BeanEquipe) request.getSession().getAttribute("equipe1");
		BeanEquipe equipe2 = (BeanEquipe) request.getSession().getAttribute("equipe2");
		BddMatch matchs = (BddMatch) request.getSession().getAttribute("matchs");
		BeanMatch match = (BeanMatch) request.getSession().getAttribute("match");
		List<BeanMatch> listeMatchs = new ArrayList<BeanMatch>();
		List<BeanTir> listeTirs = new ArrayList<BeanTir>();
		
		
		request.setAttribute("equipe1", equipe1);
		request.setAttribute("equipe2", equipe2);
		request.setAttribute("match", match);
		
		BddTir lancer = new BddTir();
		
		if(request.getParameter("score1") != null) {
			int score1 = Integer.parseInt(request.getParameter("score1"));
			lancer.addLance(equipe1, score1);
			listeTirs = lancer.recupererTirs();
			request.setAttribute("listeTirs", listeTirs);
			
			match.setScoreEquipe1(match.getScoreEquipe1() + score1);
			equipe1.setTour(false);
			equipe2.setTour(true);
			
			matchs.updateMatch(match);
			
			if(match.getScoreEquipe1() == 50) {
				listeMatchs = matchs.recupererMatchs();
				request.setAttribute("listeMatchs", listeMatchs); //liste des matchs
				this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp").forward(request, response);
				
			}else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/match.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("score2") != null) {
			int score2 = Integer.parseInt(request.getParameter("score2"));
			lancer.addLance(equipe2, score2);
			listeTirs = lancer.recupererTirs();
			request.setAttribute("listeTirs", listeTirs);
			
			match.setScoreEquipe2(match.getScoreEquipe2() + score2);
			equipe2.setTour(false);
			equipe1.setTour(true);
			
			matchs.updateMatch(match);
			
			if(match.getScoreEquipe2() == 50) {
				listeMatchs = matchs.recupererMatchs();
				request.setAttribute("listeMatchs", listeMatchs); //liste des matchs
				this.getServletContext().getRequestDispatcher("/WEB-INF/resultat.jsp").forward(request, response); 
				
			}else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/match.jsp").forward(request, response);
			}
		}
		
		
	}

}
