package dal.impl;

import bo.Utilisateur;
import dal.IGenericDao;
import exception.DALException;

import java.util.List;

public class UtilisateurImpl implements IGenericDao<Utilisateur> {
    @Override
    public void insert(Utilisateur obj) throws DALException {

    }

    @Override
    public void update(Utilisateur obj) throws DALException {

    }

    @Override
    public void delete(int id) throws DALException {

    }

    @Override
    public Utilisateur selectById(int id) throws DALException {
        return null;
    }

    @Override
    public List<Utilisateur> selectAll() throws DALException {
        return null;
    }
}
