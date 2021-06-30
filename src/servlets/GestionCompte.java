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
                "/creationcompte",
                "/enregistrercompte"
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
        Utilisateur userToInsertOrAfterUpdate = null;
        String newPwConfirmation = null;
        String pwControl = null;
        boolean isMeOrImAdmin = false;
        String action = req.getParameter("action");
        try {
            //Utilisateur qui sera affiché en cas d'erreur d'entré des données
            if (req.getParameter("userId")!=null){
                //Préparation des variables pour les affichages et modif de profil
                userId = Integer.parseInt(req.getParameter("userId"));
                moi = (Utilisateur) req.getSession().getAttribute("userConnected");
                userBeforeUpdate = um.getById(userId);
                pwControl = req.getParameter("password");
                isMeOrImAdmin = userBeforeUpdate.getId().equals(moi.getId()) || moi.isAdmin();
            }
            if(req.getRequestURI().contains("enregistrercompte")){
                newPwConfirmation = req.getParameter("confirmPassword").trim();
                userToInsertOrAfterUpdate = um.prepareUser(req);
            }

            /******************************/
            /* PREPARATION DE L'AFFICHAGE */
            /******************************/
            //Affichage d'un profil
            if(req.getRequestURI().contains("profil")){
                req.setAttribute("displayBtnModif", false);
                if(isMeOrImAdmin)
                    req.setAttribute("displayBtnModif", true);
                //Je charge l'utilisateur à afficher
                req.setAttribute("userToDisplay", um.getById(userId));
                req.getRequestDispatcher("WEB-INF/profil.jsp").forward(req, resp);
            }

            //Modification d'un compte
            if(req.getRequestURI().contains("modifcompte")){
                req.setAttribute("userToDisplay", um.getById(userId));
                req.setAttribute("affichagejsp", "modification");
                req.getRequestDispatcher("WEB-INF/gestioncompte.jsp").forward(req, resp);
            }


            /******************************/
            /* ENREGISTREMENT DES DONNEES */
            /******************************/
            //Enregistrer les modifications du profil
            if(req.getRequestURI().contains("enregistrercompte")){
                if("creer".equals(action)) {
                    //Enregistrer les une création
                    um.creer(userToInsertOrAfterUpdate, newPwConfirmation);
                    req.setAttribute("messageConfirm", UserException.CREATION_USER_OK);
                    req.getRequestDispatcher("accueilS").forward(req, resp);
                }
                if("maj".equals(action)){
                    //Enregistrer une modif
                    um.mettreAJour(userBeforeUpdate, userToInsertOrAfterUpdate, pwControl, newPwConfirmation);
                    //Je me met à jour si c'est moi qui ai changé
                    Utilisateur monNouveauMoi = um.getById(moi.getId());
                    req.getSession().setAttribute("userConnected", monNouveauMoi);
                    //Je charge l'utilisateur à afficher
                    req.setAttribute("userToDisplay", um.getById(userId));
                    req.setAttribute("affichagejsp", "modification");
                    req.setAttribute("messageConfirm", UserException.MODIF_USER_OK);
                    req.getRequestDispatcher("WEB-INF/gestioncompte.jsp").forward(req, resp);
                }
                if("supprimer".equals(action)){
                    um.supprimer(userBeforeUpdate.getId());
                    req.getSession().setAttribute("userConnected", null);
                    req.setAttribute("messageConfirm", UserException.SUPPR_USER_OK);
                    req.getRequestDispatcher("accueilS").forward(req, resp);
                }
            }
        }catch(GlobalException e){
            if(req.getRequestURI().contains("enregistrercompte")) {
                if (userId != null) req.setAttribute("affichagejsp", "modification");
                if (userId == null) req.setAttribute("affichagejsp", "creation");
                req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
                req.setAttribute("userToDisplay", userToInsertOrAfterUpdate);
                req.getRequestDispatcher("WEB-INF/gestioncompte.jsp").forward(req, resp);
            }
        }
    }
}
