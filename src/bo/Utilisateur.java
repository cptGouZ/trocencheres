package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements Serializable {
    Integer idUtilisateur=0;
    Adresse adresse=new Adresse(0,null, "rue des églantiers", "44800", "Sautron");
    String pseudo="Gouz2";
    String nom="toto";
    String prenom="julien";
    String email="julien@gmail.com";
    String telephone="0628187989";
    String password="toto";
    Integer credit=200;
    boolean administrateur;
}
