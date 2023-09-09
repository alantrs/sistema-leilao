package com.lp2.model.veiculo;

import com.lp2.model.enums.TipoVeiculo;
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
    private TipoVeiculo tipoVeiculo;

    public DadosExibicaoVeiculoDTO(Veiculo veiculo){
        this.id = veiculo.getId();
        this.modelo = veiculo.getModelo();
        this.marca = veiculo.getMarca();
        this.ano = veiculo.getAno();
        this.renavam = veiculo.getRenavam();
        this.chassi = veiculo.getChassi();
        this.tipoVeiculo = veiculo.getTipoVeiculo();
    }
}
