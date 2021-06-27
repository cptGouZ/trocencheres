package servlets;

import bll.interfaces.IConnexionManager;
import bll.ManagerProvider;
import bo.Utilisateur;
import com.sun.org.apache.xpath.internal.operations.Bool;
import exception.GlobalException;

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

        //Récupérer l'identifiant et le mot de passe

        String identifiant = req.getParameter("login");
        String mdp = req.getParameter("mdp");

        System.out.println(identifiant);
        System.out.println(mdp);

        IConnexionManager icm = ManagerProvider.getConnexionManager();
        Utilisateur utilisateurConnecte = null;
        try {
            utilisateurConnecte = icm.connexionAuSite(identifiant,mdp);
            req.getSession().setAttribute("userConnected",utilisateurConnecte);
            req.getRequestDispatcher("accueilS").forward(req, resp);

        } catch (GlobalException e) {
            e.printStackTrace();

            req.setAttribute("messageErreurLog", e.getMessageErrors());
            req.getRequestDispatcher("WEB-INF/connexion.jsp").forward(req, resp);

            //Mettre un cookie sur la durée de connexion après 5min d'inactivité
         /*   Cookie[] cookies = req.getCookies();
            Cookie deconnectionCookie = new Cookie ("deconnexionAuto", "");
            deconnectionCookie.setMaxAge(15);
            resp.addCookie(deconnectionCookie);*/
        }
    }
}
