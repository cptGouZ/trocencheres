package servlets;

import bll.ManagerProvider;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IArticleManager;
import bll.interfaces.IConnexionManager;
import bo.Adresse;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import com.sun.xml.internal.bind.v2.TODO;
import exception.GlobalException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@WebServlet("/vente")

public class Vente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalDate default_date = LocalDate.now();
        req.setAttribute("dateDuJour", default_date.toString());

        RequestDispatcher rd ;
        rd = req.getRequestDispatcher("WEB-INF/vente.jsp") ;
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //LocalTime heureEnchere = LocalTime.now();
        Integer idUtilisateur = ((Utilisateur) req.getSession().getAttribute("userConnected")).getId();
        Utilisateur userEnCours = (Utilisateur) req.getSession().getAttribute("userConnected");

        String article = req.getParameter("article").trim();
        String description = req.getParameter("description").trim();
        String categorieString = req.getParameter("categorie").trim() ;
        Integer categorie = Integer.valueOf(categorieString);
        Integer prixDepart = Integer.valueOf(req.getParameter("prixDepart").trim());

        String debutEnchere = req.getParameter("debutEnchere").trim();
        String debutEncherePrecis = (debutEnchere + ":01").trim();
        LocalDateTime debutEnchereBll = LocalDateTime.parse(debutEncherePrecis);

        String finEnchere = req.getParameter("finEnchere").trim();
        String finEncherePrecis = (finEnchere + ":59").trim() ;
        LocalDateTime finEnchereBll = LocalDateTime.parse(finEncherePrecis);

        String rue = req.getParameter("rue").trim();
        String cpo = req.getParameter("cpo").trim();
        String ville = req.getParameter("ville").trim();

        req.setAttribute("articleSaisie",article);
        req.setAttribute("descriptionSaisie",description);
        req.setAttribute("categorieSaisie",categorie);
        req.setAttribute("prixSaisie",prixDepart);
        req.setAttribute("dateDebutSaisie",debutEnchere);
        req.setAttribute("dateFinSaisie",finEnchere);
        req.setAttribute("rueSaisie",rue);
        req.setAttribute("cpoSaisie",cpo);
        req.setAttribute("villeSaisie",ville);

        try {
            IArticleManager icm = ManagerProvider.getArticleManager();
            IAdresseManager iam = ManagerProvider.getAdresseManager();

            Adresse newAdresse = null ;
            newAdresse = new Adresse(rue,cpo,ville,idUtilisateur,false);
            iam.creer(newAdresse);

            icm.insertNewArticle(
                            userEnCours,
                            categorie,
                            article,
                            description,
                            debutEnchereBll,
                            finEnchereBll,
                            prixDepart,
                            newAdresse);

            //Affiche un message confirmant la création de l'article en BDD
            String creationArticleOk = "Felicitations votre nouvelle vente a bien ete enregistree" ;
            req.setAttribute("messageConfirm" , creationArticleOk);

            //Reset tout les champs saisis lors de la création de l'article
            req.setAttribute("articleSaisie", null);
            req.setAttribute("descriptionSaisie", null);
            req.setAttribute("categorieSaisie", null);
            req.setAttribute("prixSaisie", null);
            req.setAttribute("dateDebutSaisie", null);
            req.setAttribute("dateFinSaisie", null);
            req.setAttribute("rueSaisie", null);
            req.setAttribute("cpoSaisie", null);
            req.setAttribute("villeSaisie", null);

            //Et renvoi à la page de création de la vente
            req.getRequestDispatcher("WEB-INF/vente.jsp").forward(req,resp);

        } catch (GlobalException e) {
            e.printStackTrace();

            //Affiche un message d'erreur si la vérification article a échoué
            req.setAttribute("messageErreur", GlobalException.getInstance().getMessageErrors());
            //Et renvoi à la page de création de la vente
            req.getRequestDispatcher("WEB-INF/vente.jsp").forward(req,resp);
        }

    }
}
