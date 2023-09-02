package com.lp2.dominio;

import com.lp2.dominio.dto.veiculo.DadosEntradaVeiculo;
import com.lp2.dominio.enums.TipoVeiculo;
import com.lp2.service.VeiculoService;
import io.micronaut.core.annotation.Introspected;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Introspected
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;
    private TipoVeiculo tipoVeiculo;


    public Veiculo(){}

    public Veiculo(DadosEntradaVeiculo cadastro){
        this.modelo = cadastro.getModelo();
        this.marca = cadastro.getMarca();
        this.ano = cadastro.getAno();
        this.renavam = cadastro.getRenavam();
        this.chassi = cadastro.getChassi();
        this.tipoVeiculo = cadastro.getTipoVeiculo();
    }

}
