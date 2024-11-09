package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.dto.PermissionDTO;
import cloud.masteroflie.sgpa.models.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface PerfilService {

    Perfil criarPerfil(Perfil perfil) throws Exception;

    List<Perfil> listarPerfil() throws Exception;

    Perfil buscarPerfil(UUID perfilID) throws Exception;
    String statusPerfil(UUID perfilID) throws Exception;
    void deletarPerfil(UUID perfilID) throws Exception;
    void addPermission(PermissionDTO permissionDTO) throws Exception;
    void removePermission(PermissionDTO permissionDTO) throws Exception;
    void editPerfil(Perfil perfil, Authentication authentication) throws Exception;
}
