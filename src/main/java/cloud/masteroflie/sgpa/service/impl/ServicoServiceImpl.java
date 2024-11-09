package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.ServicoDTO;
import cloud.masteroflie.sgpa.models.Servico;
import cloud.masteroflie.sgpa.repository.DepartamentoRepository;
import cloud.masteroflie.sgpa.repository.ServicoRepository;
import cloud.masteroflie.sgpa.repository.SetorRepository;
import cloud.masteroflie.sgpa.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private DepartamentoServiceImpl departamentoServiceImpl;

    @Override
    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    @Override
    public Servico criarServico(ServicoDTO servicoDTO, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SERVICE_CREATE"))) {
            if (servicoRepository.existsServicoByNome(servicoDTO.getNome())) {
                throw new Exception("Já Existe uma Servico com o nome " + servicoDTO.getNome());
            } else {
                Servico servico = new Servico();
                if (servicoDTO.getAberturaOnline()) {
                    servico.setNome(servicoDTO.getNome());
                    servico.setDescricao(servicoDTO.getDescricao());
                    servico.setSetor(setorRepository.findById(servicoDTO.getSetorID()).get());
                    servico.setAberturaOnline(servicoDTO.getAberturaOnline());
                    servico.setDepartamento(departamentoRepository.findById(servicoDTO.getDepartamentoID()).orElseThrow(null));
                } else {
                    servico.setNome(servicoDTO.getNome());
                    servico.setDescricao(servicoDTO.getDescricao());
                    servico.setSetor(setorRepository.findById(servicoDTO.getSetorID()).get());
                    servico.setAberturaOnline(servicoDTO.getAberturaOnline());
                }
                return servicoRepository.save(servico);
            }
        } else {
            throw new Exception("Você não possui permissão para criar um Servico");
        }
    }

    @Override
    public Servico buscarServico(UUID idServico) throws Exception {
        Servico servico = servicoRepository.findById(idServico).orElseThrow(null);
        if (servico != null) {
            return servico;
        } else {
            throw new Exception("Serviço não Encontrado");
        }
    }

    @Override
    public void deleteServico(UUID idServico, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SERVICE_DELETE"))) {
            Servico servico = servicoRepository.findById(idServico).orElseThrow(() -> new Exception("Serviço não Encontrado")); //TODO porque o null nao funciona?
            if (servico != null) {
                if (servico.getProcessos().stream().count() == 0) {
                    servicoRepository.delete(servico);
                } else {
                    throw new Exception("Não e possivel excluir esse Servico, Pois ele possui processos cadastrado!");
                }
            } else {
                throw new Exception("Serviço não Encontrado");
            }
        } else {
            throw new Exception("Você não possui permissão para excluir um Servico");
        }
    }

    @Override
    public String statusServico(UUID idServico, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SERVICE_DELETE"))) {
            Servico servico = servicoRepository.findById(idServico).orElseThrow(() -> new Exception("Serviço não Encontrado"));
            if (servico.getStatusServico()) {
                servico.setStatusServico(false);
                servicoRepository.save(servico);
                return "Serviço desativado com sucesso";
            } else {
                servico.setStatusServico(true);
                servicoRepository.save(servico);
                return "Serviço ativado com sucesso";
            }
        } else {
            throw new Exception("Você não possui permissão para ativar/desativar um Servico");
        }
    }

    @Override
    public void atualizarServico(ServicoDTO servicoDTO, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SERVICE_EDIT"))) {
            Servico servico = servicoRepository.findById(servicoDTO.getId()).orElseThrow(() -> new Exception("Serviço não Encontrado"));
            servico.setNome(servicoDTO.getNome());
            servico.setDescricao(servicoDTO.getDescricao());
            servico.setSetor(setorRepository.findById(servicoDTO.getSetorID()).get());
            servico.setAberturaOnline(servicoDTO.getAberturaOnline());
            if (servicoDTO.getAberturaOnline()) {
                servico.setDepartamento(departamentoRepository.findById(servicoDTO.getDepartamentoID()).orElseThrow(null));
            } else {
                servico.setDepartamento(null);
            }
            servicoRepository.save(servico);
        } else {
            throw new Exception("Você não possui permissão para editar um Servico");
        }
    }
}
