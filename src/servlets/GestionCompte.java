package servlets;

import bll.interfaces.IUserManager;
import bll.ManagerProvider;
import bo.Adresse;
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
public class GestionCompte extends HttpServlet {
    public final static int NEW_ACCOUNT = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
/*             if(req.getParameter("messageErreur")!=null)
                req.setAttribute("messageErreur", req.getAttribute("messageErreur"));*/
            IUserManager um = ManagerProvider.getUserManager();
            boolean isUserCreation = Boolean.parseBoolean(req.getParameter("createUser"));
            Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
            if(isUserCreation){
                //La demande est une création de compte
                if(userConnected!=null) {
                    //Utilisateur déjà connecté qui demande un nouveau compte je redirige vers accueil
                    req.getRequestDispatcher("accueilS").forward(req, resp);
                }else{
                    //Création du compte autorisé
                    req.setAttribute("createUser", true);
                    req.getRequestDispatcher("WEB-INF/gestionCompte.jsp").forward(req, resp);
                }
            }

            if(userConnected!=null){
                //Utilisateur est déjà connecté et demande la modification d'un profil
                Integer userId = Integer.parseInt(req.getParameter("userId"));
                Utilisateur userToModify = um.getById(userId);
                if(userConnected.getId()!=userToModify.getId()){
                    //L'utilisateur qui demande la modification de profil n'est pas lui même
                    req.getRequestDispatcher("accueilS").forward(req, resp);
                }else{
                    //Modification du profil autorisé
                    req.setAttribute("userId", userId);
                    req.getRequestDispatcher("WEB-INF/gestionCompte.jsp").forward(req, resp);
                }
            }
        } catch (GlobalException e) {
            System.out.println(e.getMessageErrors());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserManager um = ManagerProvider.getUserManager();
        String action = req.getParameter("action").trim();
        Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
        Integer userId = null;
        if(!req.getParameter("userId").isEmpty())
            userId = Integer.parseInt(req.getParameter("userId"));
        try {
            //Récupération des données de la page partie user
            String pseudo = req.getParameter("pseudo").trim();
            String nom = req.getParameter("nom").trim();
            String prenom = req.getParameter("prenom").trim();
            String email = req.getParameter("email").trim();
            String tel = req.getParameter("tel").trim();
            String password = req.getParameter("password").trim();
            String newPassword = req.getParameter("newPassword").trim();
            String confirmPassword = req.getParameter("confirmPassword").trim();
            //Récupération des données de la page partie adresse
            String rue = req.getParameter("rue").trim();
            String cpo = req.getParameter("cpo").trim();
            String ville = req.getParameter("ville").trim();


            //Création de l'utilisateur
            if("creer".equals(action)){
                Adresse newAdresse = new Adresse(rue, cpo, ville,true);
                Utilisateur newUser = new Utilisateur(newAdresse, pseudo, nom, prenom, email, tel, 0, false);
                um.creer(newUser, newPassword, confirmPassword);
                req.getRequestDispatcher("WEB-INF/gestionCompte/confirmCreation.jsp").forward(req, resp);
            }

            //Mise à jour utilisateur
            if ("maj".equals(action) && userId == userConnected.getId()) {
                Utilisateur userToUpdate = um.getById(Integer.parseInt(req.getParameter("userId")));
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

            /*//Suppression utilisateur
            if(concernedUser!=null && "remove".equals(action)){
                um.remove(concernedUser.getId());
                req.getRequestDispatcher("WEB-INF/gestionCompte/confirmDelete.jsp").forward(req, resp);
            }*/
        }catch(GlobalException e){
            if("creer".equals(action)) {
                req.setAttribute("createUser", true);
                req.removeAttribute("userId");
                req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
                req.getRequestDispatcher("WEB-INF/gestionCompte.jsp").forward(req, resp);
            }
            if("maj".equals(action) && userId == userConnected.getId()) {
                req.setAttribute("userId", userId);
                req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
                req.getRequestDispatcher("WEB-INF/gestionCompte.jsp").forward(req, resp);
            }
            if("remove".equals(action)){
                req.setAttribute("userId", userId);
                req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
                req.getRequestDispatcher("profil").forward(req, resp);
            }
        }
    }
}
