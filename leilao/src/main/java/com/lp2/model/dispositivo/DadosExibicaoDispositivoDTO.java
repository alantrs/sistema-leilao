package com.lp2.model.dispositivo;

import com.lp2.model.enums.Condicao;
import com.lp2.model.enums.TipoDispositivo;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoDispositivoDTO {

    private Long id;
    private String nome;
    private String marca;
    private Integer ano;
    private Condicao condicao;
    private TipoDispositivo tipoDispositivo;
    private Integer quantidade;

    public DadosExibicaoDispositivoDTO(DispositivoInformatica dispositivoInformatica) {
        this.id = dispositivoInformatica.getId();
        this.nome = dispositivoInformatica.getNome();
        this.marca = dispositivoInformatica.getMarca();
        this.ano = dispositivoInformatica.getAno();
        this.condicao = dispositivoInformatica.getCondicao();
        this.tipoDispositivo = dispositivoInformatica.getTipoDispositivo();
        this.quantidade = dispositivoInformatica.getQuantidade();
    }
}

