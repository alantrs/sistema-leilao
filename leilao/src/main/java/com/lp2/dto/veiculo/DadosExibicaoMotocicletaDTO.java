package com.lp2.dto.veiculo;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoMotocicletaDTO extends DadosExibicaoVeiculoDTO{

    private Integer cilindradas;
}
