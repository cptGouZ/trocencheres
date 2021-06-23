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
    default Utilisateur selectByLogin(String login) throws DALException {return null;}

    //Méthodes spécifiques à Article
    default Article selectByArticle(String article) throws DALException {return null;}
    default List<Article> selectByCriterias(String articleName, String catName, boolean openedEnchere,
                                            boolean inprogressEnchere, boolean winEnchere,
                                            boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws DALException {return null;}
    default Utilisateur selectByEmail(String email) {return null;}
    default Utilisateur selectByPseudo(String pseudo) {return null;}

    //Méthode spécifique à Article
    default Article selectByArticle(String article) {return null;}
}
