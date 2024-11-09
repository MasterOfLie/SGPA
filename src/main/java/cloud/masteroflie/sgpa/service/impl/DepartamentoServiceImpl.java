package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.DepartamentoDTO;
import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.DepartamentoRepository;
import cloud.masteroflie.sgpa.repository.UsuarioRepository;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Departamento criarDepartamento(Departamento departamento, Authentication auth) throws Exception {
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEPARTMENT_CREATE"))){
            if(departamentoRepository.existsSetorByNome(departamento.getNome())) {
                throw new Exception("Já existe uma departamento com o nome " + departamento.getNome());
            }else {
                return departamentoRepository.save(departamento);
            }
        }else {
            throw new Exception("Você não possui permissão para criar um setor");
        }
    }

    @Override //TODO MUDAR PARA O USUARIO
    public void addDepartamento(DepartamentoDTO departamentoDTO, Authentication auth) throws Exception {
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEPARTMENT_ASSIGN"))){
            Usuario usuario = usuarioRepository.findById(departamentoDTO.getUsuarioID()).get();
            Departamento departamento = departamentoRepository.findById(departamentoDTO.getDepartamentoID()).get();
            if(usuario.getDepartamentos().contains(departamento)){
                usuario.getDepartamentos().remove(departamento);
                usuarioRepository.save(usuario);
            }else {
                usuario.getDepartamentos().add(departamento);
                usuarioRepository.save(usuario);
            }
        }else {
            throw new Exception("Você não possui permissão para ativar/desativar um departamento a esse usuario.");
        }
    }

    @Override
    public Departamento buscarDepartamento(UUID id) throws Exception {
        return departamentoRepository.findById(id).get();
    }

    @Override
    public String statusDepartamento(DepartamentoDTO departamentoDTO, Authentication authentication) throws Exception {
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEPARTMENT_TOGGLE"))){
            Departamento departamento = departamentoRepository.findById(departamentoDTO.getDepartamentoID()).orElseThrow(null);
            if(departamento != null){
                if(departamento.getUsuarios().stream().count() == 0){
                    if(departamento.getStatusDepartamento()){
                        departamento.setStatusDepartamento(false);
                        departamentoRepository.save(departamento);
                        return "Departamento desativado com sucesso";
                    }else {
                        departamento.setStatusDepartamento(true);
                        departamentoRepository.save(departamento);
                        return "Departamento ativado com sucesso";
                    }
                }else{
                    throw new Exception("Departamento possui usuario cadastrado.");
                }
            }else{
                throw new Exception("Departamento não encontrado.");
            }
        }else {
            throw new Exception("Você não possui permissão para ativar/desativar um departamento.");
        }
    }

    @Override
    public String deleteDepartamento(DepartamentoDTO departamentoDTO, Authentication authentication) throws Exception {
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEPARTMENT_DELETE"))){
            Departamento departamento = departamentoRepository.findById(departamentoDTO.getDepartamentoID()).orElseThrow(null);
            if(departamento != null){
                if(departamento.getUsuarios().stream().count() == 0){
                    departamentoRepository.delete(departamento);
                    return "Departamento excluido com sucesso";
                }else{
                    throw new Exception("Departamento possui usuario cadastrado.");
                }
            }else{
                throw new Exception("Departamento não encontrado.");
            }
        }else {
            throw new Exception("Você não possui permissão para excluir um departamento.");
        }
    }

    @Override
    public String editarDepartamento(Departamento Departamento, Authentication authentication) throws Exception {
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_DEPARTMENT_EDIT"))){
            Departamento departamento = departamentoRepository.findById(Departamento.getId()).orElseThrow(null);
            if(departamento != null){
               departamento.setNome(Departamento.getNome());
               departamento.setDescricao(Departamento.getDescricao());
               departamentoRepository.save(departamento);
               return "Departamento atualizado com sucesso";
            }else{
                throw new Exception("Departamento não encontrado.");
            }
        }else {
            throw new Exception("Você não possui permissão para editar um departamento.");
        }
    }
}
