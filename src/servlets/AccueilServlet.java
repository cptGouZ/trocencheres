package servlets;

import bll.impl.ArticleManager;
import bll.interfaces.IArticleManager;
import bll.ManagerProvider;
import bo.Article;
import exception.GlobalException;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccueilServlet extends HttpServlet {

    private Object Utilisateur;
    private Object Categorie;

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //On recupere la categorie, et le nom quand l'utilisateur le saisit
        String textechoix = req.getParameter("textechoix");
        String categorie = req.getParameter("categorie");

        //Récupérer eglt les checkbox
        boolean encheresOuv = req.getParameter( "ach1" ) != null;
        boolean encheresEnCours = req.getParameter( "ach2" ) != null;
        boolean encheresRemp = req.getParameter( "ach3" ) != null;
        boolean ventesEnCours = req.getParameter( "ven1" ) != null;
        boolean ventesNonDeb = req.getParameter( "ven2" ) != null;
        boolean ventesTerm = req.getParameter( "ven3" ) != null;

        //Affichage par categorie
        //On fait appel a ArticleManager
        List<Article> articleList2 = new ArrayList<>();
        IArticleManager am2 = ManagerProvider.getArticleManager();
        articleList2 = am2.getByCriteres(textechoix, categorie);
        req.setAttribute("listedesarticles", articleList2);

        List<String> listeCat;
        listeCat = am2.getLibellesCategorie();
        req.setAttribute("libellesCategories", listeCat);

        if(ventesTerm == true) {
        List<Article> articleList3 = new ArrayList<>();
        articleList3 = am2.selectByCheck();
        req.setAttribute("listedesarticles", articleList3);}

        //Redirection vers accueil
        RequestDispatcher rd;
        rd = req.getRequestDispatcher("WEB-INF/accueil.jsp");
        rd.forward(req, resp);
        //}
        //Si absence de tri, on appelle le SelectAll donc on rappelle la méthode doGet afin d'afficher tous les articles
        //else {this.doGet(req, resp);}
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Affichage de tous les articles
        //On fait appel a ArticleManager
        List<Article> articleList = new ArrayList<>();
        IArticleManager am = ManagerProvider.getArticleManager();

        //Recuperation des libellés de catégories
        List<String> listeCat;
        am.getLibellesCategorie();

        try {
            articleList = am.getAll();
            //System.out.println("titi" + articleList);

        } catch (GlobalException e) {
            e.printStackTrace();
        }

        listeCat = am.getLibellesCategorie();
        //System.out.println("tutu" + listeCat);

        //La servlet envoie l'info à la JSP !
        req.setAttribute("listedesarticles", articleList);
        req.setAttribute("libellesCategories", listeCat);

        //Redirection vers accueil
        RequestDispatcher rd;
        rd = req.getRequestDispatcher("WEB-INF/accueil.jsp");
        rd.forward(req, resp);
    }

}
