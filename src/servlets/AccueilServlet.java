package servlets;

import bll.impl.ArticleManager;
import bll.interfaces.IArticleManager;
import bll.ManagerProvider;
import bll.interfaces.ICategorieManager;
import bo.Article;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //On recupere la categorie, et le nom quand l'utilisateur le saisit
            String textechoix = req.getParameter("textechoix");
            Integer categorie = 0;
            if(req.getParameter("categorie")!=null)
                categorie = Integer.valueOf(req.getParameter("categorie"));
            //Récupérer eglt les checkbox si elles sont cochees
            boolean ventesTerm = req.getParameter("ven3") != null;
            boolean encheresOuv = req.getParameter("ach1") != null;
            boolean ventesNonDeb = req.getParameter("ven2") != null;
            boolean encheresEnCours = req.getParameter("ach2") != null;
            boolean encheresRemp = req.getParameter("ach3") != null;
            boolean ventesEnCours = req.getParameter("ven1") != null;

            //On fait appel a ArticleManager
            IArticleManager am2 = ManagerProvider.getArticleManager();
            //Preparation des catégories et définition de l'attribut
            ICategorieManager cateman = ManagerProvider.getCategorieManager();
            List<bo.Categorie> listeCat = cateman.getAll();

            //Declencher requete par tri
            Utilisateur util = (bo.Utilisateur) req.getSession().getAttribute("userConnected");
            List<Article> articleList = am2.getByCrit2(textechoix, categorie, ventesTerm, encheresOuv, ventesNonDeb, encheresEnCours, encheresRemp, ventesEnCours, util);

            //Ajout des attributs à la requête
            req.setAttribute("listeCategories", listeCat);
            req.setAttribute("listedesarticles", articleList);

            //Redirection vers accueil
            RequestDispatcher rd;
            rd = req.getRequestDispatcher("WEB-INF/accueil.jsp");
            rd.forward(req, resp);
        } catch (GlobalException e) {
            System.out.println(e.getMessageErrors());
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //On fait appel a ArticleManager
            List<Article> articleList = new ArrayList<>();
            IArticleManager am = ManagerProvider.getArticleManager();

            //Recuperation des libellés de catégories
            ICategorieManager cateman = ManagerProvider.getCategorieManager();
            List<bo.Categorie> listeCat = cateman.getAll();

            articleList = am.getAll();

            //La servlet envoie l'info à la JSP !
            req.setAttribute("listedesarticles", articleList);
            req.setAttribute("listeCategories", listeCat);

            //Redirection vers accueil
            RequestDispatcher rd;
            rd = req.getRequestDispatcher("WEB-INF/accueil.jsp");
            rd.forward(req, resp);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
    }

}
