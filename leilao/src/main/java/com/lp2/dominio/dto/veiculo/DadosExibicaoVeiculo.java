package com.lp2.dominio.dto.veiculo;

import com.lp2.dominio.Veiculo;
import com.lp2.dominio.enums.TipoVeiculo;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoVeiculo {

    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;
    private TipoVeiculo tipoVeiculo;

    public DadosExibicaoVeiculo(Veiculo veiculo){
        this.id = veiculo.getId();
        this.modelo = veiculo.getModelo();
        this.marca = veiculo.getMarca();
        this.ano = veiculo.getAno();
        this.renavam = veiculo.getRenavam();
        this.chassi = veiculo.getChassi();
        this.tipoVeiculo = veiculo.getTipoVeiculo();
    }
}
