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
    private Integer id;
    private Utilisateur userId;
    private LocalDate dateEnchere;
    private Integer montant;
}
