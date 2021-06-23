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
    public Utilisateur selectByLogin(String login){



        try (
                Connection uneConnection = ConnectionProvider.getConnection();
                PreparedStatement pStmt1 = uneConnection.prepareStatement(SQL_SELECT_BY_EMAIL);
                PreparedStatement pStmt2 = uneConnection.prepareStatement(SQL_SELECT_BY_PSEUDO);
        ){
            ResultSet rs = pStmt1.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateurEnBdd = new Utilisateur();
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));
                utilisateurEnBdd.setId(rs.getInt("no_utilisateur"));


                if(utilisateurEnBdd.getEmail() == login.trim()){

                    .setId(rs.getInt("no_utilisateur"));
                    pStmt1.executeUpdate();
                    pStmt1.close();
                }
                catch (Exception e){
                    e.printStackTrace();

                }



            }

        }






        return new Utilisateur();
    }
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


