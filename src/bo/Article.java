package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
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
    private Integer prixInitiale;
    private Adresse adresseRetrait;
}

