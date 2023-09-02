package com.lp2.dominio.dto.veiculo;

import com.lp2.dominio.enums.TipoVeiculo;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Introspected
@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaVeiculo {

    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;
    private TipoVeiculo tipoVeiculo;
}
