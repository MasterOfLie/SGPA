package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.repository.ProcessoRepository;
import cloud.masteroflie.sgpa.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoServiceImpl implements ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Override
    public List<Processo> listarProcessos() {
        return processoRepository.findAll();
    }
}
