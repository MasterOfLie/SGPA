package cloud.masteroflie.sgpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigDTO {

    private String nome;
    private String containerBlob;
    private String theme;
}
