package com.lp2.dto.veiculo;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaCarroDTO extends DadosEntradaVeiculoDTO{

    private Integer quantidadePortas;
}
