package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Setor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SetorRepository extends JpaRepository<Setor, UUID> {

    boolean existsSetorByNome(String nome);
    Setor findByNome(String nombre);

}
