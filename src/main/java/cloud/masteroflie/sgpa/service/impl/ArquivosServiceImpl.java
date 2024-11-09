package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.models.Arquivos;
import cloud.masteroflie.sgpa.models.Setor;
import cloud.masteroflie.sgpa.repository.ArquivoRepository;
import cloud.masteroflie.sgpa.repository.SetorRepository;
import cloud.masteroflie.sgpa.service.ArquivosService;
import cloud.masteroflie.sgpa.service.AzureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArquivosServiceImpl implements ArquivosService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private AzureService azureService;

    @Override
    public String acessoExterno(Arquivos arquivo, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PROCESS_EDIT"))) {
            Arquivos arquivoExterno = arquivoRepository.findById(arquivo.getId()).orElseThrow(() -> new Exception("Arquivo não encontrado"));
            if (arquivoExterno != null) {
                if(arquivoExterno.getAcessoExterno() == null){
                    arquivoExterno.setAcessoExterno(false);
                    arquivoRepository.save(arquivoExterno);
                    throw new Exception("O arquivo está nulo. Foi realizada uma correção automática. Recomendamos que entre em contato com o suporte para mais assistência.");
                }
                if (arquivoExterno.getAcessoExterno()){
                    arquivoExterno.setAcessoExterno(false);
                    arquivoRepository.save(arquivoExterno);
                    return "Acesso Externo Desabilitado";
                }else {
                    arquivoExterno.setAcessoExterno(true);
                    arquivoRepository.save(arquivoExterno);
                    return "Acesso Externo Habilitado";
                }
            }
            throw new Exception("Arquivo não encontrado");
        } else {
            throw new Exception("Você não possui permissão para editar um Setor");
        }
    }

    @Override
    public String downloadArquivo(Arquivos arquivo) throws Exception {
        Arquivos arquivoExterno = arquivoRepository.findById(arquivo.getId()).orElseThrow(() -> new Exception("Arquivo não encontrado"));
        return azureService.viewFile(arquivoExterno.getBlobName());
    }

    @Override
    public String deleteArquivo(Arquivos arquivo) throws Exception {
        Arquivos arquivoExterno = arquivoRepository.findById(arquivo.getId()).orElseThrow(() -> new Exception("Arquivo não encontrado"));
        azureService.deleteFile(arquivoExterno.getBlobName());
        arquivoRepository.delete(arquivoExterno);
        return "Arquivo Deletado";
    }

    @Override
    public List<Arquivos> listarArquivos() throws Exception {
        return List.of();
    }
}
