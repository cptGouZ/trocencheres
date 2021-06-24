package servlets;

import bll.ManagerProvider;
import bll.interfaces.IArticleManager;
import bll.interfaces.IConnexionManager;
import bo.Adresse;
import bo.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet("/vente")

public class Vente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalDate default_date = LocalDate.now();
        req.setAttribute("dateDuJour", default_date.toString());

        RequestDispatcher rd ;
        rd = req.getRequestDispatcher("WEB-INF/vente.jsp") ;
        rd.forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String article = req.getParameter("article");
        String descritpion = req.getParameter("description");
        Integer categorie = Integer.valueOf(req.getParameter("categorie"));
        Integer prixDepart = Integer.valueOf(req.getParameter("prixDepart"));

        String debutEnchere = req.getParameter("debutEnchere");
        LocalDateTime debutEnchereBll = LocalDateTime.parse(debutEnchere);

        String finEnchere = req.getParameter("finEnchere");
        LocalDateTime finEnchereBll = LocalDateTime.parse("finEnchere");

        String rue = req.getParameter("rue");
        String cpo = req.getParameter("cpo");
        String ville = req.getParameter("ville");

        System.out.println(article);
        System.out.println(descritpion);
        System.out.println(categorie);
        System.out.println(prixDepart);
        System.out.println(debutEnchere);
        System.out.println(finEnchere);
        System.out.println(rue);
        System.out.println(cpo);
        System.out.println(ville);

        IArticleManager icm = ManagerProvider.getArticleManager();

        Article newArticle = null ;
                newArticle = icm.insertNewArticle(
                            article,
                            descritpion,
                            categorie,
                            prixDepart,
                            debutEnchereBll,
                            finEnchereBll);

        Adresse newAdresse = null ;
                newAdresse = icm.insertNewAdresse(
                            rue,
                            cpo,
                            ville);



    }
}
