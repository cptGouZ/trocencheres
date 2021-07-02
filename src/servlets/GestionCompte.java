package servlets;

import bll.ManagerProvider;
import bll.interfaces.IUserManager;
import bo.Utilisateur;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {
                "/profil",
                "/modifcompte",
                "/creationcompte"
        })
public class GestionCompte extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("affichagejsp", "creation");
        req.getRequestDispatcher("WEB-INF/gestioncompte.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserManager um = ManagerProvider.getUserManager();
        Integer userId = null;
        Utilisateur moi = null;
        Utilisateur userBeforeUpdate = null;
        boolean isMeOrImAdmin = false;
        try {
            //Utilisateur qui sera affiché en cas d'erreur d'entré des données
            if (!req.getRequestURI().contains("creationcompte")){
                //Préparation des variables pour les affichages et modif de profil
                userId = Integer.parseInt(req.getParameter("userId"));
                moi = (Utilisateur) req.getSession().getAttribute("userConnected");
                userBeforeUpdate = um.getById(userId);
                isMeOrImAdmin = userBeforeUpdate.getId().equals(moi.getId()) || moi.isAdmin();
            }

            //Affichage d'un profil
            if(req.getRequestURI().contains("profil")){
                req.setAttribute("creditdispo", um.getById(userId).getCreditDispo() );
                req.setAttribute("displayBtnModif", false);
                if(isMeOrImAdmin)
                    req.setAttribute("displayBtnModif", true);
                //Je charge l'utilisateur à afficher
                req.setAttribute("userToDisplay", um.getById(userId));
                req.getRequestDispatcher("WEB-INF/profil.jsp").forward(req, resp);
            }

            //Modification ou Suppression d'un compte
            if(req.getRequestURI().contains("creationcompte")){
                req.setAttribute("affichagejsp", "modification");
                req.getRequestDispatcher("WEB-INF/gestioncompte.jsp").forward(req, resp);
            }

            //Modification ou Suppression d'un compte
            if(req.getRequestURI().contains("modifcompte")){
                req.setAttribute("userToDisplay", um.getById(userId));
                req.setAttribute("affichagejsp", "modification");
                req.getRequestDispatcher("WEB-INF/gestioncompte.jsp").forward(req, resp);
            }
        }catch(GlobalException e){
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            req.getRequestDispatcher("accueil").forward(req, resp);
        }
    }
}
