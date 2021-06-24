package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deconnexion")
public class deconnexion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean deconnexion ;

        deconnexion = Boolean.parseBoolean((req.getParameter("seDeconnecter")));

        //System.out.println("test1 " + deconnexion);

        if(deconnexion == true) {
           // System.out.println("test2 " + deconnexion);
            req.getSession().setAttribute("userConnected",null);
            deconnexion = false ;
            req.getRequestDispatcher("accueilS").forward(req, resp);
        }
    }
}
