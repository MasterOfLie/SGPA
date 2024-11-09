package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.models.Setor;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SetorService {


    Setor criarSetor(Setor setor, Authentication authentication) throws Exception;
    Setor buscarSetor(UUID idSetor) throws Exception;
    List<Setor> listarSetor();
    String statusSetor(UUID id, Authentication authentication) throws Exception;
    void editarSetor(Setor setor, Authentication authentication) throws Exception;
}
