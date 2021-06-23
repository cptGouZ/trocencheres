package dal;

import exception.DALException;

import java.util.List;

public interface IGenericDao<T> {
    void insert(T obj) throws DALException;
    void update(T obj) throws DALException;
    void delete(int id) throws DALException;
    T selectById(int id) throws DALException;
    List<T> selectAll() throws DALException;
}
