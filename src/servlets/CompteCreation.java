package servlets;

import bll.ManagerProvider;
import bll.impl.UserManager;
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

@WebServlet("/comptecreation")
public class CompteCreation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
        if(userConnected!=null) {
            //Utilisateur déjà connecté qui demande un nouveau compte je redirige vers accueil
            req.getRequestDispatcher("accueilS").forward(req, resp);
        }else{
            //Création du compte autorisé
            req.setAttribute("affichage", "creation");
            req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserManager um = ManagerProvider.getUserManager();
        Utilisateur newUser = null;
        try {
            newUser = UserManager.prepareUser(req);
            String confirmPassword = req.getParameter("confirmPassword").trim();
            um.creer(newUser, confirmPassword);
            req.setAttribute("messageConfirm", UserException.CREATION_USER_OK);
            req.getRequestDispatcher("accueilS").forward(req, resp);
        } catch (GlobalException e) {
            req.setAttribute("affichage", "creation");
            req.setAttribute("userToDisplay", newUser);
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
        }
    }
}