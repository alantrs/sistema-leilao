package com.lp2.model;

import com.lp2.dto.veiculo.DadosAtualizacaoCarroDTO;
import com.lp2.dto.veiculo.DadosAtualizacaoMotocicletaDTO;
import com.lp2.dto.veiculo.DadosEntradaMotocicletaDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Motocicleta extends Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;

    private Integer cilindradas;

    public Motocicleta(){}

    public Motocicleta(DadosEntradaMotocicletaDTO cadastroMotocicleta){
        super(cadastroMotocicleta);
        this.cilindradas = cadastroMotocicleta.getCilindradas();
    }

    public Motocicleta(Motocicleta motocicleta, DadosAtualizacaoMotocicletaDTO atualizacaoMotocicleta){
        super(motocicleta, atualizacaoMotocicleta);
        this.id = motocicleta.getId();
        this.cilindradas = atualizacaoMotocicleta.getCilindradas() != null ? atualizacaoMotocicleta.getCilindradas() : motocicleta.getCilindradas();
    }
}
