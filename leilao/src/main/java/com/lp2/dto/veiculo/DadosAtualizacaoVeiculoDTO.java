package com.lp2.dto.veiculo;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosAtualizacaoVeiculoDTO {

    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;
}
