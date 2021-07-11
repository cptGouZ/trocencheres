package bll.impl;

import bll.interfaces.IArticleManager;
import bll.interfaces.IEnchereManager;
import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.ArticleException;
import exception.exceptionEnums.EnchereException;
import jdk.nashorn.internal.objects.Global;
import sun.security.action.GetLongAction;

import java.util.regex.Pattern;

public class EnchereManager implements IEnchereManager {
    @Override
    public Enchere getLastEnchereOnArticle(int idArticle) throws GlobalException {
        Enchere encDao = DaoProvider.getEnchereDao().selectEnchereMaxByIdArticle(idArticle);
        return encDao ;
    }

    @Override
    public void creer(Article article, String montant, Utilisateur userConnected,Enchere lastEnchere) throws GlobalException {

        //Création d'un objet enchere
        Enchere nouvelleEnchere = new Enchere() ;

        //Récupérer une enchereDAO
        IGenericDao<Enchere> encDao = DaoProvider.getEnchereDao() ;

        //Vérifier le montant renseigné par l'utilisateur dans l'IHM
        validerPrix(montant);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();
        Integer montantOK = Integer.valueOf(montant);

        //Insérer dans l'enchère toutes les données récupérées de la BLL
        nouvelleEnchere.setArticle(article);
        nouvelleEnchere.setMontant(montantOK);
        nouvelleEnchere.setUser(userConnected);

        //Récupérer le crédit restant pour l'utilisateur souhaitant enchérir
        Integer creditDispo = userConnected.getCreditDispo();

        //Contrôler qu'il lui reste suffisament de crédit pour enchérir
        if(montantOK > creditDispo)
            GlobalException.getInstance().addError(EnchereException.CREDIT_INSUFFISANT);

        //Contrôler que l'enchère est bien strictement supérieur à la dernière enchère
        if(montantOK <= lastEnchere.getMontant())
            GlobalException.getInstance().addError(EnchereException.ENCH_INF_LASTENCHERE);
        if(GlobalException.getInstance().hasErrors())
            throw GlobalException.getInstance();

        //Si les contrôles sont OK j'envoi la nouvelle enchère à la DAL
            encDao.insert(nouvelleEnchere);

    }

    private final String PATTERN_PRIX = "^[0-9]{1,10}$" ;

    private void validerPrix(String prixAVerifier){

        if (prixAVerifier.isEmpty())
            GlobalException.getInstance().addError(ArticleException.PRIX_ARTICLE_VIDE);
        if(!Pattern.matches(PATTERN_PRIX, prixAVerifier))
            GlobalException.getInstance().addError(ArticleException.PRIX_ARTICLE_INVALIDE);
    }
}
