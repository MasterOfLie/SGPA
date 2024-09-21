package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.dto.ConfigDTO;
import cloud.masteroflie.sgpa.models.Aplicacao;

public interface AplicacaoService {
    Aplicacao getAplicacao(String aplicacao);
    Void salvarAplicacao(ConfigDTO config);
}
