package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.MovimentacaoDTO;
import cloud.masteroflie.sgpa.dto.ProcessoDTO;
import cloud.masteroflie.sgpa.enums.ProcessoStatus;
import cloud.masteroflie.sgpa.models.*;
import cloud.masteroflie.sgpa.repository.*;
import cloud.masteroflie.sgpa.service.ProcessoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProcessoServiceImpl implements ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Override
    public List<Processo> listarProcessos() {
        return processoRepository.findAll();
    }

    @Override
    @Transactional
    public Processo criarProcesso(ProcessoDTO processoDTO, Authentication auth) throws Exception {
        Servico servico = servicoRepository.findById(processoDTO.getServicoID()).get();
        Departamento departamento = departamentoRepository.findById(processoDTO.getDepartamentoID()).get();
        Usuario usuario = (Usuario) auth.getPrincipal();
        Usuario solicitante = usuarioRepository.findById(processoDTO.getSolicitanteID()).get();
        if (servico.getStatusServico()) {
            Processo processo = new Processo();
            processo.setDescricao(processoDTO.getDescricao());
            processo.setServico(servico);
            processo.setDepartamento(departamento);
            processo.setUsuario(usuario);
            processo.setRequerente(solicitante);
            return processoRepository.save(processo);
        } else {
            throw new Exception("Não e possivel criar um processo. Serviço desabilitado");
        }
    }

    @Override
    public List<Processo> emAnalise(Authentication auth) throws Exception{
        Usuario usuario = (Usuario) auth.getPrincipal();
        Usuario usuarioAtualizadoo = usuarioRepository.findById(usuario.getId()).orElseThrow(() -> new Exception("Usuario nao encontrado"));
        List<Processo> processosAnalise = new ArrayList<>();
        for (Departamento departamento : usuarioAtualizadoo.getDepartamentos()) {
            Departamento departamentoAtualizado = departamentoRepository.findById(departamento.getId()).get();
            processosAnalise.addAll(departamentoAtualizado.getProcessos());
        }
        return processosAnalise;
    }

    @Override
    public List<Processo> meusProcessos(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Usuario usuarioAtualizadoo = usuarioRepository.findById(usuario.getId()).get();
        List<Processo> processos = usuarioAtualizadoo.getRequrente();
        return processos;
    }

    @Override
    public Processo buscarProcesso(UUID idProcesso) throws Exception {
        return processoRepository.findByUuid(idProcesso);
    }

    @Override
    public void movimentarProcesso(MovimentacaoDTO movimentacaoDTO, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROCESS_EDIT"))) {
            Usuario usuario = (Usuario) authentication.getPrincipal();
            Processo processo = buscarProcesso(movimentacaoDTO.getProcessoID());
            Departamento destino = departamentoRepository.findById(movimentacaoDTO.getDestino()).orElseThrow(() -> new Exception("Destino não Encontrado"));
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setDescricao(movimentacaoDTO.getDescricao());
            movimentacao.setProcesso(processo);
            movimentacao.setDepartamentoDestino(destino);
            movimentacao.setDepartamentoOrigem(processo.getDepartamento());
            movimentacao.setUsuarioMovimentacao(usuario);
            processo.setDepartamento(destino);
            processo.getMovimentacoes().add(movimentacaoRepository.save(movimentacao));
            processoRepository.save(processo);
        } else {
            throw new Exception("Você não possui permissão para movimentar um processo");
        }
    }

    @Override
    public String alterarStatus(ProcessoDTO processoDTO, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROCESS_EDIT"))) {
            Processo processo = buscarProcesso(processoDTO.getProcessoID());
            switch (processoDTO.getStatus()) {
                case "ANDAMENTO" -> {
                    processo.setStatus(ProcessoStatus.ANDAMENTO);
                    processoRepository.save(processo);
                    return "Processo alterado para Em Analise";
                }
                case "ARQUIVADO" -> {
                    processo.setStatus(ProcessoStatus.ARQUIVADO);
                    processoRepository.save(processo);
                    return "Processo alterado para Arquivo";
                }
                case "CONCLUIDO" -> {
                    processo.setStatus(ProcessoStatus.CONCLUIDO);
                    processoRepository.save(processo);
                    return "Processo alterado para Concluido";
                }
                case "CANCELADO" -> {
                    processo.setStatus(ProcessoStatus.CANCELADO);
                    processoRepository.save(processo);
                    return "Processo alterado para Cancelado";
                }
            }
            return "Error Inesperado";
        } else {
            throw new Exception("Você não possui permissão para movimentar um processo");
        }
    }
}
