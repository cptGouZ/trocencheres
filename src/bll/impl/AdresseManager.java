package bll.impl;

import bll.ManagerProvider;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IUserManager;
import bo.Adresse;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.AdresseException;
import exception.exceptionEnums.UserException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AdresseManager implements IAdresseManager {
    @Override
    public void creer(Adresse adresse) throws GlobalException {
        IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
        validerRue(adresse);
        validerCpo(adresse);
        validerVille(adresse);
        if(adresse.getUserId()!=null)
            validerUtilisateur(adresse);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        adresseDao.insert(adresse);
    }

    @Override
    public void mettreAJour(Adresse adresse) throws GlobalException {
        IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
        validerRue(adresse);
        validerCpo(adresse);
        validerVille(adresse);
        if(adresse.getUserId()!=null)
            validerUtilisateur(adresse);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        adresseDao.update(adresse);
    }

    @Override
    public void supprimer(int id) throws GlobalException {
        IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
        adresseDao.delete(id);
    }

    @Override
    public List<Adresse> getAdressesByUser(int userId) throws GlobalException {
        IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
        List<Adresse> retour = adresseDao.selectAllAdresseByUser(userId);
        return retour;
    }

    @Override
    public Adresse getById (int id) throws GlobalException{
        IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
        Adresse retour = adresseDao.selectById(id);
        return retour;
    }

    @Override
    public List<Adresse> getAll() throws GlobalException {
        IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
        List<Adresse> retour = adresseDao.selectAll();
        return retour;
    }

    /**************************/
    /* CONTROLES DE L'ADRESSE */
    /**************************/
    private final String PATTERN_RUE = "^[\\p{L}\\p{Z}-]*$";
    private final String PATTERN_CPO = "^[\\d]{5}$";
    private final String PATTERN_VILLE = "^[\\p{L}\\p{Z}-]*$";

    private void validerRue(Adresse adresse) throws GlobalException{
        if(adresse.getRue().isEmpty())
            GlobalException.getInstance().addError(AdresseException.RUE_VIDE);
        if(!Pattern.matches(PATTERN_RUE, adresse.getRue()))
            GlobalException.getInstance().addError(AdresseException.RUE_INVALIDE);
    }
    private void validerCpo(Adresse adresse) throws GlobalException{
        if(adresse.getCpo().isEmpty())
            GlobalException.getInstance().addError(AdresseException.CPO_VIDE);
        if(!Pattern.matches(PATTERN_CPO, adresse.getCpo()))
            GlobalException.getInstance().addError(AdresseException.CPO_INVALIDE);
    }
    private void validerVille(Adresse adresse) throws GlobalException{
        if(adresse.getVille().isEmpty())
            GlobalException.getInstance().addError(AdresseException.VILLE_VIDE);
        if(!Pattern.matches(PATTERN_VILLE, adresse.getVille()))
            GlobalException.getInstance().addError(AdresseException.VILLE_INVALIDE);
    }
    private void validerUtilisateur(Adresse adresse) throws GlobalException{
        IUserManager um = ManagerProvider.getUserManager();
        if(um.getById(adresse.getUserId())==null)
            GlobalException.getInstance().addError(AdresseException.BAD_USER_ADRESSE);
    }
}
