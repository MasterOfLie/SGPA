package cloud.masteroflie.sgpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessosDTO {

    private Long id;
    private String nomeServico;
    private String descricao;
    private Long protocolo;
    private String anoVigencia;
    private String nomeRequerente;
    private String funcionario;
    private String departamento;

}
