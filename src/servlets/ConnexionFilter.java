package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/vente",
        "/profil",
        "/enchere"
})
public class ConnexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest reqFilter, ServletResponse respFilter, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)reqFilter;
        HttpServletResponse resp = (HttpServletResponse)respFilter;
        boolean isConnected = req.getSession().getAttribute("userConnected")!=null;
        if(!isConnected) {
            req.getRequestDispatcher("connexion").forward(req, resp);
        }
        filterChain.doFilter(req, resp);
    }
}
