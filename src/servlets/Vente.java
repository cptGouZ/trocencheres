package servlets;

import bll.ManagerProvider;
import bll.interfaces.IArticleManager;
import bll.interfaces.IConnexionManager;
import bo.Adresse;
import bo.Article;
import bo.Utilisateur;
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
import java.time.LocalTime;
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

        //LocalTime heureEnchere = LocalTime.now();
        Integer idUtilisateur = ((Utilisateur) req.getSession().getAttribute("userConnected")).getId();
        Utilisateur userEnCours = (Utilisateur) req.getSession().getAttribute("userConnected");

        String article = req.getParameter("article");
        String description = req.getParameter("description");
        Integer categorie = Integer.valueOf(req.getParameter("categorie"));
        Integer prixDepart = Integer.valueOf(req.getParameter("prixDepart"));

        String debutEnchere = req.getParameter("debutEnchere");
        String debutEncherePrecis = (debutEnchere + "T00:00:00") ;
        LocalDateTime debutEnchereBll = LocalDateTime.parse(debutEncherePrecis);

        String finEnchere = req.getParameter("finEnchere");
        String finEncherePrecis = (finEnchere + "T23:59:59") ;
        LocalDateTime finEnchereBll = LocalDateTime.parse(finEncherePrecis);

        String rue = req.getParameter("rue");
        String cpo = req.getParameter("cpo");
        String ville = req.getParameter("ville");


        System.out.println(idUtilisateur);
        System.out.println(article);
        System.out.println(description);
        System.out.println(categorie);
        System.out.println(prixDepart);
        System.out.println("test 3 : " + debutEnchereBll);
        System.out.println("test 4 : " + finEnchereBll);
        System.out.println(rue);
        System.out.println(cpo);
        System.out.println(ville);

        try {
            IArticleManager icm = ManagerProvider.getArticleManager();

            Article newArticle = null ;
                newArticle = icm.insertNewArticle(
                            userEnCours,
                            categorie,
                            article,
                            description,
                            debutEnchereBll,
                            finEnchereBll,
                            prixDepart);

            Adresse newAdresse = null ;
                    newAdresse = icm.insertNewAdresse(
                                rue,
                                cpo,
                                ville);

        } catch (GlobalException e) {
            e.printStackTrace();
        }


    }
}
