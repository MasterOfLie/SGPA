package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {
   Boolean existsSetorByNome(String nome);
}
