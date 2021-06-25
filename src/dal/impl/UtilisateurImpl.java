package dal.impl;

import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurImpl implements IGenericDao<Utilisateur> {

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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return utilisateurEnBdd;
    }


    @Override
    public void insert(Utilisateur obj) throws GlobalException {

    }

    @Override
    public void update(Utilisateur obj) throws GlobalException {

    }

    @Override
    public void delete(int id) throws GlobalException {

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
                retour.setAdresse(null);
                retour.setPseudo(rs.getString("pseudo"));
                retour.setNom(rs.getString("nom"));
                retour.setPrenom(rs.getString("prenom"));
                retour.setEmail(rs.getString("email"));
                retour.setPhone(rs.getString("telephone"));
                retour.setPassword(rs.getString("mdp"));
                retour.setCredit(rs.getInt("credit"));
                retour.setAdmin(rs.getBoolean("administrateur"));
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


