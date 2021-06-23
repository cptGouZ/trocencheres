package servlets;

import bll.IUserManager;
import bll.ManagerProvider;
import bo.Adresse;
import bo.Utilisateur;
import exception.BLLException;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/gestioncompte")
public class GestionCompte extends HttpServlet {
    public final static int DEFAULT = -1;
    public final static int NEW_ACCOUNT = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            IUserManager um = ManagerProvider.getUserManager();
            int userId = DEFAULT;
            if( req.getParameter("userId") != null )
                userId = Integer.parseInt(req.getParameter("userId"));

            Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
            Utilisateur userToDisplay = null;
            userToDisplay = um.getById(userId);
            System.out.println(userToDisplay);
            System.out.println(userId);
            //L'utilisateur n'existe pas et nous ne demandons pas une création de compte
            if(userToDisplay==null && userId != NEW_ACCOUNT) {
                req.getRequestDispatcher("accueilS").forward(req, resp);
            }
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
            req.getRequestDispatcher("accueilS").forward(req, resp);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserManager um = ManagerProvider.getUserManager();

        //Récupération des données de la page
        String pseudo = req.getParameter("pseudo").trim();
        String nom = req.getParameter("nom").trim();
        String prenom = req.getParameter("prenom").trim();
        String email = req.getParameter("email").trim();
        String tel = req.getParameter("tel").trim();
        String rue = req.getParameter("rue").trim();
        String cpo = req.getParameter("cpo").trim();
        String ville = req.getParameter("ville").trim();
        String password = req.getParameter("password").trim();
        String newPassword = req.getParameter("newPassword").trim();
        String confirmPassword = req.getParameter("confirmPassword").trim();
        String action = req.getParameter("action").trim();
        Utilisateur concernedUser = um.getById(Integer.parseInt(req.getParameter("userId")));

        //Création de l'utilisateur
        Utilisateur userToUpdate = null;
        if(concernedUser==null){
            Adresse adresse = new Adresse(rue, cpo, ville, true);
            userToUpdate = new Utilisateur(adresse, pseudo, nom, prenom, email, tel, password, 0, false);
            um.create(userToUpdate);
            req.getRequestDispatcher("WEB-INF/gestionCompte/confirmCreation.jsp").forward(req, resp);
        }

        //Mise à jour utilisateur
        if(concernedUser!=null && "update".equals(action)){
            userToUpdate = concernedUser;
            userToUpdate.setPseudo(pseudo);
            //userToUpdate.setAdmin();
            userToUpdate.getAdresse().setRue(rue);
            userToUpdate.getAdresse().setCpo(cpo);
            userToUpdate.getAdresse().setVille(ville);
            userToUpdate.setEmail(email);
            userToUpdate.setNom(nom);
            userToUpdate.setPassword(password);
            userToUpdate.setPhone(tel);
            userToUpdate.setPrenom(prenom);
            um.mettreAJour(userToUpdate);
            req.getRequestDispatcher("WEB-INF/gestionCompte/confirmUpdate.jsp").forward(req, resp);
        }

        //Suppression utilisateur
        if(concernedUser!=null && "remove".equals(action)){
            um.remove(concernedUser.getId());
            req.getRequestDispatcher("WEB-INF/gestionCompte/confirmDelete.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("WEB-INF/accueilS").forward(req, resp);
    }
}
