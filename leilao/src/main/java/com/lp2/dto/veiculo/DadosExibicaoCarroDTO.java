package com.lp2.dto.veiculo;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoCarroDTO extends DadosExibicaoVeiculoDTO{

    private Integer quantidadePortas;

    public DadosExibicaoCarroDTO(){

    }
}
