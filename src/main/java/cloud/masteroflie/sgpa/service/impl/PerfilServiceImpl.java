package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.PermissionDTO;
import cloud.masteroflie.sgpa.enums.PermissionsRole;
import cloud.masteroflie.sgpa.models.Perfil;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.PerfilRepository;
import cloud.masteroflie.sgpa.repository.UsuarioRepository;
import cloud.masteroflie.sgpa.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PerfilServiceImpl implements PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Perfil criarPerfil(Perfil perfil) throws Exception {
        if (perfilRepository.existsByNome(perfil.getNome())) {
            throw new Exception("Já existe um perfil cadastrado com esse nome");
        }
        return perfilRepository.save(perfil);
    }

    @Override
    public List<Perfil> listarPerfil() throws Exception {
        if (perfilRepository.count() == 0) {
            throw new Exception("Não existe um perfil cadastrado");
        }
        return perfilRepository.findAll();
    }

    @Override
    public Perfil buscarPerfil(UUID perfilID) throws Exception {
        return perfilRepository.findById(perfilID).get();
    }

    @Override
    public String statusPerfil(UUID perfilID) throws Exception {
        Perfil perfil = perfilRepository.findById(perfilID).get();
        if(perfil.getAtivo()){
            List<Usuario> usuarios = usuarioRepository.findAllByPerfil(perfil);
            for (Usuario usuario : usuarios){
                usuario.setPerfil(null);
                usuarioRepository.save(usuario);
            }
            perfil.setAtivo(false);
            perfilRepository.save(perfil);
            return "Perfil Desativado. Perfil Removido dos Usuarios";
        }else{
            perfil.setAtivo(true);
            perfilRepository.save(perfil);
            return "Perfil Ativado";
        }
    }

    @Override
    public void deletarPerfil(UUID perfilID) throws Exception {
        if (perfilRepository.existsById(perfilID)) {
            if (perfilRepository.findById(perfilID).get().getUsuarios().stream().count() == 0) {
                perfilRepository.deleteById(perfilID);
            } else {
                throw new Exception("O Perfil possui usuarios");
            }
        }
    }

    @Override
    public void addPermission(PermissionDTO permissionDTO) throws Exception {
        PermissionsRole permission = PermissionsRole.valueOf(permissionDTO.getPermission());
        Perfil perfil = (Perfil) perfilRepository.findById(permissionDTO.getId()).get();
        if (!perfil.getPermissions().contains(permission)) {
            perfil.getPermissions().add(permission);
            perfilRepository.save(perfil);
        } else {
            throw new Exception("O Perfil já possui essa permissão");
        }
    }


    @Override
    public void removePermission(PermissionDTO permissionDTO) throws Exception {
        PermissionsRole permission = PermissionsRole.valueOf(permissionDTO.getPermission());
        Perfil perfil = (Perfil) perfilRepository.findById(permissionDTO.getId()).get();
        if (perfil.getPermissions().contains(permission)) {
            perfil.getPermissions().remove(permission);
            perfilRepository.save(perfil);
        } else {
            throw new Exception("O Perfil não possui essa permissão");
        }
    }

    @Override
    public void editPerfil(Perfil perfil, Authentication authentication) throws Exception {
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROFILE_EDIT"))){
            Perfil perfilEdit = perfilRepository.findById(perfil.getId()).orElseThrow(null);
            if(perfilEdit != null) {
                perfilEdit.setNome(perfil.getNome());
                perfilEdit.setDescricao(perfil.getDescricao());
                perfilRepository.save(perfilEdit);
            }else {
                throw new Exception("Perfil não encontrado.");
            }
        }else {
            throw new Exception("Você não possui permissão para alterar perfil.");
        }
    }

}
