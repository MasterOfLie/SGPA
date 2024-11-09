package cloud.masteroflie.sgpa.models;

import cloud.masteroflie.sgpa.enums.ProcessoStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Processo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long protocolo;

    private UUID uuid = UUID.randomUUID();

    private Long vigencia = (long) LocalDateTime.now().getYear();

    @Lob
    private String descricao;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    private ProcessoStatus Status = ProcessoStatus.ANDAMENTO;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = true)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = true)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "requerente_id", nullable = true)
    private Usuario requerente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "processo")
    private List<Movimentacao> movimentacoes;

    @OneToMany(mappedBy = "processo")
    private List<Arquivos> Arquivos;

    public Long getProtocolo() {
        return this.id;
    }
}
