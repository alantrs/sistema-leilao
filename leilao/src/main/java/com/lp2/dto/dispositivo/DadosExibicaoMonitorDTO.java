package com.lp2.dto.dispositivo;


import com.lp2.dto.dispositivo.DadosExibicaoDispositivoDTO;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoMonitorDTO extends DadosExibicaoDispositivoDTO {

    private String tamanhoTela;

    public DadosExibicaoMonitorDTO() {
    }
}
