package cloud.masteroflie.sgpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aplicacao {

    @Id
    private String aplicacao = "aplicacao";
    private String nome;
    private String containerBlob;
    private String theme;

}
