package dal.impl;

import bo.Adresse;
import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.DaoProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDal implements IGenericDao<Utilisateur> {

    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
    private static final String SQL_SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";

    @Override
    public Utilisateur selectByEmail(String login) {

        Utilisateur utilisateurEnBdd = null ;

        try (
                Connection uneConnection = ConnectionProvider.getConnection();
                PreparedStatement pStmt = uneConnection.prepareStatement(SQL_SELECT_BY_EMAIL);
        ) {
            pStmt.setString(1, login);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                utilisateurEnBdd = new Utilisateur();
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));
                utilisateurEnBdd.setPseudo(rs.getString("pseudo"));
                utilisateurEnBdd.setNom(rs.getString("nom"));
                utilisateurEnBdd.setPrenom(rs.getString("prenom"));
                utilisateurEnBdd.setEmail(rs.getString("email"));
                utilisateurEnBdd.setPassword(rs.getString("mdp"));
                utilisateurEnBdd.setCredit(rs.getInt("credit"));
                utilisateurEnBdd.setAdmin(rs.getBoolean("administrateur"));
                IGenericDao<Adresse> ad = DaoProvider.getAdresseDao();
                utilisateurEnBdd.setAdresse(ad.selectUserDomicile(utilisateurEnBdd.getId()));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return utilisateurEnBdd;
    }

    @Override
    public Utilisateur selectByPseudo(String pseudo) {

        Utilisateur utilisateurEnBdd = null;

        try (
                Connection uneConnection = ConnectionProvider.getConnection();
                PreparedStatement pStmt = uneConnection.prepareStatement(SQL_SELECT_BY_PSEUDO);
        ) {
            pStmt.setString(1, pseudo);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                utilisateurEnBdd = new Utilisateur();
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));
                utilisateurEnBdd.setPseudo(rs.getString("pseudo"));
                utilisateurEnBdd.setNom(rs.getString("nom"));
                utilisateurEnBdd.setPrenom(rs.getString("prenom"));
                utilisateurEnBdd.setEmail(rs.getString("email"));
                utilisateurEnBdd.setPassword(rs.getString("mdp"));
                utilisateurEnBdd.setCredit(rs.getInt("credit"));
                utilisateurEnBdd.setAdmin(rs.getBoolean("administrateur"));
                IGenericDao<Adresse> ad = DaoProvider.getAdresseDao();
                utilisateurEnBdd.setAdresse(ad.selectUserDomicile(utilisateurEnBdd.getId()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return utilisateurEnBdd;
    }


    @Override
    public void insert(Utilisateur obj) throws GlobalException {
        final String INSERT = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, mdp, credit, administrateur) " +
                              "VALUES (?, ?, ?, ?, ?, ?, 0, 0)";
        try (
                Connection uneConnection = ConnectionProvider.getConnection();
                PreparedStatement pstmt = uneConnection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ){
            pstmt.setString(1, obj.getPseudo());
            pstmt.setString(2, obj.getNom());
            pstmt.setString(3, obj.getPrenom());
            pstmt.setString(4, obj.getEmail());
            pstmt.setString(5, obj.getPhone());
            pstmt.setString(6, obj.getPassword());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                obj.setId(rs.getInt(1));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(UserException.USER_INSERTION_ERROR);
            throw GlobalException.getInstance();
        }
    }

    //TODO mise à jour du statut administrateur
    @Override
    public void update(Utilisateur obj) throws GlobalException {
        final String UPDATE = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, mdp=?, credit=?, administrateur=0 WHERE no_utilisateur=?";
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(UPDATE)) {
            pstmt.setString(1, obj.getPseudo());
            pstmt.setString(2, obj.getNom());
            pstmt.setString(3, obj.getPrenom());
            pstmt.setString(4, obj.getEmail());
            pstmt.setString(5, obj.getPhone());
            pstmt.setString(6, obj.getPassword());
            pstmt.setInt(7, obj.getCredit());
            pstmt.setInt(8,obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            GlobalException.getInstance().addError(UserException.USER_UPDATE_ERROR);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public void delete(int id) throws GlobalException {
        final String DELETE = "DELETE FROM utilisateurs WHERE no_utilisateur = ?";
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(DELETE)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(UserException.USER_DELETION_ERROR);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public Utilisateur selectById(int id) throws GlobalException {
        final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
        Utilisateur retour = null;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                retour = new Utilisateur();
                retour.setId(rs.getInt("no_utilisateur"));
                retour.setPseudo(rs.getString("pseudo"));
                retour.setNom(rs.getString("nom"));
                retour.setPrenom(rs.getString("prenom"));
                retour.setEmail(rs.getString("email"));
                retour.setPhone(rs.getString("telephone"));
                retour.setPassword(rs.getString("mdp"));
                retour.setCredit(rs.getInt("credit"));
                retour.setAdmin(rs.getBoolean("administrateur"));
                IGenericDao<Adresse> ad = DaoProvider.getAdresseDao();
                retour.setAdresse(ad.selectUserDomicile(id));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            GlobalException.getInstance().addError(UserException.SELECT_BY_USER_ID);
            throw GlobalException.getInstance();
        }
        return retour;
    }

    @Override
    public List<Utilisateur> selectAll() throws GlobalException {
        List<Utilisateur> retour = new ArrayList<>();

        return retour;
    }
}


