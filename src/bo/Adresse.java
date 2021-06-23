package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {
    Integer id;
    String rue;
    String cpo;
    String ville;
    boolean domicile;

    public Adresse(String rue, String cpo, String ville, boolean domicile) {
    }
}
