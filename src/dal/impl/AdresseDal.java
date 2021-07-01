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
    private static final String SQL_SELECT_BY_USER_ID = "SELECT * FROM ADRESSES WHERE no_utilisateur=? AND domicile=1 " ;

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
            GlobalException.getInstance().addError(AdresseException.INSERT);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public void update(Adresse obj) throws GlobalException {
        final String UPDATE = "UPDATE adresses SET rue=?, cpo=?, ville=?, domicile=? WHERE no_adresse=?";
        try(Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
        ){
            pstmt.setString(1, obj.getRue());
            pstmt.setString(2, obj.getCpo());
            pstmt.setString(3, obj.getVille());
            pstmt.setBoolean(4, obj.getDomicile());
            pstmt.setInt(5, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AdresseException.UPDATE);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public void delete(int id) throws GlobalException {
        final String INSERT = "INSERT INTO adresses (rue, cpo, ville, no_utilisateur, domicile) VALUES (?, ?, ?, ?, ?)";
        try(Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(INSERT);
        ){
            pstmt.setInt(1, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AdresseException.DELETE);
            throw GlobalException.getInstance();
        }
    }

    @Override
    public Adresse selectById(int id) throws GlobalException {
        final String SQL_SELECT_BY_ID = "SELECT * FROM ADRESSES WHERE no_adresse=?" ;
        Adresse adresseRecherchee = null;
        try (
                Connection uneConnection = ConnectionProvider.getConnection();
                PreparedStatement pStmt = uneConnection.prepareStatement(SQL_SELECT_BY_ID);
        ) {
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                adresseRecherchee = adresseFromRs(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AdresseException.SELECT_BY_ID);
            throw GlobalException.getInstance();
        }
        return adresseRecherchee ;
    }

    @Override
    @Deprecated
    public List<Adresse> selectAll() throws GlobalException {
        return new ArrayList<>();
    }

    @Override
    @Deprecated
    public List<Adresse> selectAllAdresseByUser(int userId) throws GlobalException {
        return new ArrayList<>();
    }

    public Adresse selectUserDomicile(int idUtilisateur) throws GlobalException {
        Adresse adresseRecherchee = null;
        try (
                Connection uneConnection = ConnectionProvider.getConnection();
                PreparedStatement pStmt = uneConnection.prepareStatement(SQL_SELECT_BY_USER_ID);
        ) {
            pStmt.setInt(1, idUtilisateur);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                adresseRecherchee = adresseFromRs(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            GlobalException.getInstance().addError(AdresseException.SELECT_ALL_EXCEPTION);
            throw GlobalException.getInstance();
        }
        return adresseRecherchee ;
    }
    private Adresse adresseFromRs(ResultSet rs) throws SQLException {
        Adresse retour = new Adresse();
        retour.setId(rs.getInt("no_adresse"));
        retour.setRue(rs.getString("rue"));
        retour.setCpo(rs.getString("cpo"));
        retour.setVille(rs.getString("ville"));
        retour.setDomicile(rs.getBoolean("domicile"));
        retour.setUserId(rs.getInt("no_utilisateur"));
        return retour;
    }
}
