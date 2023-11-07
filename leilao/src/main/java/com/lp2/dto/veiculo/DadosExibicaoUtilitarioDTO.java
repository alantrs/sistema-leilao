package com.lp2.dto.veiculo;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoUtilitarioDTO extends DadosExibicaoVeiculoDTO{

    private Integer capacidadePessoas;
}
