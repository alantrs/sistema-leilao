package com.lp2.model;

import com.lp2.dto.dispositivo.DadosAtualizacaoRoteadorDTO;
import com.lp2.dto.dispositivo.DadosEntradaRoteadorDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Roteador extends DispositivoInformatica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;
    private Integer quantidadePortas;

    public Roteador(){}

    public Roteador(DadosEntradaRoteadorDTO cadastroRoteador){
        super(cadastroRoteador);
        this.quantidadePortas = cadastroRoteador.getQuantidadePortas();
    }

    public Roteador(Roteador roteador, DadosAtualizacaoRoteadorDTO atualizacaoRoteador){
        super(roteador, atualizacaoRoteador);
        this.id = roteador.getId();
        this.quantidadePortas = atualizacaoRoteador.getQuantidadePortas() != null ? atualizacaoRoteador.getQuantidadePortas() : roteador.quantidadePortas;
    }
}
