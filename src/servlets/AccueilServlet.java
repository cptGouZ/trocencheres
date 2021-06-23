package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccueilServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //On recupere la categorie
        String categorie = req.getParameter("categorie");
        System.out.println(categorie);
        req.setAttribute("categorie ",categorie);

        String categorie2 = req.getParameter("categorie");
        System.out.println(categorie2);
        req.setAttribute("categorie ",categorie2);

        //On recupere egalement les parametres d'inscription
        String pseudo = req.getParameter("pseudo");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String rue = req.getParameter("rue");
        String cpo = req.getParameter("cpo");
        String ville = req.getParameter("ville");
        String password = req.getParameter("password");
        String confirmation = req.getParameter("confirmation");

        req.setAttribute("pseudo", pseudo);
        req.setAttribute("nom", nom);
        req.setAttribute("prenom", prenom);
        req.setAttribute("email", email);
        req.setAttribute("telephone", telephone);
        req.setAttribute("rue", rue);
        req.setAttribute("cpo", cpo);
        req.setAttribute("ville", ville);
        req.setAttribute("password", password);
        req.setAttribute("confirmation", confirmation);

        //La servlet envoie l'info à la JSP
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/accueil.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Je déclare le RequestDispatcher
        RequestDispatcher rd;
        //Pour aller à la JSP ACCUEIL !
        rd = req.getRequestDispatcher("WEB-INF/accueil.jsp");
        rd.forward(req, resp);
    }

}
