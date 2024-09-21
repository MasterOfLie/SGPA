package cloud.masteroflie.sgpa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Arquivos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeArquivo;
    private String nomeBlob;
    @CreationTimestamp
    private LocalDateTime dataArquivo;

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

    public Arquivos(Processo processo, String nomeBlob, String nomeArquivo) {
        this.processo = processo;
        this.nomeBlob = nomeBlob;
        this.nomeArquivo = nomeArquivo;
    }

}
