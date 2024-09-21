package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.models.Servico;
import cloud.masteroflie.sgpa.repository.ServicoRepository;
import cloud.masteroflie.sgpa.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoServiceImpl implements ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    @Override
    public Servico buscarServico(Long id) {
        return servicoRepository.findById(id).get();
    }

    @Override
    public Servico criarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    public Servico salvar(Servico servico) {
        servicoRepository.save(servico);
        return servico;
    }
}
