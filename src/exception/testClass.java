package exception;

import com.sun.xml.internal.bind.v2.TODO;
import exception.exceptionEnums.UserException;
import jdk.nashorn.internal.scripts.JS;

import javax.swing.text.html.CSS;

public class testClass {
// A FAIRE    ce soir et demain matin
// TODO : requête du montant dispo ne prend pas en compte les articles retirés : A tester
// TODO_ok : tester la suppression utilisateur : validé Olivier + Julien
// TODO : Vérifier l'heure de début et fin d'enchère : Alex
// TODO_ok : Changer la servlet accueilS en accueil : Olivier
/* TODO : Vérification des exception. Le header contien "messageErreur" et "messageConfirm" pour les affichages
    Utilisateur
    Dal
        Article -> Olivier : ok : tous les champs obligatoires déclenchent une exception dans Nouvelle Vente
        Enchere -> Julien : ok
        Adresse -> validé Julien
        Categorie -> Olivier : ok
        User -> validé Julien
    Managers
        AdresseManager -> validé Julien
        ArticleManager -> Olivier : ok
        CategorieManager -> Olivier : ok
        ConnexionManager -> Alex
        EnchereManager -> Julien
        UserManager -> validé Julien
    Servlet
        GestionCompte -> validé Julien
        AccueilServlet -> Julien
        AfficherEnchere -> reste à tester le retrait
        Connexion -> validé Julien
        Vente->Alex

  */

// SECONDAIRE    demain après-midi
// TODO : CSS / JS sur les option bouton : Olivier
// TODO : Message confirmation et erreur à afficher dans les JSP : Julien
// TODO : insertArticle à redéplacer dans l'insert standard : Alex
// TODO : Déplacer le bloc affichage enchère dans un fragment : Olivier
}
