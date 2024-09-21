package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.ConfigDTO;
import cloud.masteroflie.sgpa.models.Aplicacao;
import cloud.masteroflie.sgpa.repository.AplicacaoRepository;
import cloud.masteroflie.sgpa.service.AplicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplicacaoServiceImpl implements AplicacaoService {

    @Autowired
    private AplicacaoRepository aplicacaoRepository;

    @Override
    public Aplicacao getAplicacao(String aplicacao) {

        Aplicacao config = aplicacaoRepository.findById(aplicacao).orElse(null);
        if (config == null) {
            Aplicacao novo = new Aplicacao();
            novo.setAplicacao(aplicacao);
            novo.setNome("SGPA");
            return aplicacaoRepository.save(novo);
        }
        return aplicacaoRepository.findById(aplicacao).get();
    }

    @Override
    public Void salvarAplicacao(ConfigDTO config) {
        Aplicacao salvar = aplicacaoRepository.findById("aplicacao").get();
        salvar.setNome(config.getNome());
        salvar.setContainerBlob(config.getContainerBlob());
        salvar.setTheme(config.getTheme());
        aplicacaoRepository.save(salvar);
        return null;
    }
}
