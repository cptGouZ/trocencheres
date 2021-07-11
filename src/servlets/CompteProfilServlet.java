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

@WebServlet ("/profil")
public class CompteProfilServlet extends HttpServlet {
    private final IUserManager um = ManagerProvider.getUserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("uid"));
        Integer loggedUserId = (Integer) req.getSession().getAttribute("luid");
        Utilisateur userToDisplay = null;
        try {
            userToDisplay = um.getById(userId);
            req.setAttribute("displayBtnModif", loggedUserId.equals(userId) ? true : false);
            req.setAttribute("creditdispo", userToDisplay.getCreditDispo()); //TODO passer ca dans le filtre
        } catch (GlobalException e) {
            req.setAttribute("messageErreur", UserException.UNKNOWN_USER);
        }finally{
            req.setAttribute("userToDisplay", userToDisplay);
            req.getRequestDispatcher("WEB-INF/CompteProfil.jsp").forward(req, resp);
        }
    }
}
