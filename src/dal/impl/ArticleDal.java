package dal.impl;

import bo.Adresse;
import bo.Article;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDal implements IGenericDao<Article> {

    private static final String SQL_SELECT_ALL_ARTICLES = "SELECT article, prix_vente, date_fin_encheres, no_utilisateur FROM ARTICLES";

    private static final String SQL_SELECT_BY_ID = "SELECT article, prix_vente, date_fin_encheres, no_utilisateur FROM ARTICLES WHERE id = ?";

    private static final String SQL_INSERT_ARTICLE = "insert into ARTICLES(article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_adresse) values(?,?,?,?,?,0,?,?,?);";

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
                //J'acjoute l'article à la liste
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
    public List<Article> selectByCriteres(String articleName, String catName, boolean openedEnchere,
                                          boolean inprogressEnchere, boolean winEnchere,
                                          boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException {

        String SQL_SELECT_ARTICLES_BY_CRITERES = "SELECT a.no_categorie, a.article, a.prix_vente, a.date_fin_encheres, c.no_categorie, c.libelle " +
                "FROM ARTICLES a INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie WHERE ";

        //Je crée une liste
        List<Article> list = new ArrayList<Article>();
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {
            PreparedStatement pstt = con.prepareCall(String.valueOf(SQL_SELECT_ARTICLES_BY_CRITERES));

            //Je trie en fonction du choix utilisateur
            StringBuilder sqlConstruction = new StringBuilder(SQL_SELECT_ARTICLES_BY_CRITERES);

                sqlConstruction.append("a.article = '" + articleName  + "',");

            //Choix catégorie
            System.out.println("tutu" + catName);
            if("toutes".equals(catName)) { sqlConstruction.append(" c.libelle = 'toutes',");}
            else if("informatique".equals(catName)) { sqlConstruction.append(" c.libelle = 'informatique',");}
            else if("ameublement".equals(catName)) { sqlConstruction.append(" c.libelle = 'ameublement',");}
            else if("vetement".equals(catName)) { sqlConstruction.append(" c.libelle = 'vetement',");}
            else {sqlConstruction.append(" c.libelle = 'sports&loisirs',");}


            //sqlConstruction.append("c.libelle = '" + catName + "',");


//            //Choix des checkbox
//            //TODO attente la création des article pour pouvoir gérer les période de vente
//            if(openedEnchere) {
//                sqlConstruction.append(" openedEnchere = true,"); }
//            if(inprogressEnchere) {
//                sqlConstruction.append("inprogressEnchere = true,"); }
//            if(winEnchere) {
//                sqlConstruction.append(" winEnchere = true,"); }
//            if(inprogressVente) {
//                sqlConstruction.append(" inprogressVente = true,"); }
//            if(beforeVente) {
//                sqlConstruction.append(" beforeVente = true,"); }
//            if(finishedVente) {
//                sqlConstruction.append(" finishedVente =  true,"); }
                System.out.println(sqlConstruction);
//
//            ResultSet rs = pstt.executeQuery();
////            while (rs.next()) {
////                //Je choisis les paramètres de l'objet avec le get
////                Article artAjout = new Article();
////                artAjout.setArticle(rs.getString("article"));
////                artAjout.setPrixVente(rs.getInt("prix_vente"));
////                artAjout.setDateFin(rs.getDate("date_fin_encheres").toLocalDate().atTime(0, 0));
////                //J'ajoute l'item "Vendeur"
////                Utilisateur ut = FactoriesDao.getUtilisateurDao().selectById(rs.getInt("no_utilisateur"));
////                artAjout.setUtilisateur(ut);
////                }
////                //J'acjoute l'article à la liste
////                //list.add(artAjout);
//

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
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
            pStmt.setInt(8,newArticle.getUtilisateur().getAdresse().getId());

            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys() ;
                while(rs.next()){
                newArticle.setId(rs.getInt(1));
                }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newArticle;
    }

}