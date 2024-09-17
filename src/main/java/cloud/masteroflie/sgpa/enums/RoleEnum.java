package cloud.masteroflie.sgpa.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("admin"),
    STAFF("staff"),
    USUARIO("usuario");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

}
