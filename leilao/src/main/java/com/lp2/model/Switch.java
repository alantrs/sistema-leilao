package com.lp2.model;

import com.lp2.dto.dispositivo.DadosAtualizacaoSwitchDTO;
import com.lp2.dto.dispositivo.DadosEntradaSwitchDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Switch extends DispositivoInformatica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;
    private Integer quantidadePortas;

    public Switch(){}

    public Switch(DadosEntradaSwitchDTO cadastroSwitch){
        super(cadastroSwitch);
        this.quantidadePortas = cadastroSwitch.getQuantidadePortas();
    }

    public Switch(Switch swit, DadosAtualizacaoSwitchDTO atualizacaoSwitch){
        super(swit, atualizacaoSwitch);
        this.id = swit.getId();
        this.quantidadePortas = atualizacaoSwitch.getQuantidadePortas() != null ? atualizacaoSwitch.getQuantidadePortas() : swit.getQuantidadePortas();
    }
}
