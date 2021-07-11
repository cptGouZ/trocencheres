package dal.impl;

import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.EnchereException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnchereDal implements IGenericDao<Enchere> {
    private static final String SELECT_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_enchere = (SELECT MAX(no_enchere) FROM ENCHERES WHERE no_article=?)";
    @Override
    public Integer sumEnchereByUser(int userId) throws GlobalException {
        final String SUM_ENCHERE_BY_USER="SELECT SUM(montant) AS montant " +
                                         "FROM ENCHERES " +
                                            "INNER JOIN (SELECT MAX(no_enchere) as no_enchere, no_utilisateur FROM ENCHERES GROUP BY no_article, no_utilisateur) AS t ON t.no_enchere = encheres.no_enchere " +
                                            "INNER JOIN ARTICLES A on ENCHERES.no_article = A.no_article " +
                                         "WHERE t.no_utilisateur=? and a.retrait=0;";
        int retour = 0;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(SUM_ENCHERE_BY_USER)
        ){
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                retour = rs.getInt( "montant");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(EnchereException.SELECT_BY_ARTICLE);
            throw GlobalException.getInstance();
        }
        return retour;
    }

    @Override
    public Enchere selectEnchereMaxByIdArticle(int idArticle) throws GlobalException {
        Enchere retour = null;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE)) {
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                retour = EnchereFromRs(rs);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(EnchereException.SELECT_BY_ARTICLE);
            throw GlobalException.getInstance();
        }
        return retour;
    }

    @Override
    public void insert(Enchere obj) throws GlobalException {
        final String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, montant) VALUES (?, ?, ?)";
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setInt(1, obj.getUser().getId());
            pstmt.setInt(2, obj.getArticle().getId());
            pstmt.setInt(3, obj.getMontant());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                obj.setId(rs.getInt(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(EnchereException.SELECT_BY_ARTICLE);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public void update(Enchere obj) throws GlobalException {
        final String UPDATE = "UPDATE ENCHERES SET no_utilisateur=?, no_article=?, date=?, montant=? WHERE no_enchere=?";
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(UPDATE)
        ) {
            pstmt.setInt(1, obj.getUser().getId());
            pstmt.setInt(2, obj.getArticle().getId());
            pstmt.setTimestamp(3, Timestamp.valueOf(obj.getDateEnchere()));
            pstmt.setInt(4, obj.getMontant());
            pstmt.setInt(5, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(EnchereException.SELECT_BY_ARTICLE);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public void delete(int id) throws GlobalException {
        final String DELETE = "DELETE FROM ENCHERES WHERE no_enchere=?";
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(DELETE)
        ) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(EnchereException.SELECT_BY_ARTICLE);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public Enchere selectById(int id) throws GlobalException {
        final String SELECT_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere=?";
        Enchere retour = null;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)
        ) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                retour = EnchereFromRs(rs);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(EnchereException.SELECT_BY_ARTICLE);
            throw GlobalException.getInstance();
        }
        return retour;
    }


    @Override
    public List<Enchere> selectAll() throws GlobalException {
        final String SELECT_ALL = "SELECT * FROM ENCHERES";
        List<Enchere> retour = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL)
        ) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                retour.add(EnchereFromRs(rs));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(EnchereException.SELECT_BY_ARTICLE);
            throw GlobalException.getInstance();
        }
        return retour;
    }


    private Enchere EnchereFromRs(ResultSet rs) throws SQLException, GlobalException {
        Enchere retour = new Enchere();
        Utilisateur user = DaoProvider.getUtilisateurDao().selectById(rs.getInt("no_utilisateur"));
        Article article = DaoProvider.getArticleDao().selectById(rs.getInt("no_article"));
        retour.setUser(user);
        retour.setArticle(article);
        retour.setId(rs.getInt("no_enchere"));
        retour.setDateEnchere(rs.getTimestamp("date").toLocalDateTime());
        retour.setMontant(rs.getInt("montant"));
        return retour;
    }
}


