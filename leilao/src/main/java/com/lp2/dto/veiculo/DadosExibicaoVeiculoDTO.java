package com.lp2.dto.veiculo;

import com.lp2.model.Veiculo;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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

    public BigDecimal valorInical;

    public DadosExibicaoVeiculoDTO(){
    }

    public DadosExibicaoVeiculoDTO(Veiculo veiculo){
        this.id = veiculo.getId();
        this.modelo = veiculo.getModelo();
        this.marca = veiculo.getMarca();
        this.ano = veiculo.getAno();
        this.renavam = veiculo.getRenavam();
        this.chassi = veiculo.getChassi();
        this.valorInical = veiculo.getValorInicial();
    }
}
