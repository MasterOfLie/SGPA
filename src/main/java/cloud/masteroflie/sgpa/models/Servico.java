package cloud.masteroflie.sgpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_servico")
@Getter
@Setter
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private boolean isActive;

    public boolean getAtivo(){
        return isActive;
    }

    @OneToMany(mappedBy = "servicos", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Processo> processos = new HashSet<>();

}
