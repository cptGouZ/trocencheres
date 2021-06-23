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
    Adresse adresse=new Adresse(0,null, "rue des églantiers", "44800", "Sautron");
    String pseudo="Gouz"+id;
    String nom="toto";
    String prenom="julien";
    String email="julien@gmail.com";
    String phone ="0628187989";
    String password="toto";
    java.lang.Integer credit=200;
    boolean administrateur;


    // Constructeur pour CONNEXION
    public Utilisateur(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }
}




