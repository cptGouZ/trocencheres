package servlets;

import bo.Adresse;
import bo.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gestioncompte")
public class GestionCompte extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = -1;
        if( req.getParameter("userId") != null )
            userId = Integer.parseInt(req.getParameter("userId"));

        Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
        Utilisateur userToDisplay = getUserById(userId);
        if(userToDisplay == null)
            //Si l'utilisateur demandé dans la base n'éxiste pas on retourne vers la page d'accueil
            req.getRequestDispatcher("WEB-INF/accueil.jsp").forward(req, resp);
        if(userToDisplay.getId()!=userConnected.getId()) {
            //Affichage d'un profil
            req.setAttribute("user", userToDisplay);
            req.getRequestDispatcher("WEB-INF/profil.jsp").forward(req, resp);
        }
        if(userId==-1 || userToDisplay.getId()==userConnected.getId()){
            //Creation ou Modif compte
            req.setAttribute("user", getUserById(userConnected.getId()));
            req.getRequestDispatcher("WEB-INF/gestionCompte.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        Utilisateur userConnected = (Utilisateur)req.getSession().getAttribute("userConnected");
        String pseudo = req.getParameter("pseudo");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String tel = req.getParameter("tel");
        String rue = req.getParameter("rue");
        String cpo = req.getParameter("cpo");
        String ville = req.getParameter("ville");
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");
        String action = req.getParameter("action");
        Adresse adresseJsp = new Adresse(0, null, rue, cpo, ville);
        Utilisateur userJsp = new Utilisateur(0, adresseJsp, pseudo, nom, prenom, email, tel, password, 0, false);
        switch(action){
            case "save" :
                System.out.println("Create or save User");
                break;

            case "delete" :

                break;
        }
        Utilisateur currentUser = new Utilisateur();
        currentUser.setPseudo(pseudo);
        req.getRequestDispatcher("WEB-INF/gestionCompte.jsp").forward(req, resp);
    }


    private Utilisateur getUserById(int id){
        Utilisateur retour =  new Utilisateur();
        retour.setId(id);
        return retour;
    }
/*    private Utilisateur ConstruireUnUtilisateur(HttpServletRequest req, Adresse adrr){

    }
    private Adresse ConstruireUneAdresse(HttpServletRequest req){

    }*/
}
