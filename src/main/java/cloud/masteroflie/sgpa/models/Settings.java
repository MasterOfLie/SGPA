package cloud.masteroflie.sgpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Settings {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String settingsKey;
    private String value;

}
