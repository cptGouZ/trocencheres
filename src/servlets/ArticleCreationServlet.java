package servlets;

import bll.ManagerProvider;
import bll.interfaces.IArticleManager;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.ArticleException;
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
            req.setAttribute("message", GlobalException.getInstance().getMessageErrors());
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
            //Si tout s'est bien passé
            req.setAttribute("messageConfirm" , GlobalException.getInstance().getMessage(ArticleException.ARTICLE_CREATION_OK));
            req.setAttribute("articleToDisplay",null);
        } catch (GlobalException e) {
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
        }finally{
            req.getRequestDispatcher("WEB-INF/vente.jsp").forward(req,resp);
        }

    }
}
