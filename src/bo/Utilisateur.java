package bo;

import dal.DaoProvider;
import exception.GlobalException;
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
    private Integer id;
    private Adresse adresse;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private String password;
    private Integer credit;
    private boolean admin;
    private List<Adresse> adresses = new ArrayList<>();

    public Utilisateur(Adresse adresse, String pseudo, String nom, String prenom, String email, String phone) {
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
    }

    public Integer getCreditDispo() throws GlobalException {
        return getCredit()-DaoProvider.getEnchereDao().sumEnchereByUser(getId());
    }
}




