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

@WebServlet("/suppressioncompte")
public class CompteSuppressionServlet extends HttpServlet {
    private final IUserManager um = ManagerProvider.getUserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("uid"));
        Utilisateur user = null;
        try {
            user = um.getById(userId);
            if(user==null) {
                GlobalException.getInstance().addError(UserException.UNKNOWN_USER);
                throw GlobalException.getInstance();
            }
        } catch (GlobalException e) {
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessage(UserException.UNKNOWN_USER));
        }finally {
            req.getRequestDispatcher("WEB-INF/CompteSuppression.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("uid"));
        Integer loggedUserId = (Integer) req.getSession().getAttribute("luid");
        //Utilisateurs
        try {
            Utilisateur loggedUser = um.getById(loggedUserId);
            if(!(loggedUserId.equals(userId) || loggedUser.isAdmin())){
                GlobalException.getInstance().addError(UserException.USER_DELETION_UNAUTHORIZED);
                throw GlobalException.getInstance();
            }
            um.supprimer(userId);
            req.getSession().setAttribute("luid", null);
            req.setAttribute("messageConfirm", GlobalException.getInstance().getMessage(UserException.SUPPR_USER_OK));
            req.getRequestDispatcher("WEB-INF/Accueil.jsp").forward(req, resp);
        } catch (GlobalException e) {
            req.setAttribute("uid", userId);
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            req.getRequestDispatcher("WEB-INF/CompteSuppression.jsp").forward(req, resp);
        }
    }
}
