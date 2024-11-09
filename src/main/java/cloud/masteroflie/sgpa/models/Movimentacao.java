package cloud.masteroflie.sgpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "departamento_origem_id", nullable = false)
    private Departamento departamentoOrigem;

    @ManyToOne
    @JoinColumn(name = "departamento_destino_id", nullable = false)
    private Departamento departamentoDestino;

    @ManyToOne
    @JoinColumn(name = "processo_id")
    private Processo processo;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioMovimentacao;

    @CreationTimestamp
    private LocalDateTime dataCriacao;




}
