package cloud.masteroflie.sgpa.enums;

import lombok.Getter;

@Getter
public enum ProcessoStatus {
    ANDAMENTO("ANDAMENTO"),
    CONCLUIDO("CONCLUIDO"),
    CANCELADO("CANCELADO"),
    ARQUIVADO("ARQUIVADO");

    private String status;

    ProcessoStatus(String status) {
        this.status = status;
    }
}
