package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);

}
