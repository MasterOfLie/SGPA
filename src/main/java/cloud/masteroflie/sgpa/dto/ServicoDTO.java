package cloud.masteroflie.sgpa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private UUID setorID;
    private Boolean aberturaOnline;
    private UUID departamentoID;

}
