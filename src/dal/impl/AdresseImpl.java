package dal.impl;

import bo.Adresse;
import dal.IGenericDao;
import exception.DALException;

import java.util.ArrayList;
import java.util.List;

public class AdresseImpl implements IGenericDao<Adresse> {
    @Override
    public void insert(Adresse obj) throws DALException {

    }

    @Override
    public void update(Adresse obj) throws DALException {

    }

    @Override
    public void delete(int id) throws DALException {

    }

    @Override
    public Adresse selectById(int id) throws DALException {
        return new Adresse();
    }

    @Override
    public List<Adresse> selectAll() throws DALException {
        return new ArrayList<>();
    }
}
