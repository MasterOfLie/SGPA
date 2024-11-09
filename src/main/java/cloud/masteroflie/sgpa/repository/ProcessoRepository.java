package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    @Query("SELECT MAX(p.protocolo) FROM Processo p")
    Long findMaxProtocolo();

    Processo findByUuid(UUID idProcesso);
}
