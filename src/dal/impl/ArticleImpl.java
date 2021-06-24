package dal.impl;

import bo.Article;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.IGenericDao;
import exception.DALException;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleImpl implements IGenericDao<Article> {

    private static final String SQL_SELECT_BY_ARTICLES = "SELECT u.id, u.pseudo, a.id, a.article, a.prixVente, a.dateFin" +
                                                         "FROM Utilisateurs u INNER JOIN Articles a ON u.id = a.id";
    private static final String SQL_SELECT_ARTICLES_BY_CRITERES = "SELECT u.id, u.pseudo, a.id, a.article, a.prixVente, a.dateFin, c.id, c.libelle" +
                                                                  "FROM Utilisateurs u INNER JOIN Articles a ON u.id = a.id" +
                                                                  "INNER JOIN Categories c ON a.id = c.id";

    @Override
    public void insert(Article obj) throws DALException {

    }

    @Override
    public void update(Article obj) throws DALException {

    }

    @Override
    public void delete(int id) throws DALException {

    }

    @Override
    public Article selectById(int id) throws DALException {
        return null;
    }

    @Override
    public List<Article> selectAll() throws DALException {
        return null;
    }

    @SneakyThrows
    @Override
    public Article selectByArticle(String article) {
        Article a = new Article();
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {
            PreparedStatement pstt = con.prepareCall(SQL_SELECT_BY_ARTICLES);
            ResultSet rs = pstt.executeQuery();

            //On choisit les paramètres de son objet avec le get
            while (rs.next()) {
                a.setArticle(rs.getString("article"));
                a.setDateFin(rs.getDate("dateFin".toLocalDateTime()));
                a.setPrixVente(rs.getInt("prixVente"));
                pstt.executeQuery();
            }
        }
        try {
            return IGenericDao.super.selectByArticle(article);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return a;
    }

    @SneakyThrows
    @Override
    public List<Article> selectByCriteres(String articleName, String catName, boolean openedEnchere,
                                           boolean inprogressEnchere, boolean winEnchere,
                                           boolean inprogressVente, boolean beforeVente, boolean finishedVente) {
        List<Article> list = new ArrayList<>();

        try (
                Connection con = ConnectionProvider.getConnection()
        ) {
            PreparedStatement pstt = con.prepareCall(SQL_SELECT_ARTICLES_BY_CRITERES);
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {

/*                //On choisit les paramètres de son objet avec le get
                list.getUtilisateur();
                list.getCategorie();
                list.setArticle(rs.getString("article"));
                //a.setDateFin(rs.getDate("dateFin".toLocalDateTime()));
                list.setPrixVente(rs.getInt("prixVente"));
                pstt.executeQuery();*/
            }
        }
        /*try {
            return IGenericDao.super.selectByCriterias(list<article>);
        } catch (DALException e) {
            e.printStackTrace();
        }*/
        return list;
    }
}