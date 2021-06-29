package dal.impl;

import bo.Adresse;
import bo.Enchere;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.EnchereException;
import exception.exceptionEnums.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnchereDal implements IGenericDao<Enchere> {

    private static final String SELECT_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_enchere = (SELECT MAX(no_enchere) FROM ENCHERES WHERE no_article=?)";

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

    }

    @Override
    public void update(Enchere obj) throws GlobalException {

    }

    @Override
    public void delete(int id) throws GlobalException {

    }

    @Override
    public Enchere selectById(int id) throws GlobalException {
        return null;
    }


    @Override
    public List<Enchere> selectAll() throws GlobalException {
        return null;
    }


    private Enchere EnchereFromRs(ResultSet rs) throws SQLException, GlobalException {

        Enchere retour = new Enchere();
        retour.setId(rs.getInt("no_enchere"));

        Utilisateur user = DaoProvider.getUtilisateurDao().selectById(rs.getInt("no_utilisateur"));

        retour.setUser(user);
        retour.setDateEnchere(rs.getTimestamp("date").toLocalDateTime());
        retour.setMontant(rs.getInt("montant"));

        return retour;
    }
}


