package servlets;

import bll.interfaces.IArticleManager;
import bll.ManagerProvider;
import bll.interfaces.ICategorieManager;
import bo.Article;
import bo.Utilisateur;
import exception.GlobalException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/accueil")
public class AccueilServlet extends HttpServlet {
    private final IArticleManager am = ManagerProvider.getArticleManager();
    private final ICategorieManager cateman = ManagerProvider.getCategorieManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //Recuperation des libellés de catégories
            List<bo.Categorie> listeCat = cateman.getAll();
            List<Article> articleList = am.getAll();

            //La servlet envoie l'info à la JSP !
            req.setAttribute("listedesarticles", articleList);
            req.setAttribute("listeCategories", listeCat);
            req.getRequestDispatcher("WEB-INF/accueil.jsp").forward(req, resp);

        } catch (GlobalException e) {
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            req.getRequestDispatcher("WEB-INF/accueil.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //On recupere la categorie, et le nom quand l'utilisateur le saisit
            String textechoix = req.getParameter("textechoix");
            int categorie = 0;
            if(req.getParameter("categorie")!=null)
                categorie = Integer.parseInt(req.getParameter("categorie"));
            //Récupérer eglt les checkbox si elles sont cochees
            boolean ventesTerm = req.getParameter("ven3") != null;
            boolean encheresOuv = req.getParameter("ach1") != null;
            boolean ventesNonDeb = req.getParameter("ven2") != null;
            boolean encheresEnCours = req.getParameter("ach2") != null;
            boolean encheresRemp = req.getParameter("ach3") != null;
            boolean ventesEnCours = req.getParameter("ven1") != null;

            //Declencher requete par tri
            Utilisateur util = null;
            if(req.getSession().getAttribute("luid")!=null)
                util = ManagerProvider.getUserManager().getById((Integer) req.getSession().getAttribute("luid"));
            List<Article> articleList = am.getByCriteria(textechoix, categorie, ventesTerm, encheresOuv, ventesNonDeb, encheresEnCours, encheresRemp, ventesEnCours, util);
            List<bo.Categorie> listeCat = cateman.getAll();

            //Ajout des attributs à la requête
            req.setAttribute("listeCategories", listeCat);
            req.setAttribute("listedesarticles", articleList);
            req.getRequestDispatcher("WEB-INF/accueil.jsp").forward(req, resp);

        } catch (GlobalException e) {
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            req.getRequestDispatcher("WEB-INF/accueil.jsp").forward(req, resp);
        }
    }
}
