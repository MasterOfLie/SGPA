package cloud.masteroflie.sgpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoDTO {
    private String descricao;
    private UUID departamentoID;
    private UUID solicitanteID;
    private UUID servicoID;
    private UUID processoID;
    private String status;
}
