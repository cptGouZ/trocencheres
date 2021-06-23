package dal.impl;

import bo.Utilisateur;
import dal.ConnectionProvider;
import dal.IGenericDao;
import exception.DALException;

import java.sql.*;
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
        return null;
    }

    @Override
    public List<Utilisateur> selectAll() throws DALException {
        return null;
    }
}


