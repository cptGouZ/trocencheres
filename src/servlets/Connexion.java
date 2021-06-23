package servlets;

import bll.FConnexionManager;
import bll.IConnexionManager;
import bll.impl.ConnexionManager;
import bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/connexion")
public class Connexion extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String jspConnexion = "WEB-INF/connexion.jsp";
        String jspReinitMdp = "WEB-INF/reinitMdp.jsp";

        String redirectionJsp = jspConnexion ;

        if(req.getParameter("id")!=null)
        {
            redirectionJsp = jspReinitMdp ;
        }

        RequestDispatcher rd ;
        rd = req.getRequestDispatcher(redirectionJsp) ;
        rd.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        //Mettre un cookie sur le rester connecté
       /* Cookie[] cookies = req.getCookies();
        Cookie connectionCookie = new Cookie ()*/


        //Récupérer l'identifiant et le mot de passe

        String identifiant = req.getParameter("login");
        String mdp = req.getParameter("mdp");

        System.out.println(identifiant);
        System.out.println(mdp);

        IConnexionManager icm = FConnexionManager.getConnexionManager();
        icm.connexionAuSite(identifiant,mdp);


    }
}
