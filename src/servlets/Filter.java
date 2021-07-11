package servlets;

import bll.ManagerProvider;
import bll.interfaces.IUserManager;
import bo.Utilisateur;
import exception.GlobalException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/vente",
        "/profil",
        "/enchere",
        "/gestioncompte",
        "/suppressioncompte",
        "/afficherenchere",
        "/encherir",
        "/retrait"
})
public class Filter implements javax.servlet.Filter {
    private final IUserManager um = ManagerProvider.getUserManager();
    @Override
    public void doFilter(ServletRequest reqFilter, ServletResponse respFilter, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)reqFilter;
        HttpServletResponse resp = (HttpServletResponse)respFilter;
        boolean isConnected = req.getSession().getAttribute("luid")!=null;
        if(!isConnected) {
            if(req.getParameter("uid")!=null)
                req.setAttribute("uid", req.getParameter("uid"));
            req.setAttribute("destination", req.getServletPath());
            req.getSession().setAttribute("pseudo", null);
            req.getSession().setAttribute("credit", null);
            req.getSession().setAttribute("creditdispo", null);
            req.getRequestDispatcher("connexion").forward(req, resp);
        }
        int loggedUserId = (Integer)req.getSession().getAttribute("luid");
        try {
            Utilisateur loggedUser = um.getById(loggedUserId);
            req.getSession().setAttribute("pseudo", loggedUser.getPseudo());
            req.getSession().setAttribute("credit", loggedUser.getCredit());
            req.getSession().setAttribute("creditdispo", loggedUser.getCreditDispo());
        } catch (GlobalException e) {
            filterChain.doFilter(req, resp);
        }
        filterChain.doFilter(req, resp);
    }
}
