package dal;

import bo.Article;
import bo.Utilisateur;
import dal.impl.UtilisateurImpl;
import exception.DALException;

import java.util.List;

public interface IGenericDao<T> {
    //Méthodes communes à tout le monde
    void insert(T obj) throws DALException;
    void update(T obj) throws DALException;
    void delete(int id) throws DALException;
    T selectById(int id) throws DALException;
    List<T> selectAll() throws DALException;

    //Méthode spécifique à Utilisateur
    default Utilisateur selectByLogin(String login) {return null;}

    //Méthode spécifique à Article
    default Article selectByArticle(String article) {return null;}
}
