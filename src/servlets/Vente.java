package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
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
        String categorie = req.getParameter("categorie");
        String prixDepart = req.getParameter("prixDepart");
        String dateDebut = req.getParameter("dateDebut");
        String dateFin = req.getParameter("dateFin");
        String rue = req.getParameter("rue");
        String cpo = req.getParameter("cpo");
        String ville = req.getParameter("ville");

        System.out.println(article);
        System.out.println(descritpion);
        System.out.println(categorie);
        System.out.println(prixDepart);
        System.out.println(dateDebut);
        System.out.println(dateFin);
        System.out.println(rue);
        System.out.println(cpo);
        System.out.println(ville);



    }
}
