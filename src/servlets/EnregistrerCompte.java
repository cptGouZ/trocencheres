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

@WebServlet("/enregistrercompte")
public class EnregistrerCompte extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserManager um = ManagerProvider.getUserManager();
        Integer userId = null;
        Utilisateur moi = null;
        Utilisateur userBeforeUpdate = null;
        String pwControl = null;
        String newPwConfirmation = null;
        Utilisateur userToInsertOrAfterUpdate =null;
        String action = req.getParameter("action");
        try{
            newPwConfirmation = req.getParameter("confirmPassword").trim();
            userToInsertOrAfterUpdate = um.prepareUser(req);

            //Utilisateur qui sera affiché en cas d'erreur d'entré des données
            if (!"creer".equals(action)){
                //Préparation des variables pour les affichages et modif de profil
                userId = Integer.parseInt(req.getParameter("userId"));
                moi = (Utilisateur) req.getSession().getAttribute("userConnected");
                userBeforeUpdate = um.getById(userId);
                pwControl = req.getParameter("password");
            }
            if("creer".equals(action)) {
                //Enregistrer les une création
                um.creer(userToInsertOrAfterUpdate, newPwConfirmation);
                req.setAttribute("messageConfirm", UserException.CREATION_USER_OK);
                resp.sendRedirect("accueil");
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
                resp.sendRedirect("accueil");
            }
        }catch(GlobalException e){
            if ("maj".equals(action)) req.setAttribute("affichagejsp", "modification");
            if ("creer".equals(action)) req.setAttribute("affichagejsp", "creation");
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            req.setAttribute("userToDisplay", userToInsertOrAfterUpdate);
            req.getRequestDispatcher("WEB-INF/gestioncompte.jsp").forward(req, resp);
        }
    }
}
