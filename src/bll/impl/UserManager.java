package bll.impl;

import bll.ManagerProvider;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IUserManager;
import bo.Adresse;
import bo.Utilisateur;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class UserManager implements IUserManager {
    IGenericDao<Utilisateur> userDao = DaoProvider.getUtilisateurDao();
    IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
    @Override
    public Utilisateur getById(int userId) throws GlobalException {
        return userDao.selectById(userId);
    }

    @Override
    public void mettreAJour(Integer userId, Utilisateur userUpdated, String newPwConf) throws GlobalException {
        Utilisateur userInBase = getById(userId);
        /* MISE A JOUR DE L'UTILISATEUR COURANT */
        userInBase.setPseudo(userUpdated.getPseudo());
        userInBase.setNom(userUpdated.getNom());
        userInBase.setPrenom(userUpdated.getPrenom());
        userInBase.setEmail(userUpdated.getEmail());
        userInBase.setPhone(userUpdated.getPhone());
        userInBase.getAdresse().setRue(userUpdated.getAdresse().getRue());
        userInBase.getAdresse().setCpo(userUpdated.getAdresse().getCpo());
        userInBase.getAdresse().setVille(userUpdated.getAdresse().getVille());

        /* CONTROLE DES DONNEES */
        validerUser(userInBase);
        if( !userInBase.getPassword().equals(userUpdated.getPassword()) ) {
            //Validation du nouveau mot de passe demandé
            validerPasswordCreation(userUpdated, newPwConf);
            userInBase.setPassword(userUpdated.getPassword());
        }
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();

        /* Modification de l'utilisateur en base */
        userDao.update(userInBase);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        adresseDao.update(userInBase.getAdresse());
    }

    @Override
    public void supprimer(int userId) throws GlobalException {
        //TODO controles les enchères et ventes en cours sur l'utilisateur
        IAdresseManager am = ManagerProvider.getAdresseManager();
        Utilisateur user = getById(userId);
/*        for (Adresse a : am.getAdressesByUser(userId)){
            am.supprimer(user.getAdresse().getId());
        }*/
        userDao.delete(user.getId());
    }

    @Override
    public void creer(Utilisateur user, String confirmationPassword) throws GlobalException {
        IAdresseManager am = ManagerProvider.getAdresseManager();
        IGenericDao<Utilisateur>userDao = DaoProvider.getUtilisateurDao();

        /* CONTROLE DES DONNEES */
        validerUser(user);
        validerPasswordCreation(user, confirmationPassword);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();

        /* MANIPULATION DE LA DAL */
        userDao.insert(user);
        user.getAdresse().setUserId(user.getId());
        user.getAdresse().setDomicile(true);
        try {
            //On essai de créer l'adresse, si ca se passe mal, on supprimer l'utilisateur créé précédement
            am.creer(user.getAdresse());
        }catch (GlobalException e) {
            userDao.delete(user.getId());
            throw e;
        }
    }



    /*CONTROLES DE L'UTILISATEUR */
    private final static String PATTERN_USER = "^[\\p{L}0-9]{0,30}$";
    private final static String PATTERN_PRENOM = "^[\\p{L}0-9]{0,30}$";
    private final static String PATTERN_NOM = "^[\\p{L}0-9]{0,30}$";
    private final static String PATTERN_EMAIL = "^[\\w\\-.]*@[a-z0-9-.]*\\.[a-z]*$";
    private final static String PATTERN_TELEPHONE = "^\\d{10}$";
    private final static String PATTERN_PASSWORD ="^[\\p{P}\\w]{6,12}$";

    private void validerUser(Utilisateur user) throws GlobalException {
        //Pseudo
        Utilisateur userByPseudo = userDao.selectByPseudo(user.getPseudo());
        if (user.getPseudo().isEmpty())
            GlobalException.getInstance().addError(UserException.PSEUDO_VIDE);
        if (!Pattern.matches(PATTERN_USER,user.getPseudo()))
            GlobalException.getInstance().addError(UserException.PSEUDO_INVALIDE);
        if (userByPseudo!=null && !userByPseudo.getId().equals(user.getId()))
            GlobalException.getInstance().addError(UserException.PSEUDO_EXISTANT);
        //Nom
        if(user.getNom().isEmpty())
            GlobalException.getInstance().addError(UserException.NOM_VIDE);
        if(!Pattern.matches(PATTERN_NOM, user.getNom()))
            GlobalException.getInstance().addError(UserException.NOM_INVALIDE);
        //Prénom
        if(user.getPrenom().isEmpty())
            GlobalException.getInstance().addError(UserException.PRENOM_VIDE);
        if(!Pattern.matches(PATTERN_PRENOM,user.getPrenom()))
            GlobalException.getInstance().addError(UserException.PRENOM_INVALIDE);
        //E-mail
        Utilisateur userByMail = userDao.selectByEmail(user.getEmail());
        if(user.getEmail().isEmpty())
            GlobalException.getInstance().addError(UserException.EMAIL_VIDE);
        if(!Pattern.matches(PATTERN_EMAIL, user.getEmail()))
            GlobalException.getInstance().addError(UserException.EMAIL_INVALIDE);
        if (userByMail!=null && !userByMail.getId().equals(user.getId()))
            GlobalException.getInstance().addError(UserException.EMAIL_EXISTANT);
        //Téléphone
        if(!user.getPhone().isEmpty()){
            if(!Pattern.matches(PATTERN_TELEPHONE, user.getPhone()))
                GlobalException.getInstance().addError(UserException.TELEPHONE_INVALIDE);
        }
    }

    private void validerPasswordCreation(Utilisateur user, String confirmationPassword){
        //Contrôle du nouveau mot de passe
        if(user.getPassword().isEmpty())
            GlobalException.getInstance().addError(UserException.NEW_PASSWORD_VIDE);
        if(!Pattern.matches(PATTERN_PASSWORD, user.getPassword()))
            GlobalException.getInstance().addError((UserException.NEW_PASSWORD_INVALIDE));
        //Contrôle du mot de passe de confirmation
        if(confirmationPassword.isEmpty())
            GlobalException.getInstance().addError(UserException.CONFIRMATION_PASSWORD_VIDE);
        //Contrôle de la cohérence des deux mots de passe
        if(!user.getPassword().equals(confirmationPassword))
            GlobalException.getInstance().addError(UserException.CONFIRMATION_PASSWORD_NO_MATCH);
    }

    public Utilisateur getFromHttpRequest(HttpServletRequest req, String actualPw){
        //Récupération des données de la page
        String pseudo = req.getParameter("pseudo").trim();
        String nom = req.getParameter("nom").trim();
        String prenom = req.getParameter("prenom").trim();
        String email = req.getParameter("email").trim();
        String tel = req.getParameter("tel").trim();
        String rue = req.getParameter("rue").trim();
        String cpo = req.getParameter("cpo").trim();
        String ville = req.getParameter("ville").trim();
        Adresse newAdresse = new Adresse(rue, cpo, ville);
        Utilisateur newUser = new Utilisateur(newAdresse, pseudo, nom, prenom, email, tel);
        String pwToSet = req.getParameter("newPw").isEmpty() ? actualPw : req.getParameter("newPw").trim();
        newUser.setPassword(pwToSet);
        return newUser;
    }
}