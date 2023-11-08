package com.lp2.model;

import com.lp2.dto.veiculo.DadosAtualizacaoUtilitarioDTO;
import com.lp2.dto.veiculo.DadosEntradaUtilitarioDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Utilitario extends Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;

    private Integer capacidadePessoas;

    public Utilitario(){}

    public Utilitario(DadosEntradaUtilitarioDTO cadastroUtilitario){
        super(cadastroUtilitario);
        this.capacidadePessoas = cadastroUtilitario.getCapacidadePessoas();
    }

    public Utilitario(Utilitario utilitario, DadosAtualizacaoUtilitarioDTO atualizacaoUtilitario){
        super(utilitario, atualizacaoUtilitario);
        this.capacidadePessoas = atualizacaoUtilitario.getCapacidadePessoas();
    }
}
