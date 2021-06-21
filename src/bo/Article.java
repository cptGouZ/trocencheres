package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    Integer idArticle;
    Utilisateur utilisateur;
    Categorie categorie;
    String article;
    String description;
    LocalDate dateDebut;
    LocalDate dateFin;
    Integer prixVente;
}

