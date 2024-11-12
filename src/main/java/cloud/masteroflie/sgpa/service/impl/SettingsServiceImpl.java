package cloud.masteroflie.sgpa.service.impl;

import cloud.masteroflie.sgpa.dto.SetupDTO;
import cloud.masteroflie.sgpa.models.Settings;
import cloud.masteroflie.sgpa.models.Usuario;
import cloud.masteroflie.sgpa.repository.SettingsRepository;
import cloud.masteroflie.sgpa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl {

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private UsuarioService usuarioService;

    public String SetupApp(SetupDTO fistSetupDTO) throws Exception {
        settingsRepository.save(new Settings("settings::app:Nome", fistSetupDTO.getNomeAplicacao()));
        settingsRepository.save(new Settings("settings::app:AzureString", fistSetupDTO.getAzureString()));
        settingsRepository.save(new Settings("settings::app:Container", fistSetupDTO.getContainer()));
        Usuario usuario = new Usuario();
        usuario.setName(fistSetupDTO.getNome());
        usuario.setEmail(fistSetupDTO.getEmail());
        usuario.setCidade(fistSetupDTO.getCidade());
        usuario.setEstado(fistSetupDTO.getEstado());
        usuario.setBairro(fistSetupDTO.getSetor());
        usuario.setCpfCnpj(fistSetupDTO.getCpfCnpj());
        usuario.setPassword(fistSetupDTO.getSenha());
        usuarioService.cadastrarUsuario(usuario);
        return "Setup Complete";
    }
}
