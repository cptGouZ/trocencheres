package servlets;

import bll.ManagerProvider;
import bll.interfaces.IUserManager;
import bo.Utilisateur;
import exception.GlobalException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profil1")
public class ProfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IUserManager um = ManagerProvider.getUserManager();
            int userId = Integer.parseInt(req.getParameter("userId"));
            Utilisateur userToDisplay = um.getById(userId);
            Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
            if(userToDisplay==null){
                //Redirection : userId inconnu -> page d'accueil
                req.getRequestDispatcher("accueilS").forward(req, resp);
            }else{
                boolean isMe = (userConnected !=null && userToDisplay.getId()== userConnected.getId()) ? true:false;
                req.setAttribute("userId", userId);
                req.setAttribute("userDisplayed", userToDisplay );
                req.setAttribute("showModifButton", isMe);
                //Redirection vers accueil
                req.getRequestDispatcher("WEB-INF/profil.jsp").forward(req, resp);
            }
        } catch (GlobalException e) {
            System.out.println(GlobalException.getInstance().getMessageErrors());
        }
    }
}