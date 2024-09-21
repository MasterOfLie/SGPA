package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.dto.ProcessoResponse;
import cloud.masteroflie.sgpa.models.Processo;

import java.util.List;

public interface ProcessoService {

    List<Processo> listarProcessos();

    Processo buscarPorID(Long id);

    Processo criarProcesso(ProcessoResponse response);

    Processo atualizarProcesso(ProcessoResponse processo, Long id);

}
