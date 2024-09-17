package cloud.masteroflie.sgpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_processo")
@Getter
@Setter
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Long protocolo;
    private String anoVigencia;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "requerente_id", nullable = true)
    private Usuario requerente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servicos;


}
