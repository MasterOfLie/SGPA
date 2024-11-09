package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.dto.ServicoDTO;
import cloud.masteroflie.sgpa.models.Servico;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface ServicoService {

    List<Servico> listarServicos();
    Servico criarServico(ServicoDTO servicoDTO, Authentication auth) throws Exception;
    Servico buscarServico(UUID idServico) throws Exception;
    void deleteServico(UUID idServico, Authentication authentication) throws Exception;
    String statusServico(UUID idServico, Authentication authentication) throws Exception;
    void atualizarServico(ServicoDTO servicoDTO, Authentication authentication) throws Exception;
}
