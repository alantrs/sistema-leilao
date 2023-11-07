package com.lp2.dto.veiculo;

import com.lp2.model.Veiculo;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoVeiculoDTO {

    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;

    public DadosExibicaoVeiculoDTO(){
    }
}
