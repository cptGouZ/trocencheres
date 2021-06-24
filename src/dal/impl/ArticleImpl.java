package dal.impl;

import bo.Article;
import dal.ConnectionProvider;
import dal.IGenericDao;
import exception.GlobalException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleImpl implements IGenericDao<Article> {


    private static final String SQL_SELECT_ALL_ARTICLES = "SELECT article, prix_vente, date_fin_encheres FROM ARTICLES";

    private static final String SQL_SELECT_BY_ARTICLE = "SELECT u.no_utilisateur, u.pseudo, a.no_article, a.article, a.prix_vente, a.date_fin_encheres" +
            "FROM UTILISATEURS u INNER JOIN ARTICLES a ON u.no_categorie = a.no_article";

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM ARTICLES WHERE id=?";

    private static final String SQL_SELECT_ARTICLES_BY_CRITERES = "SELECT u.no_utilisateur, u.pseudo, a.no_article, a.article, a.prix_vente, a.date_fin_encheres, c.no_categorie, c.libelle" +
            "FROM UTILISATEURS u INNER JOIN ARTICLES a ON u.no_utlisateur = a.no_article" +
            "INNER JOIN CATEGORIES c ON a.no_article = c.no_categorie";

    @Override
    public void insert(Article obj) throws GlobalException {

    }

    @Override
    public void update(Article obj) throws GlobalException {

    }

    @Override
    public void delete(int id) throws GlobalException {

    }

    @Override
    public Article selectById(int id) throws GlobalException {
        return null;
    }

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
                //J'acjoute l'article à la liste
                list.add(artAjout);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Je renvoie la liste d'articles
        return list;
    }

//    @SneakyThrows
//    @Override
//    public Article selectByArticle(String article) throws GlobalException {
//        Article a = new Article();
//        try (
//                Connection con = ConnectionProvider.getConnection()
//        ) {
//            PreparedStatement pstt3 = con.prepareCall(SQL_SELECT_BY_ARTICLE);
//            ResultSet rs = pstt3.executeQuery();
//
//            //On choisit les paramètres de son objet avec le get
//            while (rs.next()) {
//                a.setArticle(rs.getString("article"));
//                //a.setDateFin(rs.getDate("dateFin".toLocalDateTime()));
//                a.setPrixVente(rs.getInt("prixVente"));
//                //a.setUtilisateur(rs.);
//                pstt3.executeQuery();
//            }
//        }
//        try {
//            return IGenericDao.super.selectByArticle(article);
//        } catch (GlobalException e) {
//            e.printStackTrace();
//        }
//        return a;
//    }
//
//    @Override
//    public Article selectById(int id) throws GlobalException {
//        List<Article> list = new ArrayList<>();
//
//        try (
//                Connection con = ConnectionProvider.getConnection()
//        ) {
//            PreparedStatement pstt = con.prepareCall(SQL_SELECT_BY_ID);
//            ResultSet rs = pstt.executeQuery();
//            while (rs.next()) {
//
//                //On choisit les paramètres de son objet avec le get
//                list.getUtilisateur();
//                list.getCategorie();
//                list.setArticle(rs.getString("article"));
//                //a.setDateFin(rs.getDate("dateFin".toLocalDateTime()));
//                list.setPrixVente(rs.getInt("prixVente"));
//                pstt.executeQuery();*/
//            }
//        }
//        //return IGenericDao.super.selectByCriteres(list<Article>);
//        return list;
//    }
//
//    @SneakyThrows
//    @Override
//    public List<Article> selectByCriteres(String articleName, String catName, boolean openedEnchere,
//                                          boolean inprogressEnchere, boolean winEnchere,
//                                          boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException {
//        List<Article> list = new ArrayList<>();
//
//        try (
//                Connection con = ConnectionProvider.getConnection()
//        ) {
//            PreparedStatement pstt = con.prepareCall(SQL_SELECT_ARTICLES_BY_CRITERES);
//            ResultSet rs = pstt.executeQuery();
//            while (rs.next()) {
//
///*                //On choisit les paramètres de son objet avec le get
//                list.getUtilisateur();
//                list.getCategorie();
//                list.setArticle(rs.getString("article"));
//                //a.setDateFin(rs.getDate("dateFin".toLocalDateTime()));
//                list.setPrixVente(rs.getInt("prixVente"));
//                pstt.executeQuery();*/
//            }
//        }
//        //return IGenericDao.super.selectByCriteres(list<Article>);
//        return list;
//    }

}