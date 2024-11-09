package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Arquivos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArquivoRepository extends JpaRepository<Arquivos, UUID> {
}
