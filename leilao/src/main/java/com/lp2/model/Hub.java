package com.lp2.model;

import com.lp2.dto.dispositivo.DadosAtualizacaoHubDTO;
import com.lp2.dto.dispositivo.DadosEntradaHubDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Hub extends DispositivoInformatica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;
    private Integer quantidadePortas;

    public Hub(){}

    public Hub(DadosEntradaHubDTO cadastroHub){
        super(cadastroHub);
        this.quantidadePortas = cadastroHub.getQuantidadePortas();
    }

    public Hub(Hub hub, DadosAtualizacaoHubDTO atualizacaoHub){
        super(hub, atualizacaoHub);
        this.quantidadePortas = atualizacaoHub.getQuantidadePortas() != null ? atualizacaoHub.getQuantidadePortas() : hub.getQuantidadePortas();
    }
}
