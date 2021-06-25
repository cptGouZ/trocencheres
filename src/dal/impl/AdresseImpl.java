package dal.impl;

import bo.Adresse;
import dal.IGenericDao;
import exception.GlobalException;

import java.util.ArrayList;
import java.util.List;

public class AdresseImpl implements IGenericDao<Adresse> {
    @Override
    public void insert(Adresse obj) throws GlobalException {

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
}
