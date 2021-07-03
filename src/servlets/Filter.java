package servlets;

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
        "/modificationcompte", "/suppressioncompte"
})
public class Filter implements javax.servlet.Filter {
    @Override
    public void doFilter(ServletRequest reqFilter, ServletResponse respFilter, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)reqFilter;
        HttpServletResponse resp = (HttpServletResponse)respFilter;
        boolean isConnected = req.getSession().getAttribute("luid")!=null;
        if(!isConnected) {
            req.setAttribute("destination", req.getServletPath());
            req.getRequestDispatcher("connexion").forward(req, resp);
        }
        filterChain.doFilter(req, resp);
    }
}
