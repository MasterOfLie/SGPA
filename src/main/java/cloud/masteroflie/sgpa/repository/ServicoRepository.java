package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<Servico, UUID> {
    boolean existsServicoByNome(String nome);
}
