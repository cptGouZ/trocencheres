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

        //Connexion Manager pour transmission des données à la BLL
        IConnexionManager icm = ManagerProvider.getConnexionManager();

        //Créer l'utilisateur souhaitant se connecté
        Utilisateur utilisateurConnecte = null;


        try {
            //Envoi des données à la BLL pour vérifier son existence et son mot de passe
            utilisateurConnecte = icm.connexionAuSite(identifiant,mdp);

            //On enregistre l'identifiant de l'utilisateur en session
            req.getSession().setAttribute("userConnected",utilisateurConnecte);

            //Puis on renvoi vers la page d'accueil en mode connecté
            req.getRequestDispatcher("accueilS").forward(req, resp);
            //resp.sendRedirect("accueilS");

        } catch (GlobalException e) {
            e.printStackTrace();

            //Affiche un message d'erreur si la vérification identifiant/mot de passe a échoué
            req.setAttribute("messageErreurLog", e.getMessageErrors());
            //Et renvoi à la page de connexion
            req.getRequestDispatcher("WEB-INF/connexion.jsp").forward(req, resp);

        }
    }
}
