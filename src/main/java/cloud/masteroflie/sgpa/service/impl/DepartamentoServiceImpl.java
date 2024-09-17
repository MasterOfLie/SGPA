package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.repository.DepartamentoRepository;
import cloud.masteroflie.sgpa.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Override
    public List<Departamento> listaTodos() {
        return departamentoRepository.findAll();
    }
    public Departamento buscaPorId(Long id) {
        return departamentoRepository.findById(id).get();
    }


    @Override
    public void criarDepartamento(Departamento departamento) {
        departamento.setActive(true);
        departamentoRepository.save(departamento);
    }

    @Override
    public void salvarDepartamento(Departamento departamento, boolean ativo) {
        departamento.setActive(ativo);
        departamentoRepository.save(departamento);
    }
}
