package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.models.Servico;
import cloud.masteroflie.sgpa.models.Setor;
import cloud.masteroflie.sgpa.repository.SetorRepository;
import cloud.masteroflie.sgpa.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SetorServiceImpl implements SetorService {
    @Autowired
    private SetorRepository setorRepository;

    @Override
    public Setor criarSetor(Setor setor, Authentication authentication) throws Exception { // TODO passar a authentication e seguro? TRAZER O USUARIO DO BANCO DE DADOS E VERIFICAR NAO E MAIS SEGURO ? LENTO, SEGURO?
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SECTOR_CREATE"))){
            if(setorRepository.existsSetorByNome(setor.getNome())) {
                throw new Exception("Já Existe uma setor com o nome " + setor.getNome());
            }else {
                return setorRepository.save(setor);
            }
        }else {
            throw new Exception("Você não possui permissão para criar um setor");
        }
    }

    @Override
    public Setor buscarSetor(UUID setor) throws Exception {
        return setorRepository.findById(setor).get();
    }

    @Override
    public List<Setor> listarSetor() {
        return setorRepository.findAll();
    }

    @Override
    public String statusSetor(UUID id, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SECTOR_TOGGLE"))) {
            Setor setor = setorRepository.findById(id).orElseThrow(() -> new Exception("Serviço não Encontrado"));
            if (setor.getStatusSetor()) {
                setor.setStatusSetor(false);
                setorRepository.save(setor);
                return "Setor desativado com sucesso";
            } else {
                setor.setStatusSetor(true);
                setorRepository.save(setor);
                return "Setor ativado com sucesso";
            }
        } else {
            throw new Exception("Você não possui permissão para ativar/desativar um Setor");
        }
    }

    @Override
    public void editarSetor(Setor setor, Authentication authentication) throws Exception {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SECTOR_EDIT"))) {
            Setor setorEditado = setorRepository.findById(setor.getId()).orElseThrow(() -> new Exception("Serviço não Encontrado"));
            setorEditado.setNome(setor.getNome());
            setorEditado.setDescricao(setor.getDescricao());
            setorRepository.save(setorEditado);
        } else {
            throw new Exception("Você não possui permissão para editar um Setor");
        }
    }
}
