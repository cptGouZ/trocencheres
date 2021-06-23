package dal.impl;

import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.IGenericDao;
import exception.DALException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class UtilisateurImpl implements IGenericDao<Utilisateur> {

    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
    private static final String SQL_SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";


    @Override
    public Utilisateur selectByLogin(String login) {

        Utilisateur utilisateurTrouve = new Utilisateur();

        try (
                Connection uneConnection = ConnectionProvider.getConnection();
                PreparedStatement pStmt1 = uneConnection.prepareStatement(SQL_SELECT_BY_EMAIL);
                PreparedStatement pStmt2 = uneConnection.prepareStatement(SQL_SELECT_BY_PSEUDO);
        ) {
            pStmt1.setString(1, login);
            ResultSet rs1 = pStmt1.executeQuery();

            while (rs1.next()) {
                Utilisateur utilisateurEnBdd = new Utilisateur();
                utilisateurEnBdd.setId(rs1.getInt("no_utilisateur"));
                utilisateurEnBdd.setPseudo(rs1.getString("pseudo"));
                utilisateurEnBdd.setNom(rs1.getString("nom"));
                utilisateurEnBdd.setPrenom(rs1.getString("prenom"));
                utilisateurEnBdd.setEmail(rs1.getString("email"));
                utilisateurEnBdd.setPassword(rs1.getString("telephone"));
                utilisateurEnBdd.setCredit(rs1.getInt("credit"));
                utilisateurEnBdd.setAdmin(rs1.getBoolean("administrateur"));

                if (utilisateurEnBdd.getEmail() == login.trim()) {
                    utilisateurTrouve = utilisateurEnBdd;
                }
            }
            pStmt1.setString(1, login);
            ResultSet rs2 = pStmt1.executeQuery();

            while (rs1.next()) {
                Utilisateur utilisateurEnBdd = new Utilisateur();
                utilisateurEnBdd.setId(rs1.getInt("no_utilisateur"));
                utilisateurEnBdd.setPseudo(rs1.getString("pseudo"));
                utilisateurEnBdd.setNom(rs1.getString("nom"));
                utilisateurEnBdd.setPrenom(rs1.getString("prenom"));
                utilisateurEnBdd.setEmail(rs1.getString("email"));
                utilisateurEnBdd.setPassword(rs1.getString("telephone"));
                utilisateurEnBdd.setCredit(rs1.getInt("credit"));
                utilisateurEnBdd.setAdmin(rs1.getBoolean("administrateur"));

                if (utilisateurEnBdd.getPseudo() == login.trim()) {
                    utilisateurTrouve = utilisateurEnBdd;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return utilisateurTrouve;
    }


    @Override
    public void insert(Utilisateur obj) throws DALException {

    }

    @Override
    public void update(Utilisateur obj) throws DALException {

    }

    @Override
    public void delete(int id) throws DALException {

    }

    @Override
    public Utilisateur selectById(int id) throws DALException {
        final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
        Utilisateur retour = null;
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID)) {
            ResultSet rs = pstmt.executeQuery();
            pstmt.setInt(1, id);
            while (rs.next()) {
                retour = new Utilisateur();
                retour.setId(rs.getInt("no_utilisateur"));
                retour.setAdresse(null);
                retour.setPseudo(rs.getString("pseudo"));
                retour.setNom(rs.getString("nom"));
                retour.setPrenom(rs.getString("prenom"));
                retour.setEmail(rs.getString("email"));
                retour.setPhone(rs.getString("phone"));
                retour.setPassword(rs.getString("mdp"));
                retour.setCredit(rs.getInt("credit"));
                retour.setAdmin(rs.getBoolean("administrateur"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            new DALException(sqle.getMessage());
        }
        return retour;
    }

    @Override
    public List<Utilisateur> selectAll() throws DALException {
        return null;
    }


