package servlets;

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
import java.util.List;

public class AccueilServlet extends HttpServlet {

    private Object Utilisateur;
    private Object Categorie;

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //On recupere la categorie et le nom quand l'utilisateur les saisit
        String textArticle = req.getParameter("texteart");
        String categorie = req.getParameter("categorie");

        //Récupérer eglt les checkbox
        boolean encheresOuv = req.getParameter( "ach1" ) != null;
        boolean encheresEnCours = req.getParameter( "ach2" ) != null;
        boolean encheresRemp = req.getParameter( "ach3" ) != null;
        boolean ventesEnCours = req.getParameter( "ven1" ) != null;
        boolean encheresNonDeb = req.getParameter( "ven2" ) != null;
        boolean encheresTerm = req.getParameter( "ven3" ) != null;

        //Affichage par categorie
        //On fait appel a ArticleManager
        List<Article> articleList2 = new ArrayList<>();
        IArticleManager am2 = ManagerProvider.getArticleManager();
        //Mettre les valeur de checkbox dans les paramètres de la requête
        //articleList2 = am2.getByCriterias(textArticle, categorie, encheresOuv, encheresEnCours, encheresRemp, ventesEnCours, encheresNonDeb, encheresTerm);

        //La servlet envoie l'info à la JSP !
        req.setAttribute("listedesarticles", articleList2);

        //Je déclare le RequestDispatcher
        RequestDispatcher rd;
        //Pour aller à la JSP ACCUEIL !
        rd = req.getRequestDispatcher("WEB-INF/accueil.jsp");
        rd.forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //Test affichage article
//        Article objet1 = new Article();
//        //La servlet envoie l'info à la JSP
//        req.setAttribute("objet1", objet1);

        //Affichage de tous les articles
        //On fait appel a ArticleManager
        List<Article> articleList = new ArrayList<>();
        IArticleManager am = ManagerProvider.getArticleManager();
        try {
            articleList = am.getAll();

        } catch (GlobalException e) {
            e.printStackTrace();
        }

        //La servlet envoie l'info à la JSP !
        req.setAttribute("listedesarticles", articleList);

        //Je déclare le RequestDispatcher
        RequestDispatcher rd;
        //Pour aller à la JSP ACCUEIL !
        rd = req.getRequestDispatcher("WEB-INF/accueil.jsp");
        rd.forward(req, resp);
    }

}
