package dal;

import bo.Adresse;
import bo.Article;
import bo.Utilisateur;
import dal.impl.UtilisateurImpl;
import exception.GlobalException;

import java.util.List;

public interface IGenericDao<T> {
    //Méthodes communes à tout le monde
    void insert(T obj) throws GlobalException;
    void update(T obj) throws GlobalException;
    void delete(int id) throws GlobalException;
    T selectById(int id) throws GlobalException;
    List<T> selectAll() throws GlobalException;

    //Méthode spécifique à Utilisateur
    default Utilisateur selectByEmail(String email) throws GlobalException {return null;}
    default Utilisateur selectByPseudo(String pseudo) throws GlobalException {return null;}

    //Méthodes spécifiques à Article
    default Article selectByArticle(String article) throws GlobalException {return null;}
    default Article insertNewArticle(T nouvelArticle) throws GlobalException {return null;}
    default List<Article> selectByCriteres(String articleName, String catName, boolean openedEnchere,
                                            boolean inprogressEnchere, boolean winEnchere,
                                            boolean inprogressVente, boolean beforeVente, boolean finishedVente) throws GlobalException {return null;}

    //Méthode spécifique à Adresse
    default List<Adresse> selectAllAdresseByUser(int userId) throws GlobalException {return null;}
}
