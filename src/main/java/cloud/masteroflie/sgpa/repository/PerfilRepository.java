package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
    Boolean existsByNome(String nome);
}
