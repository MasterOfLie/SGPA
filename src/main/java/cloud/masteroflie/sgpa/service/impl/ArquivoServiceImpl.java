package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.models.Arquivos;
import cloud.masteroflie.sgpa.repository.ArquivoRepository;
import cloud.masteroflie.sgpa.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArquivoServiceImpl implements ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Override
    public Arquivos buscarArquivo(Long id) {
        return arquivoRepository.findById(id).get();
    }
}
