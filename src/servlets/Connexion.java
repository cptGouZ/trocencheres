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

@WebServlet(
        urlPatterns = {
                "/connexion",
                "/deconnexion"
        })
public class Connexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURI().contains("deconnexion")){
            req.getSession().setAttribute("userConnected",null);
            resp.sendRedirect("accueil");
        }else if(req.getRequestURI().contains("connexion")){
            req.getRequestDispatcher("WEB-INF/connexion.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupérer l'identifiant et le mot de passe
        String identifiant = req.getParameter("login");
        String mdp = req.getParameter("mdp");

        //Connexion Manager pour transmission des données à la BLL
        IConnexionManager icm = ManagerProvider.getConnexionManager();

        try {
            //Envoi des données à la BLL pour vérifier l'existence de l'utilisateur
            Utilisateur utilisateurConnecte = icm.connexionAuSite(identifiant,mdp);

            //On enregistre l'utilisateur en session
            req.getSession().setAttribute("userConnected",utilisateurConnecte);

            //Puis on renvoi vers la page d'accueil en mode connecté
            resp.sendRedirect("accueil");

        } catch (GlobalException e) {
            e.printStackTrace();
            //Affiche un message d'erreur si la vérification identifiant/mot de passe a échoué
            req.setAttribute("messageErreur", e.getMessageErrors());
            //Et renvoi à la page de connexion
            req.getRequestDispatcher("WEB-INF/connexion.jsp").forward(req, resp);
        }
    }
}
