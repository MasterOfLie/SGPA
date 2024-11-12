package cloud.masteroflie.sgpa.repository;

import cloud.masteroflie.sgpa.models.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, String> {
}
