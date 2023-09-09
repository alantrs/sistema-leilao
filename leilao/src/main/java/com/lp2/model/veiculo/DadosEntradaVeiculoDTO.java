package com.lp2.model.veiculo;

import com.lp2.model.enums.TipoVeiculo;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaVeiculoDTO {

    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;
    private TipoVeiculo tipoVeiculo;
}
