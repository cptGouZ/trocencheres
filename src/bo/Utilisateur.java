package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements Serializable {
    private int id =0;
    private Adresse adresse=null;
    private String pseudo="Gouz"+id;
    private String nom="toto";
    private String prenom="julien";
    private String email="julien@gmail.com";
    private String phone ="0628187989";
    private String password="toto";
    private Integer credit=200;
    private boolean admin;
    private List<Adresse> adresses = new ArrayList<>();

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

    public Utilisateur(Adresse adresse, String pseudo, String nom, String prenom, String email, String phone, int credit, boolean admin) {
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.credit = credit;
        this.admin = admin;
    }

    //TODO A supprimer plus tard ?
    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
    }

    public Utilisateur(Adresse adresse, String pseudo, String nom, String prenom, String email, String phone) {
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
    }
}




