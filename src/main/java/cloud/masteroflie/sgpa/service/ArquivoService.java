package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.models.Arquivos;

public interface ArquivoService {
    Arquivos buscarArquivo(Long id);

    Void removerArquivo(Long id);

}
