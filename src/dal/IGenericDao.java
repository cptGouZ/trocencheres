package dal;

import bo.Adresse;
import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
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
    default List<Article> selectByCrit2(String articleName, Integer catId, boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb,
                                        boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours, Utilisateur util) throws GlobalException {return null;}

    //Méthode spécifique à Adresse
    default List<Adresse> selectAllAdresseByUser(int userId) throws GlobalException {return null;}
    default Adresse selectUserDomicile(int id) throws GlobalException {return null ;}

    //Methode spécifique à Categorie
    default List<String> selectLibelleCategories() throws GlobalException {return null;}

    //Méthode spécifique à Enchere
    default Enchere selectEnchereMaxByIdArticle(int idArticle) throws GlobalException {return null;}
    default Integer sumEnchereByUser(int userId) throws GlobalException {return null;}
}
