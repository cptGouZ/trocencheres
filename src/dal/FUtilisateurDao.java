package dal;

import dal.impl.UtilisateurDaoImpl;

public class FUtilisateurDao {
    public static IUtilisateurDao getConnexionDao() {
        return new UtilisateurDaoImpl();
    }
}
