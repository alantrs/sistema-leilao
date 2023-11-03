package com.lp2.model;

import com.lp2.dto.dispositivo.DadosAtualizacaoMonitorDTO;
import com.lp2.dto.dispositivo.DadosEntradaMonitorDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Serdeable
public class Monitor extends DispositivoInformatica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subclass_id_sequence")
    @SequenceGenerator(name = "subclass_id_sequence", sequenceName = "subclass_id_sequence")
    private Long id;
    private String tamanhoTela;

    public Monitor(){}

    public Monitor(DadosEntradaMonitorDTO cadastroMonitor){
        super(cadastroMonitor);
        this.tamanhoTela = cadastroMonitor.getTamanhoTela();
    }

    public Monitor(Monitor monitor, DadosAtualizacaoMonitorDTO atualizacaoMonitor){
        super(monitor, atualizacaoMonitor);
        this.id = monitor.getId();
        this.tamanhoTela = atualizacaoMonitor.getQuantidadePortas() != null ? atualizacaoMonitor.getQuantidadePortas() : monitor.getTamanhoTela();
    }
}
