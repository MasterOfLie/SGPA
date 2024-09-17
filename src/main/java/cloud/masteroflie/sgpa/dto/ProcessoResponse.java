package cloud.masteroflie.sgpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoResponse {
    private Long usuarioID;
    private Long servicoID;
    private Long departamentoID;
    private String descricaoProcesso;
}
