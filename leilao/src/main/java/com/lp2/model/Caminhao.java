package com.lp2.model;

import com.lp2.dto.veiculo.DadosAtualizacaoCaminhaoDTO;
import com.lp2.dto.veiculo.DadosAtualizacaoCarroDTO;
import com.lp2.dto.veiculo.DadosEntradaCaminhaoDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Caminhao extends Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;

    private Double peso;

    public Caminhao(){}

    public Caminhao(DadosEntradaCaminhaoDTO cadastroCaminhao){
        super(cadastroCaminhao);
        this.peso = cadastroCaminhao.getPeso();
    }

    public Caminhao(Caminhao caminhao, DadosAtualizacaoCaminhaoDTO atualizacaoCaminhao){
        super(caminhao, atualizacaoCaminhao);
        this.id = caminhao.getId();
        this.peso = atualizacaoCaminhao.getPeso() != null ? atualizacaoCaminhao.getPeso() : caminhao.getPeso();
    }
}
