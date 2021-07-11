package servlets;

import bll.ManagerProvider;
import bll.impl.UserManager;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IArticleManager;
import bll.interfaces.IUserManager;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/vente")

public class ArticleCreationServlet extends HttpServlet {
    IGenericDao<Utilisateur> userDao = DaoProvider.getUtilisateurDao();
    IGenericDao<Categorie> catDao = DaoProvider.getCategorieDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer loggedUserId = (Integer) req.getSession().getAttribute("luid");
            Utilisateur loggedUser = userDao.selectById(loggedUserId);
            LocalDate defaultDate = LocalDate.now();
            req.setAttribute("listeCategories", catDao.selectAll());
            req.setAttribute("dateDuJour", defaultDate.toString());
            req.setAttribute("loggedUserAdresse", loggedUser.getAdresse());
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("WEB-INF/vente.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IArticleManager iam = ManagerProvider.getArticleManager();

        try {
            Article newArt = iam.getFromHttpRequest(req);
            req.setAttribute("articleToDisplay",newArt);
            iam.create(newArt);

            //Affiche un message confirmant la création de l'article en BDD
            String creationArticleOk = "Felicitations votre nouvelle vente a bien ete enregistree" ;
            req.setAttribute("messageConfirm" , creationArticleOk);
            req.setAttribute("articleToDisplay",null);
        } catch (GlobalException e) {
            //Affiche un message d'erreur si la vérification article a échoué
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
        }finally{
            req.getRequestDispatcher("WEB-INF/vente.jsp").forward(req,resp);
        }

    }
}
