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
    Integer userId;

    public Adresse(String rue, String cpo, String ville, boolean domicile) {
    }
}
