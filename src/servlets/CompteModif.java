package servlets;

import bll.impl.UserManager;
import bll.interfaces.IUserManager;
import bll.ManagerProvider;
import bo.Adresse;
import bo.Utilisateur;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comptemodif")
public class CompteModif extends HttpServlet {
    public final static int NEW_ACCOUNT = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            IUserManager um = ManagerProvider.getUserManager();
            Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
            if(userConnected!=null){
                //Utilisateur est déjà connecté et demande la modification de son profil
                //Integer userId = Integer.parseInt(req.getParameter("userId"));
                Integer userId = userConnected.getId(); //TODO gestion de l'admin qui entre en get pour modif profil
                Utilisateur userToModify = um.getById(userId);
                boolean admin = false;

                if(!admin && ( !userConnected.getId().equals( userToModify.getId() ) )){
                    //L'utilisateur qui demande la modification de profil n'est pas lui même
                    resp.sendRedirect("accueilS");
                    //req.getRequestDispatcher("accueilS").forward(req, resp);
                }else{
                    //Modification du profil autorisé
                    req.setAttribute("userToDisplay", userConnected);
                    req.setAttribute("affichage", "modification");
                    req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
                }
            }
        } catch (GlobalException e) {
            System.out.println(e.getMessageErrors());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserManager um = ManagerProvider.getUserManager();
        String action = req.getParameter("action").trim();
        Utilisateur userDisplayed = (Utilisateur) req.getSession().getAttribute("userConnected");
        Utilisateur userUpdated = null;
        try {
            //Mise à jour utilisateur
            if ("maj".equals(action)) {
                userUpdated = UserManager.prepareUser(req);
                String pwConfirmation = req.getParameter("confirmPassword");
                String actualPassword = req.getParameter("password");
                um.mettreAJour(userDisplayed, userUpdated, actualPassword, pwConfirmation);

                //Préparation à l'affichage de la page de gestion avec les modifs prise en compte
                userDisplayed = um.getById(userDisplayed.getId());
                req.setAttribute("userToDisplay", userDisplayed);
                req.setAttribute("affichage", "modification");
                req.setAttribute("messageErreur", "Votre compte à bien été modifié");
                req.getSession().setAttribute("userConnected", userDisplayed);
                req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
            }

            //Suppression utilisateur
            if("supprimer".equals(action)){
                um.supprimer(userDisplayed.getId());
                req.getRequestDispatcher("WEB-INF/gestionCompte/confirmDelete.jsp").forward(req, resp);
            }
        }catch(GlobalException e){
            if("maj".equals(action)) {
                req.setAttribute("affichage", "modification");
                req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
                req.setAttribute("userToDisplay", userUpdated);
                req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
            }
            if("supprimer".equals(action)){
                req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
                req.getRequestDispatcher("profil").forward(req, resp);
            }
        }
    }
}
