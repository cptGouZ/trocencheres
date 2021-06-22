package servlets;

import bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Utilisateur user2 = new Utilisateur();
        //La servlet envoie l'info à la JSP
        req.setAttribute("user", user2);

        //Je déclare le RequestDispatcher
        RequestDispatcher rd;
        //Pour aller à la JSP profil
        rd = req.getRequestDispatcher("WEB-INF/profil.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Je déclare le RequestDispatcher
        RequestDispatcher rd;

        //Pour aller à la JSP profil
        rd = req.getRequestDispatcher("WEB-INF/profil.jsp");
        rd.forward(req, resp);
    }
}