package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.models.Arquivos;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ArquivosService {

    String acessoExterno(Arquivos arquivo, Authentication authentication) throws Exception;
    String downloadArquivo(Arquivos arquivo) throws Exception;
    String deleteArquivo(Arquivos arquivo) throws Exception;
    List<Arquivos> listarArquivos() throws Exception;
}
