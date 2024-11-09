package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, UUID> {
}
