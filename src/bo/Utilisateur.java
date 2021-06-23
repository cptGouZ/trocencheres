package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements Serializable {
    Integer id =0;
    Adresse adresse=null;
    String pseudo="Gouz"+id;
    String nom="toto";
    String prenom="julien";
    String email="julien@gmail.com";
    String phone ="0628187989";
    String password="toto";
    java.lang.Integer credit=200;
    boolean admin;


    // Constructeur pour CONNEXION
    public Utilisateur(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public Utilisateur(Adresse adresse, String pseudo, String nom, String prenom, String email, String phone, String password, int credit, boolean admin) {
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.credit = credit;
        this.admin = admin;
    }
}




