package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.dto.UsuarioDTO;
import cloud.masteroflie.sgpa.models.Usuario;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {

    void cadastrarUsuario(Usuario usuario) throws Exception;
    List<Usuario> listarUsuarios();
    Usuario buscarUsuario(UUID usuarioID) throws Exception;
    void addPerfil(UsuarioDTO usuarioDTO) throws Exception;
    void removerPerfil(UsuarioDTO usuarioDTO) throws Exception;
    List<Usuario> listarSolicitantes() throws Exception;
    void alterarSenha(String senha, UUID usuarioID) throws Exception;
    void atualizarSolicitante(Usuario usuario, Authentication authentication) throws Exception;

}
