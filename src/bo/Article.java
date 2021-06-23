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
    Integer id = 1;
    Utilisateur utilisateur = new Utilisateur();
    Categorie categorie = new Categorie(1,"sports");
    String article = "Raquette" ;
    String description = "Pour pratiquer le tennis indoor";
    LocalDateTime dateDebut = LocalDateTime.of(2021,11,02,02,12);
    LocalDateTime dateFin = LocalDateTime.of(2021,11,07,05,22);
    Integer prixVente = 49;

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }
}

