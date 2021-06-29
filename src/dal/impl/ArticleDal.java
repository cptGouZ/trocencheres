package dal.impl;

import bo.Adresse;
import bo.Article;
import bo.Categorie;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.AppException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDal implements IGenericDao<Article> {

    private static final String SQL_SELECT_ALL_ARTICLES = "SELECT article, prix_vente, date_fin_encheres, no_utilisateur FROM ARTICLES";

    private static final String SQL_SELECT_BY_ID = "SELECT article, prix_vente, date_fin_encheres, no_utilisateur FROM ARTICLES WHERE no_article=?";

    private static final String SQL_INSERT_ARTICLE = "insert into ARTICLES(article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_adresse) values(?,?,?,?,?,0,?,?,?);";

    private static final String SQL_SELECT_BY_CATEGORIES = "SELECT libelle FROM CATEGORIES";


    @Override
    public List<String> selectLibelleCategories() throws GlobalException {
        //Je crée une liste
        List<String> listCate = new ArrayList<String>();
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {
            PreparedStatement pstt = con.prepareCall(SQL_SELECT_BY_CATEGORIES);
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {
                //Je choisis le paramètre de l'objet avec le get
                listCate.add(rs.getString("libelle"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Je renvoie la liste d'articles
        return listCate;
    }


    @Override
    public List<Article> selectByCrit1(String articleName, String catName) throws GlobalException {

        String SQL_SELECT_ARTICLES_BY_CRITERES = "SELECT a.no_categorie, a.article, a.prix_vente, a.date_fin_encheres, no_utilisateur, c.libelle " +
                "FROM ARTICLES a INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie WHERE ";

        //Je crée une liste
        List<Article> list = new ArrayList<Article>();
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {

            //Je trie en fonction du choix utilisateur
            StringBuilder sqlConstruction = new StringBuilder(SQL_SELECT_ARTICLES_BY_CRITERES);

            sqlConstruction.append("a.article LIKE '%"+ articleName  +"%'");
            System.out.println("momo" + articleName);

            //Choix catégorie
            System.out.println("tutu" + catName);
            if("sport".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'sport'");}
            else if("divers".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'divers'");}
            else if("ameublement".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'ameublement'");}
            else if("vetement".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'vetement'");}
            else if("alimentation".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'alimentation'");}

            System.out.println(sqlConstruction);

            PreparedStatement pstt = con.prepareCall(sqlConstruction.toString());
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {
                //Je choisis les paramètres de l'objet avec le get
                Article artAjout2 = new Article();
                artAjout2.setArticle(rs.getString("article"));
                artAjout2.setPrixVente(rs.getInt("prix_vente"));
                artAjout2.setDateFin(rs.getDate("date_fin_encheres").toLocalDate().atTime(0, 0));
                //J'ajoute l'item "Vendeur"
                Utilisateur ut = DaoProvider.getUtilisateurDao().selectById(rs.getInt("no_utilisateur"));
                artAjout2.setUtilisateur(ut);
                //J'ajoute l'article à la liste
                list.add(artAjout2);
            }
            System.out.println("didi" + list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Article> selectByCrit2(String articleName, String catName, boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb, boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours) throws GlobalException {

        String SQL_SELECT_ARTICLES_BY_CRITERES = "SELECT a.no_categorie, a.article, a.prix_vente, a.date_fin_encheres, no_utilisateur, c.libelle " +
                "FROM ARTICLES a INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie WHERE ";

        //Je crée une liste
        List<Article> list = new ArrayList<Article>();
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {

            //Je trie en fonction du choix utilisateur
            StringBuilder sqlConstruction = new StringBuilder(SQL_SELECT_ARTICLES_BY_CRITERES);

            sqlConstruction.append("a.article LIKE '%"+ articleName  +"%'");
            System.out.println("momo" + articleName);

            //Choix catégorie
            System.out.println("tutu" + catName);
            if("sport".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'sport'");}
            else if("divers".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'divers'");}
            else if("ameublement".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'ameublement'");}
            else if("vetement".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'vetement'");}
            else if("alimentation".equals(catName)) { sqlConstruction.append(" AND c.libelle = 'alimentation'");}

            System.out.println(sqlConstruction);

             //Choix des checkbox
            //TODO attente la création des article pour pouvoir gérer les période de vente
            if(ventesTerm) {
                sqlConstruction.append(" AND (date_fin_encheres < CAST(GETDATE() AS datetime) "); }
            if(encheresOuv) {
                sqlConstruction.append(" OR CAST(GETDATE() AS datetime) BETWEEN date_debut_encheres AND date_fin_encheres "); }
            if(ventesNonDeb) {
                sqlConstruction.append(" OR date_debut_encheres > CAST(GETDATE() AS datetime))"); }

//            if(inprogressVente) {
//                sqlConstruction.append(" ,inprogressVente = true,"); }
//            if(beforeVente) {
//                sqlConstruction.append(" ,beforeVente = true,"); }
//            if(finishedVente) {
//                sqlConstruction.append(" ,finishedVente =  true,"); }


            PreparedStatement pstt = con.prepareCall(sqlConstruction.toString());
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {
                //Je choisis les paramètres de l'objet avec le get
                Article artAjout2 = new Article();
                artAjout2.setArticle(rs.getString("article"));
                artAjout2.setPrixVente(rs.getInt("prix_vente"));
                artAjout2.setDateFin(rs.getDate("date_fin_encheres").toLocalDate().atTime(0, 0));
                //J'ajoute l'item "Vendeur"
                Utilisateur ut = DaoProvider.getUtilisateurDao().selectById(rs.getInt("no_utilisateur"));
                artAjout2.setUtilisateur(ut);
                //J'ajoute l'article à la liste
                list.add(artAjout2);
            }
            System.out.println("didi" + list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    @Override
    public Utilisateur selectByEmail(String email) throws GlobalException {
        return IGenericDao.super.selectByEmail(email);
    }
    @Override
    public Utilisateur selectByPseudo(String pseudo) throws GlobalException {
        return IGenericDao.super.selectByPseudo(pseudo);
    }
    @Override
    public Article selectByArticle(String article) throws GlobalException {
        return IGenericDao.super.selectByArticle(article);
    }
    @Override
    public void insert(Article obj) throws GlobalException {

    }
    @Override
    public void update(Article obj) throws GlobalException {}
    @Override
    public void delete(int id) throws GlobalException {}

    @Override
    public List<Article> selectAll() throws GlobalException {
        //Je crée un article et une liste
        List<Article> list = new ArrayList<Article>();
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {
            PreparedStatement pstt = con.prepareCall(SQL_SELECT_ALL_ARTICLES);
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {
                //Je choisis les paramètres de l'objet avec le get
                Article artAjout = new Article();
                artAjout.setArticle(rs.getString("article"));
                artAjout.setPrixVente(rs.getInt("prix_vente"));
                artAjout.setDateFin(rs.getDate("date_fin_encheres").toLocalDate().atTime(0, 0));
                //J'ajoute l'item "Vendeur"
                Utilisateur ut = DaoProvider.getUtilisateurDao().selectById(rs.getInt("no_utilisateur"));
                artAjout.setUtilisateur(ut);
                //J'ajoute l'article à la liste
                list.add(artAjout);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Je renvoie la liste d'articles
        return list;
    }

    @Override
    public Article selectById(int id) throws GlobalException {
        //Je crée un article
        Article art = new Article();
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {
            PreparedStatement pstt = con.prepareCall(SQL_SELECT_BY_ID);
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {
                //Je choisis les paramètres de l'objet avec le get
                art.setArticle(rs.getString("article"));
                art.setPrixVente(rs.getInt("prix-vente"));
                art.setDateFin(rs.getDate("date_fin_encheres").toLocalDate().atTime(0, 0));
                //J'ajoute l'item "Vendeur"
                Utilisateur ut = DaoProvider.getUtilisateurDao().selectById(rs.getInt("no_utilisateur"));
                art.setUtilisateur(ut);
                pstt.executeQuery();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Je renvoie l'article
        return art;
    }


    @Override
    public List<Adresse> selectAllAdresseByUser(int userId) throws GlobalException {
        return IGenericDao.super.selectAllAdresseByUser(userId);
    }


    @Override
    public Article insertNewArticle(Article newArticle) throws GlobalException {

        try(Connection uneConnection = ConnectionProvider.getConnection();
            PreparedStatement pStmt = uneConnection.prepareStatement(SQL_INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);
        ){

            pStmt.setString(1,newArticle.getArticle());
            pStmt.setString(2,newArticle.getDescription());
            pStmt.setTimestamp(3,java.sql.Timestamp.valueOf(newArticle.getDateDebut()));
            pStmt.setTimestamp(4,java.sql.Timestamp.valueOf(newArticle.getDateFin()));
            pStmt.setInt(5,newArticle.getPrixInitiale());
            pStmt.setInt(6,newArticle.getUtilisateur().getId());
            pStmt.setInt(7,newArticle.getCategorie().getId());

            System.out.println("test adresse : " + newArticle.getUtilisateur().getAdresse().getId());

            pStmt.setInt(8,newArticle.getAdresseRetrait().getId());

            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys() ;
                while(rs.next()){
                newArticle.setId(rs.getInt(1));
                }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AppException.CONNECTION_ERROR);
            throw GlobalException.getInstance();
        }
        return newArticle;
    }

}