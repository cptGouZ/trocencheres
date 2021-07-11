package bll.impl;

import bll.ManagerProvider;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IUserManager;
import bo.Adresse;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.AdresseException;
import java.util.List;
import java.util.regex.Pattern;

public class AdresseManager implements IAdresseManager {
    private final IGenericDao<Adresse> adresseDao = DaoProvider.getAdresseDao();
    @Override
    public void creer(Adresse adresse) throws GlobalException {
        validerAdresse(adresse);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        adresseDao.insert(adresse);
    }

    @Override
    public void mettreAJour(Adresse adresse) throws GlobalException {
        validerAdresse(adresse);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        adresseDao.update(adresse);
    }

    @Override
    public void supprimer(int id) throws GlobalException {
        adresseDao.delete(id);
    }

    @Override
    public List<Adresse> getAdressesByUser(int userId) throws GlobalException {
        return adresseDao.selectAllAdresseByUser(userId);
    }

    @Override
    public Adresse getById (int id) throws GlobalException{
        return adresseDao.selectById(id);
    }

    @Override
    public List<Adresse> getAll() throws GlobalException {
        return adresseDao.selectAll();
    }

    /* CONTROLES DE L'ADRESSE */
    private final static String PATTERN_RUE = "^[\\p{L}\\p{Z}-]*$";
    private final static String PATTERN_CPO = "^[\\d]{5}$";
    private final static String PATTERN_VILLE = "^[\\p{L}\\p{Z}-]*$";

    private void validerAdresse(Adresse adresse) throws GlobalException{
        //Rue
        if(adresse.getRue().isEmpty())
            GlobalException.getInstance().addError(AdresseException.RUE_VIDE);
        if(!Pattern.matches(PATTERN_RUE, adresse.getRue()))
            GlobalException.getInstance().addError(AdresseException.RUE_INVALIDE);
        //Cpo
        if(adresse.getCpo().isEmpty())
            GlobalException.getInstance().addError(AdresseException.CPO_VIDE);
        if(!Pattern.matches(PATTERN_CPO, adresse.getCpo()))
            GlobalException.getInstance().addError(AdresseException.CPO_INVALIDE);
        //Ville
        if(adresse.getVille().isEmpty())
            GlobalException.getInstance().addError(AdresseException.VILLE_VIDE);
        if(!Pattern.matches(PATTERN_VILLE, adresse.getVille()))
            GlobalException.getInstance().addError(AdresseException.VILLE_INVALIDE);
        //Utilisateur
        if(adresse.getUserId()!=null){
            IUserManager um = ManagerProvider.getUserManager();
            if(um.getById(adresse.getUserId())==null)
                GlobalException.getInstance().addError(AdresseException.BAD_USER_ADRESSE);
        }
    }
}
