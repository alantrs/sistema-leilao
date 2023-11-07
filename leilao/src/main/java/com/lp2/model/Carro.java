package com.lp2.model;

import com.lp2.dto.veiculo.DadosAtualizacaoCarroDTO;
import com.lp2.dto.veiculo.DadosEntradaCarroDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Carro extends Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;

    private Integer quantidadePortas;

    public Carro(){}

    public Carro(DadosEntradaCarroDTO cadastroCarro){
        super(cadastroCarro);
        this.quantidadePortas = cadastroCarro.getQuantidadePortas();
    }

    public Carro(Carro carro, DadosAtualizacaoCarroDTO atualizacaoCarro){
        super(carro, atualizacaoCarro);
        this.id = carro.getId();
        this.quantidadePortas = atualizacaoCarro.getQuantidadePortas() != null ? atualizacaoCarro.getQuantidadePortas() : carro.getQuantidadePortas();
    }
}
