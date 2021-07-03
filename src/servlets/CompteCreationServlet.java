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

@WebServlet("/creationcompte")
public class CompteCreationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("affichagejsp", "creation");
        req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserManager um = ManagerProvider.getUserManager();
        String newPwConf = req.getParameter("newPwConf").trim();
        Utilisateur newUser = null;
        try{
            newUser = um.getUserFromRequest(req, newPwConf);
            um.creer(newUser, newPwConf);
            req.getSession().setAttribute("loggedUserId", newUser.getId());
            req.setAttribute("msgConfirm", GlobalException.getInstance().getMessage(UserException.CREATION_USER_OK));
            resp.sendRedirect("accueil");
        }catch(GlobalException e){
            req.setAttribute("affichagejsp", "creation");
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            req.setAttribute("userToDisplay", newUser);
            req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
        }
    }
}
