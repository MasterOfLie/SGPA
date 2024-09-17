package cloud.masteroflie.sgpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private String nome;

    public UsuarioDTO() {}

    public UsuarioDTO(String username, String nome, String password) {
        this.username = username;
        this.nome = nome;
        this.password = password;
    }
}
