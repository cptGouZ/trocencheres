package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements Serializable {
    Integer idUtilisateur;
    Adresse adresse;
    String pseudo;
    String nom;
    String prenom;
    String email;
    String telephone;
    String password;
    Integer credit;
    boolean administrateur;
}
