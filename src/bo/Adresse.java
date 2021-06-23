package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {
    Integer id;
    Utilisateur userId;
    String rue;
    String cpo;
    String ville;
}
