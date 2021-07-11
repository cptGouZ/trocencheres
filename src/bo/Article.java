package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private Integer id;
    private Utilisateur utilisateur;
    private Categorie categorie;
    private String article;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer prixVente;
    private Integer prixInitial;
    private Adresse adresseRetrait;
    private Boolean isRetire;

    public Article(Utilisateur utilisateur, Categorie categorie, String article, String description, LocalDateTime dateDebut, LocalDateTime dateFin, Integer prixInitial, Adresse adresseRetrait) {
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.article = article;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixInitial = prixInitial;
        this.adresseRetrait = adresseRetrait;
        isRetire=false;
    }

    public boolean isOuvert(){
        boolean retour = false;
        LocalDateTime now = LocalDateTime.now();
        if (dateDebut.compareTo(now) < 0 && 0 < dateFin.compareTo(now))
            retour = true;
        return retour;
    }
}

