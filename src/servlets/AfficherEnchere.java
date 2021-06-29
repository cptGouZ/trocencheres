package servlets;

import bll.ManagerProvider;
import bll.interfaces.IArticleManager;
import bll.interfaces.IEnchereManager;
import bo.Article;
import bo.Enchere;
import exception.GlobalException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/afficherenchere")
public class AfficherEnchere extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IEnchereManager em = ManagerProvider.getEnchereManager();
        IArticleManager am = ManagerProvider.getArticleManager();
//        Integer idArticle = Integer.valueOf(req.getParameter("idArticle"));
        int idArticle = 2;
        try {
            Article articleToDisplay = am.getById(idArticle);
            Enchere lastEnchere = em.getLastEnchereOnArticle(idArticle);
            req.setAttribute("article", articleToDisplay);
            req.setAttribute("enchere", lastEnchere);
            req.setAttribute("action", "afficher");
            req.getRequestDispatcher("WEB-INF/Enchere.jsp").forward(req, resp);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
    }
}
