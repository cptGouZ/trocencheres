package servlets;

import bll.ManagerProvider;
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
        int idArticle = Integer.parseInt(req.getParameter("idArticle"));

        try {
            Article articleToDisplay = am.getById(idArticle);
            Enchere lastEnchere = em.getLastEnchereOnArticle(idArticle);
            String montant = req.getParameter("montant");
            boolean isMeOnLastEnchere = false;
            if(lastEnchere!=null) {
                isMeOnLastEnchere = lastEnchere.getUser().getId().equals(userConnected.getId());
            }else{
                lastEnchere = new Enchere();
                lastEnchere.setMontant(articleToDisplay.getPrixInitial());
            }


            //Repréparation de la page
            req.setAttribute("article", articleToDisplay);
            req.setAttribute("enchere", lastEnchere);

            //Nous venons pour un affichage d'enchère
            if(req.getRequestURI().contains("afficherenchere")) {
                req.setAttribute("affichagejsp", "afficher");
                //Si la dernière enchère n'est pas faite par moi et que l'enchère est encore ouverte on affiche enrichir
                if (!isMeOnLastEnchere && articleToDisplay.isOuvert() && (! articleToDisplay.getUtilisateur().getId().equals(userConnected.getId())))
                    req.setAttribute("affichagejsp", "encherir");
                //Si l'enchère est fermée que c'est moi le vainqueur et qu'elle est en attente de retrait on affiche pour retirer
                if (!articleToDisplay.isOuvert() && !articleToDisplay.getIsRetire() && isMeOnLastEnchere)
                    req.setAttribute("affichagejsp", "retirer");
                req.getRequestDispatcher("WEB-INF/Enchere.jsp").forward(req, resp);
            }
            //Nous venons pour enchérir
            if(req.getRequestURI().contains("encherir")) {
                //créer une nouvelle enchère
                em.creer(articleToDisplay, montant, userConnected,lastEnchere) ;
                lastEnchere = em.getLastEnchereOnArticle(articleToDisplay.getId());
                req.setAttribute("enchere", lastEnchere);
                req.setAttribute("affichagejsp", "afficher");
                req.getRequestDispatcher("WEB-INF/Enchere.jsp").forward(req, resp);
            }
            //Nous venons pour retirer l'article
            if(req.getRequestURI().contains("retrait")) {
                am.retirer(articleToDisplay, userConnected, lastEnchere);
                req.setAttribute("messageConfirm", "Félicitation vous avez retirer votre article chez le vendeur");
                req.getRequestDispatcher("accueil").forward(req, resp);
            }
        } catch (GlobalException e) {
            //Réaffichage de la page comme elle était
            if(req.getRequestURI().contains("afficherenchere"))
                req.setAttribute("affichagejsp", "afficher");
            if(req.getRequestURI().contains("encherir"))
                req.setAttribute("affichagejsp", "encherir");
            if(req.getRequestURI().contains("retrait"))
                req.setAttribute("affichagejsp", "retirer");
            req.setAttribute("message", GlobalException.getInstance().getMessageErrors());
            req.getRequestDispatcher("WEB-INF/Enchere.jsp").forward(req, resp);
        }
    }
}
