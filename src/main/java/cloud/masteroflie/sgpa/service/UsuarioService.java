package cloud.masteroflie.sgpa.service;

import cloud.masteroflie.sgpa.dto.SolicitanteDTO;
import cloud.masteroflie.sgpa.models.Departamento;
import cloud.masteroflie.sgpa.models.Usuario;

import java.util.List;

public interface UsuarioService {
    String salvar(Usuario usuario);

    List<Usuario> listarTodos();

    Usuario buscarPorId(Long id);

    List<Departamento> meusDepartamentos(Long id);

    String atualizarUsuario(Usuario usuario, List<Long> departamentosID);

    String atualizarSolicitantes(SolicitanteDTO solicitanteDTO);

}
