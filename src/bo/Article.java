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
    private Integer id = 1;
    private Utilisateur utilisateur = new Utilisateur();
    private Categorie categorie = new Categorie(1,"sports");
    private String article = "Raquette" ;
    private String description = "Pour pratiquer le tennis indoor";
    private LocalDateTime dateDebut = LocalDateTime.of(2021,11,02,02,12);
    private LocalDateTime dateFin = LocalDateTime.of(2021,11,07,05,22);
    private Integer prixVente = 49;
    private Integer prixInitiale = 1 ;

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }
}

