package cloud.masteroflie.sgpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetupDTO {
    private String nome;
    private String email;
    private String cidade;
    private String estado;
    private String setor;
    private String cpfCnpj;
    private String senha;
    private String confirmarSenha;
    private String nomeAplicacao;
    private String azureString;
    private String container;
}
