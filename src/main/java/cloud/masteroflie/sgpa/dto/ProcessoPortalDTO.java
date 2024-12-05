package cloud.masteroflie.sgpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessoPortalDTO {

    UUID servicoID;
    String descricao;

}
