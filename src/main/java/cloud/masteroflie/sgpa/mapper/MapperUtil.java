package cloud.masteroflie.sgpa.mapper;


import cloud.masteroflie.sgpa.dto.ProcessosDTO;
import cloud.masteroflie.sgpa.dto.UsuarioDTO;
import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.models.Processo;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.service.ProcessoService;
import cloud.masteroflie.sgpa.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Component
public class MapperUtil {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private UsuarioService usuarioService;

    public List<ProcessosDTO> ProcessosResponse(){
        List<ProcessosDTO> processosDTOList = new ArrayList<>();
        List<Processo> processoList = processoService.listarProcessos();
        for (Processo processo : processoList) {
            ProcessosDTO processosDTO = new ProcessosDTO(processo.getId(), processo.getServicos().getNome(),
                    processo.getDescricao(), processo.getProtocolo(), processo.getAnoVigencia(), processo.getRequerente().getNome(),
                    processo.getUsuario().getNome(), processo.getDepartamento().getNome()
                    );
            processosDTOList.add(processosDTO);
        }

        return processosDTOList;
    }

    public List<ProcessosDTO> processosUsuario(){
        List<ProcessosDTO> processosDTOList = new ArrayList<>();
        List<Processo> processoList = processoService.listarProcessos();
        Usuario nome_usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set< Departamento> usuarioDepartamento = nome_usuario.getDepartamentos();
        System.out.println(usuarioDepartamento);
        for (Departamento usuariodepart : usuarioDepartamento) {
            System.out.println(usuariodepart.getNome());
        }
        for (Departamento departamento : usuarioDepartamento) {
            for (Processo processo : processoList) {
                if (processo.getDepartamento().getId().equals(departamento.getId())) {
                    ProcessosDTO processosDTO = new ProcessosDTO(processo.getId(), processo.getServicos().getNome(),
                            processo.getDescricao(), processo.getProtocolo(), processo.getAnoVigencia(), processo.getRequerente().getNome(),
                            processo.getUsuario().getNome(), processo.getDepartamento().getNome()
                    );
                    processosDTOList.add(processosDTO);
                }
            }
        }
        return processosDTOList;
    };

}
