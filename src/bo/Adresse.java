package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {
    private Integer id;
    private String rue;
    private String cpo;
    private String ville;
    private Boolean domicile;
    private Integer userId;

    public Adresse(String rue, String cpo, String ville) {
        this.rue = rue;
        this.cpo = cpo;
        this.ville = ville;
    }
    public Adresse(String rue, String cpo, String ville, boolean domicile) {
        this.rue = rue;
        this.cpo = cpo;
        this.ville = ville;
        this.domicile = domicile;
    }
    public Adresse(String rue, String cpo, String ville, int userId) {
        this.rue = rue;
        this.cpo = cpo;
        this.ville = ville;
        this.userId = userId;
    }
    public Adresse(String rue, String cpo, String ville, int userId, boolean domicile) {
        this.rue = rue;
        this.cpo = cpo;
        this.ville = ville;
        this.domicile = domicile;
        this.userId = userId;
    }

}
