package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enchere implements Serializable {
    private Utilisateur id;
    private Article article;
    private LocalDate date;
    private Integer montant;
}
