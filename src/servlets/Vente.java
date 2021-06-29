package servlets;

import bll.ManagerProvider;
import bll.interfaces.IAdresseManager;
import bll.interfaces.IArticleManager;
import bll.interfaces.IConnexionManager;
import bo.Adresse;
import bo.Article;
import bo.Utilisateur;
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
        Integer categorie = Integer.valueOf(req.getParameter("categorie").trim());
        Integer prixDepart = Integer.valueOf(req.getParameter("prixDepart").trim());

        String debutEnchere = req.getParameter("debutEnchere").trim();
        String debutEncherePrecis = (debutEnchere + "T00:00:00").trim();
        LocalDateTime debutEnchereBll = LocalDateTime.parse(debutEncherePrecis);

        String finEnchere = req.getParameter("finEnchere").trim();
        String finEncherePrecis = (finEnchere + "T23:59:59").trim() ;
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

            Article newArticle = null ;
                newArticle = icm.insertNewArticle(
                            userEnCours,
                            categorie,
                            article,
                            description,
                            debutEnchereBll,
                            finEnchereBll,
                            prixDepart);

            Adresse newAdresse = null ;
            newAdresse = new Adresse(rue,cpo,ville,idUtilisateur,false);
            iam.creer(newAdresse);

            //Affiche un message confirmant la création de l'article en BDD
            String creationArticleOk = "Félicitations votre nouvelle vente a bien été enregistrée" ;
            req.setAttribute("messageCreationArticle" , creationArticleOk);

            //Et renvoi à la page de création de la vente
            req.getRequestDispatcher("WEB-INF/vente.jsp").forward(req,resp);

        } catch (GlobalException e) {
            e.printStackTrace();

            //Affiche un message d'erreur si la vérification article a échoué
            req.setAttribute("messageErreurArticle", GlobalException.getInstance().getMessageErrors());
            //Et renvoi à la page de création de la vente
            req.getRequestDispatcher("WEB-INF/vente.jsp").forward(req,resp);
        }



    }
}
