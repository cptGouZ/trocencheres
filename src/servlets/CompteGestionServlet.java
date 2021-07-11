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

@WebServlet("/gestioncompte")
public class CompteGestionServlet extends HttpServlet {
    private final IUserManager um = ManagerProvider.getUserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.valueOf(req.getParameter("uid"));
        Integer loggedUserId = (Integer) req.getSession().getAttribute("luid");
        Utilisateur user = null;
        try {
            if (loggedUserId.equals(userId)) {
                user = um.getById(userId);
                req.setAttribute("affichagejsp", "modification");
                req.setAttribute("userToDisplay", user);
                req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
            }else {
                resp.sendRedirect("profil?uid=" + userId);
            }
        }catch(GlobalException e){
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            resp.sendRedirect("acceuil");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer loggedUserId = (Integer) req.getSession().getAttribute("luid");
        Integer userId = Integer.valueOf(req.getParameter("uid"));
        try{
            //Utilisateurs
            Utilisateur loggedUser =  um.getById(loggedUserId);
            Utilisateur user = um.getById(userId);

            //Mot de passes (le nouveau mot de passe est passé par la requete dans la prepa user)
            String currentPw = req.getParameter("currentPw") == null ? "" : req.getParameter("currentPw").trim();
            String newPwConf = req.getParameter("newPwConf") == null ? "" : req.getParameter("newPwConf").trim();

            //Contrôle que l'utilisateur est bien lui-même
            if( !loggedUserId.equals(userId) ){
                GlobalException.getInstance().addError(UserException.USER_DELETION_ERROR);
                throw GlobalException.getInstance();
            }
            //Contrôle du mot de passe de l'utilisateur connecté pour valider les modifications
            if( !loggedUser.getPassword().equals(currentPw) ){
                GlobalException.getInstance().addError(UserException.PASSWORD_NO_MATCH);
                throw GlobalException.getInstance();
            }

            //Enregistrement du profil et message de confirmation
            Utilisateur userAfterUpdate = um.getFromHttpRequest(req, user.getPassword());
            um.mettreAJour(user, userAfterUpdate, newPwConf);
            req.setAttribute("messageConfirm", GlobalException.getInstance().getMessage(UserException.MODIF_USER_OK));

        }catch(GlobalException e){
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
        }finally {
            req.setAttribute("userToDisplay", um.getFromHttpRequest(req, null));
            req.setAttribute("affichagejsp", "modification");
            req.getRequestDispatcher("WEB-INF/CompteGestion.jsp").forward(req, resp);
        }
    }
}
