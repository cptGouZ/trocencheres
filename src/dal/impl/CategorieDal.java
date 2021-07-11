package dal.impl;

import bo.Categorie;
import dal.ConnectionProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.AppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDal implements IGenericDao<Categorie> {

    @Override
    @Deprecated
    public void insert(Categorie obj) throws GlobalException {
        //Non implémenté
    }

    @Override
    @Deprecated
    public void update(Categorie obj) throws GlobalException {
        //Non implémenté
    }

    @Override
    @Deprecated
    public void delete(int id) throws GlobalException {
        //Non implémenté
    }

    @Override
    public Categorie selectById(int id) throws GlobalException {
        final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie=?";
        Categorie retour = null;
        try (
                Connection cnx = ConnectionProvider.getConnection();
                PreparedStatement pstt = cnx.prepareStatement(SELECT_BY_ID)
        ) {
            pstt.setInt(1, id);
            ResultSet rs = pstt.executeQuery();
            while(rs.next()){
                retour = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AppException.CONNECTION_ERROR);
            throw GlobalException.getInstance();
        }
        return retour;
    }

    @Override
    public List<Categorie> selectAll() throws GlobalException {
        final String SELECT_BY_ID = "SELECT * FROM CATEGORIES";
        List<Categorie> retour = new ArrayList<>();
        try (
                Connection cnx = ConnectionProvider.getConnection();
                PreparedStatement pstt = cnx.prepareStatement(SELECT_BY_ID)
        ) {
            ResultSet rs = pstt.executeQuery();
            while(rs.next()){
                retour.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AppException.CONNECTION_ERROR);
            throw GlobalException.getInstance();
        }
        return retour;
    }
}
