package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.models.Departamento;

import java.util.List;

public interface DepartamentoService {
    List<Departamento> listaTodos();
    Departamento buscaPorId(Long id);
    void criarDepartamento(Departamento departamento);
    void salvarDepartamento(Departamento departamento, boolean ativo);

}
