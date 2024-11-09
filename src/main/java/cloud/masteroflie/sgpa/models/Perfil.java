package cloud.masteroflie.sgpa.models;

import cloud.masteroflie.sgpa.enums.PermissionsRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Perfil implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;
    private Boolean ativo = true;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<PermissionsRole> permissions;

    public Perfil(UUID id, String nome, String descricao, Boolean ativo, List<PermissionsRole> permissions) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.permissions = permissions;
    }
}
