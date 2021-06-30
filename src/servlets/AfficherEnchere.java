package servlets;

import bll.ManagerProvider;
import bll.impl.EnchereManager;
import bll.interfaces.IArticleManager;
import bll.interfaces.IEnchereManager;
import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import exception.GlobalException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(
        urlPatterns = {
                "/afficherenchere",
                "/encherir",
                "/retrait"
        })
public class AfficherEnchere extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IEnchereManager em = ManagerProvider.getEnchereManager();
        IArticleManager am = ManagerProvider.getArticleManager();
        Utilisateur userConnected = (Utilisateur) req.getSession().getAttribute("userConnected");
//      Integer idArticle = Integer.valueOf(req.getParameter("idArticle"));
        int idArticle = 2;
        try {
            Article articleToDisplay = am.getById(idArticle);
            Enchere lastEnchere = em.getLastEnchereOnArticle(idArticle);
            String montant = req.getParameter("montant");

            boolean isMeOnLastEnchere = false;
            if(lastEnchere!=null)
                isMeOnLastEnchere = lastEnchere.getUser().getId().equals(userConnected.getId());


            /*****************************************************/
            /* SELECTION DE L'AFFICHAGE A FAIRE APRES TRAITEMENT */
            /*****************************************************/
            boolean attenteRetrait=false; //TODO calcul à établir. Statut dans table article ?

            //Par défaut on demande un affichage
            req.setAttribute("affichagejsp", "afficher");

            //Si la dernière enchère n'est pas faite par moi et que l'enchère est encore ouverte on affiche enrichir
            if(!isMeOnLastEnchere && articleToDisplay.isOuvert())
                req.setAttribute("affichagejsp", "encherir");

            //Si l'enchère est fermée que c'est moi le vainqueur et qu'elle est en attente de retrait on affiche pour retirer
            if(!articleToDisplay.isOuvert() && attenteRetrait && isMeOnLastEnchere)
                req.setAttribute("affichagejsp", "retirer");


            /****************************************/
            /* TRAITEMENT DES DONNEES A ENREGISTRER */
            /****************************************/
            if(req.getRequestURI().contains("encherir")) {
                //créer une nouvelle enchère
                em.creer(articleToDisplay, montant, userConnected) ;
                lastEnchere = em.getLastEnchereOnArticle(articleToDisplay.getId());
            }
            if(req.getRequestURI().contains("retrait")) {
                am.retirer(articleToDisplay);
            }


            /**************************************************/
            /* insertion des objet dans la jsp et redirection */
            /**************************************************/
            req.setAttribute("article", articleToDisplay);
            req.setAttribute("enchere", lastEnchere);
            req.getRequestDispatcher("WEB-INF/Enchere.jsp").forward(req, resp);
        } catch (GlobalException e) {
            e.printStackTrace();
            req.setAttribute("message", GlobalException.getInstance().getMessageErrors());
            req.getRequestDispatcher("WEB-INF/Enchere.jsp").forward(req, resp);
        }
    }
}
