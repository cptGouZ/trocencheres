package dal.impl;

import bo.*;
import dal.ConnectionProvider;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.AppException;
import exception.exceptionEnums.ArticleException;
import exception.exceptionEnums.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDal implements IGenericDao<Article> {

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM ARTICLES WHERE no_article=?";

    @Override
    public List<Article> selectByCrit2(String articleName, Integer catId, boolean ventesTerm, boolean encheresOuv, boolean ventesNonDeb, boolean encheresEnCours, boolean encheresRemp, boolean ventesEnCours, Utilisateur util) throws GlobalException {

        String SQL_SELECT_ARTICLES_BY_CRITERES2 = "SELECT a.no_article as no_article, a.article as article, a.description as description, a.retrait as retrait," +
                "a.date_debut_encheres as date_debut_encheres, a.date_fin_encheres as date_fin_encheres, a.prix_initial as prix_initial, " +
                "a.prix_vente as prix_vente, a.no_utilisateur as no_utilisateur, a.no_adresse as no_adresse, a.no_categorie as no_categorie " +
                "FROM ARTICLES a INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie " +
                "WHERE a.article LIKE ?";

        //Je crée une liste
        List<Article> list = new ArrayList<Article>();
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {

            StringBuilder sqlConstruction2 = new StringBuilder(SQL_SELECT_ARTICLES_BY_CRITERES2);

            //Traitement de la catégorie
            if(!catId.equals(0)) {
                sqlConstruction2.append(" AND c.no_categorie = '"+ catId +"'");
            }

            //Je regarde mes achats
            if(encheresOuv == true || encheresEnCours == true || encheresRemp == true) {
                    if(util != null) {
                        sqlConstruction2.append(" AND NOT(a.no_utilisateur=" + util.getId() + ")"); }
                sqlConstruction2.append(" AND ( ");
                    //Enchère ouverte
                    if(encheresOuv) {
                        gestionOr(sqlConstruction2);
                        sqlConstruction2.append(" CAST(GETDATE() AS datetime) BETWEEN date_debut_encheres AND date_fin_encheres ");}
                    //Enchères en cours avec une enchere mini
                    if(encheresEnCours) {
                        gestionOr(sqlConstruction2);
                        sqlConstruction2.append(" (CAST(GETDATE() AS datetime) BETWEEN date_debut_encheres AND date_fin_encheres) AND no_article IN (SELECT no_article" +
                                " FROM Encheres INNER JOIN" +
                                " (SELECT MAX(no_enchere) AS no_enchere FROM Encheres WHERE no_utilisateur=" + util.getId() + "  GROUP BY no_article)" +
                                " AS t ON t.no_enchere = ENCHERES.no_enchere) ");}

                    //Enchères remportees(enchere + date terminée)
                    if(encheresRemp) {
                        gestionOr(sqlConstruction2);
                        sqlConstruction2.append(" date_fin_encheres < CAST(GETDATE() AS datetime)) AND no_article IN (SELECT no_article" +
                                " FROM Encheres INNER JOIN" +
                                " (SELECT MAX(no_enchere) AS no_enchere FROM Encheres WHERE no_utilisateur=" + util.getId() + " GROUP BY no_article)" +
                                " AS t ON t.no_enchere = ENCHERES.no_enchere");}
                sqlConstruction2.append(" ) ");
            }



            //Mes ventes
            //TODO attente la création des article pour pouvoir gérer les période de vente
            if(ventesTerm == true || ventesNonDeb == true || ventesEnCours == true) {
                    if(util != null) {
                        sqlConstruction2.append(" AND a.no_utilisateur=" + util.getId() ); }
                sqlConstruction2.append(" AND ( ");
                    //Ventes en cours
                    if (ventesEnCours) {
                        gestionOr(sqlConstruction2);
                        sqlConstruction2.append(" CAST(GETDATE() AS datetime) BETWEEN date_debut_encheres AND date_fin_encheres ");}
                    //Vente non déb
                    if(ventesNonDeb) {
                        gestionOr(sqlConstruction2);
                        sqlConstruction2.append(" date_debut_encheres > CAST(GETDATE() AS datetime) ");}
                    //Vente term
                    if(ventesTerm) {
                        gestionOr(sqlConstruction2);
                        sqlConstruction2.append(" date_fin_encheres < CAST(GETDATE() AS datetime) ");}
                sqlConstruction2.append(" ) ");
            }

            if(encheresOuv == false && encheresEnCours == false && encheresRemp == false && ventesTerm == false && ventesNonDeb == false && ventesEnCours == false) {
                sqlConstruction2.append(" AND CAST(GETDATE() AS datetime) BETWEEN date_debut_encheres AND date_fin_encheres ");
            }

            System.out.println(sqlConstruction2);

            PreparedStatement pstt = con.prepareCall(sqlConstruction2.toString());
            pstt.setString(1, "%" + articleName + "%");
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {
                list.add(articleFromRs(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AppException.CONNECTION_ERROR);
            throw GlobalException.getInstance();
        }
        return list;
    }

    private void gestionOr(StringBuilder sb){
        if(!" ( ".equals(sb.substring(sb.length()-3, sb.length())))
            sb.append(" OR ");
    }

    @Override
    public Article selectByArticle(String article) throws GlobalException {
        return IGenericDao.super.selectByArticle(article);
    }

    @Override
    public void update(Article obj) throws GlobalException {
        final String UPDATE = "UPDATE ARTICLES SET article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_adresse=?, no_categorie=?, retrait=?  WHERE no_article=?";

        try (Connection uneConnection = ConnectionProvider.getConnection();
             PreparedStatement pStmt = uneConnection.prepareStatement(UPDATE)
        ){
            pStmt.setString(1, obj.getArticle());
            pStmt.setString(2, obj.getDescription());
            pStmt.setTimestamp(3,java.sql.Timestamp.valueOf(obj.getDateDebut()));
            pStmt.setTimestamp(4,java.sql.Timestamp.valueOf(obj.getDateFin()));
            pStmt.setInt(5,obj.getPrixInitiale());
            pStmt.setInt(6,obj.getPrixVente());
            pStmt.setInt(7,obj.getUtilisateur().getId());
            pStmt.setInt(8,obj.getAdresseRetrait().getId());
            pStmt.setInt(9,obj.getCategorie().getId());
            pStmt.setBoolean(10,obj.getIsRetire());
            pStmt.setInt(11,obj.getId());

            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            GlobalException.getInstance().addError(ArticleException.ECHEC_MISE_A_JOUR_ARTICLE);
            throw GlobalException.getInstance();
        }
    }

    @Deprecated
    @Override
    public void delete(int id) throws GlobalException {}

    @Override
    public List<Article> selectAll() throws GlobalException {

        String SQL_SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES " +
                "WHERE CAST(GETDATE() AS datetime) BETWEEN date_debut_encheres AND date_fin_encheres";

        //Je crée un article et une liste
        List<Article> list = new ArrayList<Article>();
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection()
        ) {
            PreparedStatement pstt = con.prepareCall(SQL_SELECT_ALL_ARTICLES);
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {
                list.add(articleFromRs(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Je renvoie la liste d'articles
        return list;
    }

    private Article articleFromRs(ResultSet rs) throws SQLException, GlobalException {
        Article art = new Article();
        Categorie cat = DaoProvider.getCategorieDao().selectById(rs.getInt("no_categorie"));
        Utilisateur user = DaoProvider.getUtilisateurDao().selectById(rs.getInt("no_utilisateur"));
        Adresse adresse = DaoProvider.getAdresseDao().selectById(rs.getInt("no_adresse"));

        art.setId(rs.getInt("no_article"));
        art.setArticle(rs.getString("article"));
        art.setDescription(rs.getString("description"));
        art.setDateDebut(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
        art.setDateFin(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
        art.setPrixInitiale(rs.getInt("prix_initial"));
        art.setPrixVente(rs.getInt("prix_vente"));
        art.setIsRetire(rs.getBoolean("retrait"));
        art.setUtilisateur(user);
        art.setCategorie(cat);
        art.setAdresseRetrait(adresse);
        return art;
    }

    @Override
    public Article selectById(int id) throws GlobalException {
        //Je crée un article
        Article art = null;
        //Je lance la connexion
        try (
                Connection con = ConnectionProvider.getConnection();
                PreparedStatement pstt = con.prepareCall(SQL_SELECT_BY_ID);
        ) {
            pstt.setInt(1,id);
            ResultSet rs = pstt.executeQuery();
            while (rs.next()) {
                art = articleFromRs(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Je renvoie l'article
        return art;
    }

    @Override
    public void insert(Article newArticle) throws GlobalException {
        final String SQL_INSERT_ARTICLE = "insert into ARTICLES(article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, no_adresse, retrait) values(?,?,?,?,?,0,?,?,?,?);";
        try(Connection uneConnection = ConnectionProvider.getConnection();
            PreparedStatement pStmt = uneConnection.prepareStatement(SQL_INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);
        ){
            pStmt.setString(1,newArticle.getArticle());
            pStmt.setString(2,newArticle.getDescription());
            pStmt.setTimestamp(3,java.sql.Timestamp.valueOf(newArticle.getDateDebut()));
            pStmt.setTimestamp(4,java.sql.Timestamp.valueOf(newArticle.getDateFin()));
            pStmt.setInt(5,newArticle.getPrixInitiale());
            pStmt.setInt(6,newArticle.getUtilisateur().getId());
            pStmt.setInt(7,newArticle.getCategorie().getId());
            pStmt.setInt(8,newArticle.getAdresseRetrait().getId());
            pStmt.setBoolean(9,newArticle.getIsRetire());
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys() ;
                while(rs.next()){
                newArticle.setId(rs.getInt(1));
                }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AppException.CONNECTION_ERROR);
            throw GlobalException.getInstance();
        }
    }

}