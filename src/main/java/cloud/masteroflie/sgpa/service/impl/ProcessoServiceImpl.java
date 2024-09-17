package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.ProcessoResponse;
import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.models.Servico;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.DepartamentoRepository;
import cloud.masteroflie.sgpa.repository.ProcessoRepository;
import cloud.masteroflie.sgpa.repository.ServicoRepository;
import cloud.masteroflie.sgpa.repository.UserRepository;
import cloud.masteroflie.sgpa.service.ProcessoService;
import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoServiceImpl implements ProcessoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public List<Processo> listarProcessos() {
        return processoRepository.findAll();
    }

    @Override
    public Processo buscarPorID(Long id) {
        return processoRepository.findById(id).get();
    }

    @Override
    public Processo criarRequest(ProcessoResponse response) {
        Servico servico = servicoRepository.findById(response.getServicoID()).get();
        Departamento departamento = departamentoRepository.findById(response.getDepartamentoID()).get();
        Usuario usuario = userRepository.findById(response.getUsuarioID()).get();
        Usuario solicitante = userRepository.findById(response.getUsuarioID()).get();

        Processo processo = new Processo();
        processo.setRequerente(usuario);
        processo.setDepartamento(departamento);
        processo.setUsuario(usuario);
        processo.setDescricao(response.getDescricaoProcesso());
        processo.setServicos(servico);
        return  processoRepository.save(processo);
    }

    @Override
    public Processo atualizarProcesso(ProcessoResponse processo, Long id) {
        Processo processoAtualizar = processoRepository.findById(id).get();
        processoAtualizar.setDescricao(processo.getDescricaoProcesso());
        processoAtualizar.setServicos(servicoRepository.findById(processo.getServicoID()).get());
        processoAtualizar.setDepartamento(departamentoRepository.findById(processo.getDepartamentoID()).get());
        processoRepository.save(processoAtualizar);
        return processoAtualizar;
    }
}
