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
    private Integer id;
    private Utilisateur user;
    private Article article;
    private LocalDateTime dateEnchere;
    private Integer montant;
}
