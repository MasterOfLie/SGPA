package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.models.Servico;

import java.util.List;

public interface ServicoService {

    List<Servico> listarTodos();

    Servico buscarServico (Long id);

    Servico criarServico (Servico servico);
    Servico atualizarServico (Servico servico);


}
