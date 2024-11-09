package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Perfil;
import cloud.masteroflie.sgpa.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByEmail(String email);
    Usuario findByCpfCnpj(String cpfCnpj);
    List<Usuario> findAllByPerfil(Perfil perfil);

}
