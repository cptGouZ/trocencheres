package bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Retrait implements Serializable {
    Integer idArticle;
    String rue;
    String cpo;
    String ville;
}
