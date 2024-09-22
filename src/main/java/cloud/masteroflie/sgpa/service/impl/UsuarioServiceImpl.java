package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.SolicitanteDTO;
import cloud.masteroflie.sgpa.enums.RoleEnum;
import cloud.masteroflie.sgpa.exception.ErroException;
import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.DepartamentoRepository;
import cloud.masteroflie.sgpa.repository.UserRepository;
import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public String salvar(Usuario usuario){
        userRepository.save(usuario);
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        return userRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<Departamento> meusDepartamentos(Long id) {
        Set<Departamento> departamentos = buscarPorId(id).getDepartamentos();
        List<Departamento> departamentoList = new ArrayList<>();
        for (Departamento departamento : departamentos){
            departamentoList.add(departamento);
        }
        return departamentoList;
    }

    @Override
    public String atualizarUsuario(Usuario usuario, List<Long> departamentosID)  {
            if(departamentosID == null || departamentosID.isEmpty()){
                Usuario usuarioAtualizado =  userRepository.findById(usuario.getId()).get();
                usuarioAtualizado.setRole(usuario.getRole());
                usuarioAtualizado.setNome(usuario.getNome());
                usuarioAtualizado.setUsername(usuario.getUsername());
                usuarioAtualizado.setEnabled(usuario.isEnabled());
                usuarioAtualizado.setUsername(usuario.getUsername());
                usuarioAtualizado.setDepartamentos(null);
                userRepository.save(usuarioAtualizado);
            }else {
                Set<Departamento> novosDepartamentos = new HashSet<>();
                Usuario usuarioAtualizado =  userRepository.findById(usuario.getId()).get();
                usuarioAtualizado.setRole(usuario.getRole());
                usuarioAtualizado.setNome(usuario.getNome());
                usuarioAtualizado.setUsername(usuario.getUsername());
                usuarioAtualizado.setEnabled(usuario.isEnabled());
                usuarioAtualizado.setUsername(usuario.getUsername());
                usuarioAtualizado.setDepartamentos(null);
                for (Long id : departamentosID){
                    Departamento departamento = departamentoRepository.findById(id).get();
                    novosDepartamentos.add(departamento);
                }
                usuarioAtualizado.setDepartamentos(novosDepartamentos);
                userRepository.save(usuarioAtualizado);
            }
            return null;
    }
    @Override
    public String atualizarSolicitantes(SolicitanteDTO solicitanteDTO)  {
        Usuario usuarioAtualizado =  userRepository.findById(solicitanteDTO.getId()).get();
        usuarioAtualizado.setNome(solicitanteDTO.getNome());
        usuarioAtualizado.setLogradouro(solicitanteDTO.getLogradouro());
        usuarioAtualizado.setCidade(solicitanteDTO.getCidade());
        usuarioAtualizado.setCep(solicitanteDTO.getCep());
        usuarioAtualizado.setEmail(solicitanteDTO.getEmail());
        usuarioAtualizado.setTelefone(solicitanteDTO.getTelefone());
        usuarioAtualizado.setCpf(solicitanteDTO.getCpf());
        usuarioAtualizado.setEmail(solicitanteDTO.getEmail());
        usuarioAtualizado.setNumero(solicitanteDTO.getNumero());
        usuarioAtualizado.setComplemento(solicitanteDTO.getComplemento());
        usuarioAtualizado.setBairro(solicitanteDTO.getBairro());
        userRepository.save(usuarioAtualizado);
        return null;
    }
}
