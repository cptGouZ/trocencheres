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
                "/supprimercompte"
        })
public class CompteModif1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IUserManager um = ManagerProvider.getUserManager();
            //Création d'un compte
            if(req.getRequestURI().contains("creationcompte")){
                Utilisateur newUser = um.prepareUser(req);
                String confirmPassword = req.getParameter("confirmPassword").trim();

                    um.creer(newUser, confirmPassword);
                req.setAttribute("messageConfirm", UserException.CREATION_USER_OK);
                req.getRequestDispatcher("accueilS").forward(req, resp);
            }
        } catch (GlobalException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        IUserManager um = ManagerProvider.getUserManager();
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        Utilisateur moi = (Utilisateur) req.getSession().getAttribute("userConnected");
        Utilisateur userBeforeUpdate = um.getById(userId);
        Utilisateur userAfterUpdate = um.prepareUser(req);
        String newPwConfirmation = req.getParameter("confirmPassword");
        String pwControl = req.getParameter("password");
        boolean isMeOrImAdmin = userBeforeUpdate.getId().equals(moi.getId()) || moi.isAdmin();

        //Affichage d'un profil
        if(req.getRequestURI().contains("profil")){
            req.setAttribute("affichagejsp", "afficher");
            req.setAttribute("displayBtnModif", false);
            if(isMeOrImAdmin)
                req.setAttribute("displayBtnModif", true);
            //Je charge l'utilisateur à afficher
            req.setAttribute("userToDisplay", um.getById(userId));
            req.getRequestDispatcher("WEB-INF/profil.jsp").forward(req, resp);
        }

        //Modification d'un compte
        if(req.getRequestURI().contains("modifcompte")){
            um.mettreAJour(userBeforeUpdate, userAfterUpdate, pwControl, newPwConfirmation);
            //Je me met à jour si c'est moi qui ai changé
            Utilisateur monNouveauMoi = um.getById(moi.getId());
            req.getSession().setAttribute("userConnected", monNouveauMoi);
            //Je charge l'utilisateur à afficher
            req.setAttribute("userToDisplay", um.getById(userId));
            req.setAttribute("affichagejsp", "modification");
            req.setAttribute("messageConfirm", UserException.MODIF_USER_OK);
            req.getRequestDispatcher("WEB-INF/compte.jsp").forward(req, resp);
        }

        //Suppression d'un compte
        if(req.getRequestURI().contains("supprimercompte")){
            um.supprimer(userBeforeUpdate.getId());
            req.setAttribute("messageConfirm", UserException.SUPPR_USER_OK);
            req.getRequestDispatcher("accueilS").forward(req, resp);
        }

        }catch(GlobalException e){
            /*if("maj".equals(action) || "supprimer".equals(action)) {
                req.setAttribute("affichage", "modification");
                req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
                req.setAttribute("userToDisplay", userUpdated);
                req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
            }*/
        }
    }
}
