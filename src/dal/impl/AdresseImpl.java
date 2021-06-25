package dal.impl;

import bo.Adresse;
import dal.ConnectionProvider;
import dal.IGenericDao;
import exception.GlobalException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresseImpl implements IGenericDao<Adresse> {
    @Override
    public void insert(Adresse obj) throws GlobalException {
        try(Connection cnx = ConnectionProvider.getConnection()){} catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Adresse obj) throws GlobalException {

    }

    @Override
    public void delete(int id) throws GlobalException {

    }

    @Override
    public Adresse selectById(int id) throws GlobalException {
        return new Adresse();
    }

    @Override
    public List<Adresse> selectAll() throws GlobalException {
        return new ArrayList<>();
    }

    @Override
    public List<Adresse> selectAllAdresseByUser(int userId) throws GlobalException {
        return IGenericDao.super.selectAllAdresseByUser(userId);
    }
}
