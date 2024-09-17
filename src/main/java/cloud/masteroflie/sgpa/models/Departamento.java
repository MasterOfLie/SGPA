package cloud.masteroflie.sgpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_departamento")
@Getter
@Setter
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isActive = true;

    private String nome;

    @ManyToMany(mappedBy = "departamentos")
    @JsonIgnore
    private Set<Usuario> usuarios = new HashSet<>();

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Processo> processos = new HashSet<>();

}
