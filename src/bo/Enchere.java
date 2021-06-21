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
public class Enchere implements Serializable {
    Utilisateur utilisateur;
    Article article;
    LocalDate date;
    Integer montant;
}
