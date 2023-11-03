package com.lp2.dto.dispositivo;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosAtualizacaoMonitorDTO extends DadosAtualizacaoDispositivoDTO{

    private String quantidadePortas;
}
