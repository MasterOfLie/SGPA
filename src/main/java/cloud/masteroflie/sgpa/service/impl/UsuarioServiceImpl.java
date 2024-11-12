package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.UsuarioDTO;
import cloud.masteroflie.sgpa.enums.PermissionsRole;
import cloud.masteroflie.sgpa.models.Perfil;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.PerfilRepository;
import cloud.masteroflie.sgpa.repository.UsuarioRepository;
import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static cloud.masteroflie.sgpa.controllers.AuthController.isFirstAccess;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public void cadastrarUsuario(Usuario usuario) throws Exception {
        if (isFirstAccess) {
            if (usuarioRepository.count() == 0) {
                Perfil perfil = new Perfil();
                perfil.setNome("Administrador");
                perfil.setDescricao("Perfil Administrador Inicial");
                List<PermissionsRole> listPermissionsRole = new ArrayList<>();
                listPermissionsRole.addAll(Arrays.asList(PermissionsRole.values()));
                perfil.setPermissions(listPermissionsRole);
                perfilRepository.save(perfil);
                usuario.setPerfil(perfil);
                isFirstAccess = false;
            }else{
                isFirstAccess = false;
            }
        }
        if (usuarioRepository.findByCpfCnpj(usuario.getCpfCnpj()) != null) {
            throw new Exception("Já exite um usuario com esse CPF/CNPJ.");
        }
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new Exception("Já exite um usuario com esse Email.");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuario(UUID usuarioID) throws Exception {
        if (usuarioRepository.findById(usuarioID).isPresent()) {
            return usuarioRepository.findById(usuarioID).get();
        } else {
            throw new Exception("Usuario não encontrado.");
        }
    }

    @Override
    public void addPerfil(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioRepository.findById(usuarioDTO.getIdUsuario()).isPresent()) {
            if (perfilRepository.findById(usuarioDTO.getPerfil()).isPresent()) {
                Perfil perfil = perfilRepository.findById(usuarioDTO.getPerfil()).get();
                if (perfil.getAtivo()) {
                    Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario()).get();
                    usuario.setPerfil(perfilRepository.findById(usuarioDTO.getPerfil()).get());
                    usuarioRepository.save(usuario);
                } else {
                    throw new Exception("Não e possivel atribuir um perfil desativado a um usuario.");
                }
            } else {
                throw new Exception("Perfil não encontrado.");
            }
        } else {
            throw new Exception("Usuario não encontrado.");
        }
    }

    @Override
    public void removerPerfil(UsuarioDTO usuarioDTO) throws Exception {
        if (usuarioRepository.findById(usuarioDTO.getIdUsuario()).isPresent()) {
            Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario()).get();
            usuario.setPerfil(null);
            usuarioRepository.save(usuario);
        } else {
            throw new Exception("Usuario não encontrado.");
        }
    }

    @Override
    public List<Usuario> listarSolicitantes() throws Exception {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> solicitantes = new ArrayList<>();
        for (Usuario usuario : usuarios){
            if (usuario.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEFAULT"))){
                solicitantes.add(usuario);
            }
        }
        return solicitantes;
    }

    @Override
    public void alterarSenha(String senha, UUID usuarioID) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioID).get();
        usuario.setPassword(passwordEncoder.encode(senha));
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizarSolicitante(Usuario usuario, Authentication authentication) throws Exception {
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_REQUESTER_EDIT"))){
            Usuario solicitante = usuarioRepository.findById(usuario.getId()).orElseThrow(() -> new Exception("Usuario nao encontrado"));
            if(usuarioRepository.findByEmail(usuario.getEmail()).equals(usuario.getEmail())){
                if(usuarioRepository.findByCpfCnpj(usuario.getCpfCnpj()).equals(usuario.getCpfCnpj())){
                    solicitante.setName(usuario.getName());
                    solicitante.setCpfCnpj(usuario.getCpfCnpj());
                    solicitante.setTelefone(usuario.getTelefone());
                    solicitante.setEmail(usuario.getEmail());
                    solicitante.setCep(usuario.getCep());
                    solicitante.setCidade(usuario.getCidade());
                    solicitante.setBairro(usuario.getBairro());
                    solicitante.setLogradouro(usuario.getLogradouro());
                    solicitante.setEstado(usuario.getEstado());
                    usuarioRepository.save(solicitante);
                }else {
                    throw new Exception("Já exite um usuario com esse CPF/CNPJ.");
                }
            }else {
                throw new Exception("Já exite um usuario com esse Email.");
            }
        }else {
            throw new Exception("Você não possui permissão para alterar solicitante.");
        }
    }

    @Override
    public Long countUsuarios() {
        return usuarioRepository.count();
    }
}
