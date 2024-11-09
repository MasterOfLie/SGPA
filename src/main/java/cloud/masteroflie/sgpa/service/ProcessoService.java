package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.dto.MovimentacaoDTO;
import cloud.masteroflie.sgpa.dto.ProcessoDTO;
import cloud.masteroflie.sgpa.models.Processo;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface ProcessoService {

    List<Processo> listarProcessos();

    Processo criarProcesso(ProcessoDTO processo, Authentication auth) throws Exception;
    List<Processo> emAnalise(Authentication auth) throws Exception;
    List<Processo> meusProcessos(Authentication auth);
    Processo buscarProcesso(UUID idProcesso) throws Exception;
    void movimentarProcesso(MovimentacaoDTO movimentacaoDTO, Authentication authentication) throws Exception;
    String alterarStatus(ProcessoDTO processoDTO, Authentication authentication) throws Exception;
}
