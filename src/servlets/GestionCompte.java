package servlets;

import bll.FUserManager;
import bll.IUserManager;
import bo.Adresse;
import bo.Utilisateur;
import exception.BLLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gestioncompte")
public class GestionCompte extends HttpServlet {
    public final static int DEFAULT = -1;
    public final static int NEW_ACCOUNT = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IUserManager um = FUserManager.getUserManager();
            int userId = DEFAULT;
            if( req.getParameter("userId") != null )
                userId = Integer.parseInt(req.getParameter("userId"));

            Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
            Utilisateur userToDisplay = null;
            userToDisplay = um.getById(userId);

            //L'utilisateur n'existe pas et nous ne demandons pas une création de compte
            if(userToDisplay==null && userId != NEW_ACCOUNT)
                req.getRequestDispatcher("WEB-INF/accueil.jsp").forward(req, resp);

            //L'utilisateur n'est pas connecté
            if(userConnected==null) {
                //Demande d'affichage du profil
                if(userToDisplay!=null) {
                    req.setAttribute("userDisplayed", userToDisplay);
                    req.getRequestDispatcher("WEB-INF/profil.jsp").forward(req, resp);
                }
                //Demande de création de compte
                if(userId==NEW_ACCOUNT) {
                    req.setAttribute("userId", userId);
                    req.getRequestDispatcher("WEB-INF/gestionCompte.jsp").forward(req, resp);
                }
            }
            if(userConnected!=null){
                //Demande d'affichage du profil
                if(userToDisplay.getId()!=userConnected.getId()) {
                    req.setAttribute("userDisplayed", userToDisplay);
                    req.getRequestDispatcher("WEB-INF/profil.jsp").forward(req, resp);
                }
                //Demande de modification de compte
                if(userToDisplay.getId()==userConnected.getId()) {
                    req.setAttribute("userId", userId);
                    req.getRequestDispatcher("WEB-INF/gestionCompte.jsp").forward(req, resp);
                }
            }
        } catch (BLLException e) {
            e.printStackTrace();
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
/*    private Utilisateur ConstruireUnUtilisateur(HttpServletRequest req, Adresse adrr){

    }
    private Adresse ConstruireUneAdresse(HttpServletRequest req){

    }*/
}
