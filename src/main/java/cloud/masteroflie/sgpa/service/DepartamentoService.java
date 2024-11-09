package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.dto.DepartamentoDTO;
import cloud.masteroflie.sgpa.models.Departamento;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface DepartamentoService {

    List<Departamento> listarDepartamentos();
    Departamento criarDepartamento(Departamento departamento, Authentication auth) throws Exception;
    void addDepartamento(DepartamentoDTO departamento, Authentication auth) throws Exception;
    Departamento buscarDepartamento(UUID id) throws Exception;
    String statusDepartamento(DepartamentoDTO departamentoDTO, Authentication authentication) throws Exception;
    String deleteDepartamento(DepartamentoDTO departamentoDTO, Authentication authentication) throws Exception;
    String editarDepartamento(Departamento Departamento, Authentication authentication) throws Exception;
}
