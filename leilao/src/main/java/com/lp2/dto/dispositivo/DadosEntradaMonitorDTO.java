package com.lp2.dto.dispositivo;

import com.lp2.enums.Condicao;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaMonitorDTO extends DadosEntradaDispositivoDTO{

    private String tamanhoTela;
}
