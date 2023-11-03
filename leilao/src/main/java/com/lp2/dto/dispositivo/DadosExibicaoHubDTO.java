package com.lp2.dto.dispositivo;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoHubDTO extends DadosExibicaoDispositivoDTO {

    private Integer quantidadePortas;

    public DadosExibicaoHubDTO() {
    }
}
