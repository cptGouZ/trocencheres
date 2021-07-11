package exception.exceptionEnums;

import exception.EnumException;

//Groupe de codes 2000
public interface ArticleException extends EnumException {
    //DAL Exception 20**
    int ECHEC_MISE_A_JOUR_ARTICLE = 2000 ;
    int ECHEC_MISE_A_JOUR_CREDIT_ACHETEUR = 2010 ;
    int ECHEC_MISE_A_JOUR_CREDIT_VENDEUR = 2020 ;
    int ECHEC_RECHERCHE = 2030;
    //BLL Exception 21**
    int NOM_ARTICLE_VIDE = 2100;
    int NOM_ARTICLE_INVALIDE = 2110;
    int DESCRIPTION_ARTICLE_VIDE = 2120;
    int DESCRIPTION_ARTICLE_INVALIDE = 2130;
    int PRIX_ARTICLE_VIDE = 2140;
    int PRIX_ARTICLE_INVALIDE = 2150;
    int DATE_VIDE = 2160;
    int FORMAT_DATE_INVALIDE = 2170;
    int CRITERE_RECHERCHE_INVALIDE = 2180;
    int DATE_DEBUT_SUP_FIN = 2190;

    //IHM Exception 12**
    int ARTICLE_CREATION_OK = 2200;
}
