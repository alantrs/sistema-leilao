package com.lp2.dominio.dto.dispositivo;

import com.lp2.dominio.DispositivoInformatica;
import com.lp2.dominio.enums.Condicao;
import com.lp2.dominio.enums.TipoDispositivo;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Introspected
@Getter
@Setter
public class DadosExibicaoDispositivo {

    private Long id;
    private String nome;
    private String marca;
    private Integer ano;
    private Condicao condicao;
    private TipoDispositivo tipoDispositivo;

    public DadosExibicaoDispositivo(DispositivoInformatica dispositivoInformatica) {
        this.id = dispositivoInformatica.getId();
        this.nome = dispositivoInformatica.getNome();
        this.marca = dispositivoInformatica.getMarca();
        this.ano = dispositivoInformatica.getAno();
        this.condicao = dispositivoInformatica.getCondicao();
        this.tipoDispositivo = dispositivoInformatica.getTipoDispositivo();
    }
}

