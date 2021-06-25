package dal.impl;

import bo.Adresse;
import dal.ConnectionProvider;
import dal.IGenericDao;
import exception.GlobalException;
import exception.exceptionEnums.AdresseException;
import exception.exceptionEnums.AppException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresseDal implements IGenericDao<Adresse> {

    private static final String SQL_SELECT_BY_ID = "SELECT * FROM ADRESSES WHERE no_utilisateur=? AND domicile=1 " ;

    /**
     * Permet d'enregistrer une nouvelle adresse en BDD
     * L'adresse transmise doit contenir ses infos y compris le n° d'utilisateur et si il s'agit du domicile
     * @param obj : Adresse
     * @throws GlobalException
     */
    @Override
    public void insert(Adresse obj) throws GlobalException {
        final String INSERT = "INSERT INTO adresses (rue, cpo, ville, no_utilisateur, domicile) VALUES (?, ?, ?, ?, ?)";
        try(Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ){
            pstmt.setString(1, obj.getRue());
            pstmt.setString(2, obj.getCpo());
            pstmt.setString(3, obj.getVille());
            pstmt.setInt(4, obj.getUserId());
            pstmt.setBoolean(5, obj.getDomicile());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while(rs.next()){
                obj.setId(rs.getInt(1));
            }
            if(obj.getId()==null)
                GlobalException.getInstance().addError(AdresseException.INSERT_ABORT);

            if(GlobalException.getInstance().hasErrors())
                throw GlobalException.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AppException.CONNECTION_ERROR);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public void update(Adresse obj) throws GlobalException {

    }

    @Override
    public void delete(int id) throws GlobalException {
        final String INSERT = "INSERT INTO adresses (rue, cpo, ville, no_utilisateur, domicile) VALUES (?, ?, ?, ?, ?)";
        try(Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ){
            pstmt.setInt(1, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AppException.CONNECTION_ERROR);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public Adresse selectById(int id) throws GlobalException {
        return new Adresse();
    }

    @Override
    public List<Adresse> selectAll() throws GlobalException {
        return new ArrayList<>();
    }

    @Override
    public List<Adresse> selectAllAdresseByUser(int userId) throws GlobalException {
        return IGenericDao.super.selectAllAdresseByUser(userId);
    }

    public Adresse selectUserDomicile(int idUtilisateur) {

        Adresse adresseRecherchee = null;

        try (
                Connection uneConnection = ConnectionProvider.getConnection();
                PreparedStatement pStmt = uneConnection.prepareStatement(SQL_SELECT_BY_ID);
        ) {
            pStmt.setInt(1, idUtilisateur);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                adresseRecherchee = new Adresse();
                adresseRecherchee.setId(rs.getInt("no_adresse"));

                System.out.println("test recup adresse : " + adresseRecherchee.getId());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return adresseRecherchee ;
    }
}
